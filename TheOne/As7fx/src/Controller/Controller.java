package Controller;

import Model.Exceptions.RunTimeException;
import Model.ProgramStatement;
import Model.adt.*;
import Model.Exceptions.MyException;
import Repository.IRepo;
import Repository.Repo;
import Model.Value.IValue;
import Model.Value.ReferenceValue;

import java.io.IOException;
import java.sql.Ref;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Controller {

    IRepo repo;
    ProgramStatement currentProgram;
    ExecutorService executor;
    public ArrayList<ProgramStatement> completedPrograms ;

    public Controller(IRepo repository){
        repo = repository;
        this.completedPrograms = new ArrayList<>();
    }

    public void displayCurrentProgram(){
        System.out.println(currentProgram.toString());
    }

    public void oneStepForAllPrg(List<ProgramStatement> programStateList) throws RunTimeException{
        programStateList.forEach(prg -> {
            try {
                repo.logProgramStatementExec(prg);
            } catch (IOException | MyException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        });
        List<Callable<ProgramStatement>> callList = programStateList.stream()
                .map((ProgramStatement p) -> (Callable<ProgramStatement>) (p::oneStep))
                .collect(Collectors.toList());


        List<ProgramStatement> newPrgList = null;
        try {
            newPrgList = executor.invokeAll(callList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.exit(1);
                            return null;
                        }
                    }).filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (InterruptedException e) {
            throw new RunTimeException(e.getMessage());
        }

        programStateList.addAll(newPrgList);
        programStateList.forEach((prg -> {
            try {
                repo.logProgramStatementExec(prg);
            } catch (MyException | IOException e) {
                e.printStackTrace();
            }
        }));
        repo.setProgramList(programStateList);
    }

    public void oneStepForAllPrgStates(List<ProgramStatement> prgStates) throws MyException, IOException, InterruptedException{
        List<Callable<ProgramStatement>> callList = prgStates.stream()
                .map((ProgramStatement p) -> (Callable<ProgramStatement>) (p::oneStep))
                .collect(Collectors.toList());

        List<ProgramStatement> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                }).filter(p->p!=null)
                .collect(Collectors.toList());

        prgStates.addAll(newPrgList);
        prgStates.forEach((prg -> {
            try {
                repo.logProgramStatementExec(prg);
            } catch (MyException | IOException e) {
                System.out.println(e.getMessage());
            }
        }));
        repo.setProgramList(prgStates);
    }

    public IList<IValue> allStep() throws MyException{

        executor = Executors.newFixedThreadPool(2);
        repo.setCurrent(0);
        List<ProgramStatement> programList = removeCompletedPrograms(repo.getAll());
        IList<IValue> out = programList.get(0).getOut();
        while (!programList.isEmpty()) {
            ProgramStatement state = programList.get(0);
            state.getHeap().setContent(
                    garbageCollector(
                            getAddrFromSymTable(
                                    programList.stream().map(programState -> programState.getSymTable().getContent().values()).collect(Collectors.toList()),
                                    state.getHeap().getContent()
                            ),
                            state.getHeap().getContent()
                    )
            );
            oneStepForAllPrg(programList);
            programList = removeCompletedPrograms(repo.getAll());
        }
        executor.shutdownNow();
        repo.setProgramList(programList);
        return out;
    }

    public boolean allStepGUI() throws IOException, MyException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramStatement> prgList = removeCompletedPrograms(repo.getAll());
        if (!prgList.isEmpty()) {
            ProgramStatement state = prgList.get(0);
            state.getHeap().setContent(
                    safeGarbageCollector(getAddressFromSymTbl(prgList, state.getHeap().getContent()),
                            state.getHeap().getContent())
            );
            oneStepForAllPrgStates(prgList);
            return true;
        }else {
            executor.shutdownNow();
            repo.setProgramList(prgList);
            return false;
        }
    }

    public String getFile(){ return repo.getLogFilePath();}

    public List<ProgramStatement> removeCompletedPrograms(List<ProgramStatement> inPrgList){
        return inPrgList.stream()
                .filter(ProgramStatement::isNotCompleted)
                .collect(Collectors.toList());
    }

    Set<Integer> getAddrFromSymTable(List<Collection<IValue>> symTableValues, Map<Integer, IValue> heap){
        Set<Integer> toReturn = new TreeSet<>();
        symTableValues.forEach(symTable -> symTable.stream()
                .filter(v -> v instanceof ReferenceValue)
                .forEach(v -> {
                    while (v instanceof ReferenceValue) {
                        toReturn.add(((ReferenceValue)v).getAddress());
                        v = heap.get(((ReferenceValue)v).getAddress());
                    }
                }));

        return toReturn;
    }

    public List<Integer> getAddressFromSymTbl(List<ProgramStatement> prgList, Map<Integer, IValue> heap) {
        List<Collection<IValue>> symTblValues=prgList.stream()
                .map(prgState->prgState.getSymTable().getContent().values())
                .collect(Collectors.toList());
        List<Integer> symTblAddrs=new ArrayList<>();
        symTblValues.forEach(symTbl->symTbl.stream()
                .filter(v->v instanceof ReferenceValue)
                .forEach(v->{
                    while (v instanceof ReferenceValue) {
                        symTblAddrs.add(((ReferenceValue)v).getAddress());
                        v=heap.get(((ReferenceValue)v).getAddress());
                    }
                }));
        return symTblAddrs;
    }

    Map<Integer, IValue> garbageCollector(Set<Integer> symTableAddr, Map<Integer, IValue> heap){
        return heap.entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer, IValue> safeGarbageCollector(List<Integer> symTblAddr, Map<Integer,IValue> heap) {
        return heap.entrySet().stream()
                .filter(e->symTblAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public java.util.List<ProgramStatement> getCompletedPrograms(java.util.List<ProgramStatement> inProgramList) {
        return inProgramList.stream()
                .filter(Predicate.not(ProgramStatement::isNotCompleted))
                .collect(Collectors.toList());

    }

    public void clearFile(){
        repo.setCurrent(0);
    }

}
