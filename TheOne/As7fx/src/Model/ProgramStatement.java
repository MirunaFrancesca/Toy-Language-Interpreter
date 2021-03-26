package Model;

import Model.Exceptions.ExeStackEmptyException;
import Model.Exceptions.MyException;
import Model.Statement.*;
import Model.Type.*;
import Model.Value.*;
import Model.adt.*;

import java.io.BufferedReader;
import java.util.concurrent.atomic.AtomicInteger;

public class ProgramStatement {
    IStack<IStatement> exeStack;
    IDictionary<String,IValue> symTable;
    IList<IValue> out;
    IFileTable<String, BufferedReader> fileTable;
    IHeap heap;
    ILockTable<Integer,Integer> lockTable;
    IStatement originalProgram; //optional field, but good to have
    private final int id;
    private static final AtomicInteger programStatesCount = new AtomicInteger(0);

    private static synchronized int getNewProgramId() {
        return programStatesCount.addAndGet(1);
    }

    public ProgramStatement(IStack<IStatement> stack, IDictionary<String, IValue> symTable,
                            IList<IValue> out, IFileTable<String,BufferedReader> fileTable, IHeap heap,
                            ILockTable<Integer, Integer> lockTable, IStatement prg){
        this.exeStack=stack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
        this.lockTable = lockTable;
        originalProgram = prg;//recreate the entire original prg
        exeStack.push(prg);
        id = getNewProgramId();
    }

    public String toString(){
        return  "The execution stack is:" + "\n" + exeStack.toString() +
                "The table of symbols is:" + "\n" + symTable.toString() + "\n"+
                "The out list is:" + "\n" + out.toString() + "\n";
    }

    public String printToFile(){
        return  "Id: " + this.id+ "\n"+
                "The execution stack is:" + "\n" + exeStack.toString() +
                "The table of symbols is:" + "\n" + symTable.toString() + "\n"+
                "The heap table is:" + "\n"+ heap.to_string() + "\n" +
                "The lock table is:"+ "\n"+ lockTable.toString() + "\n" +
                "The out list is:" + "\n" + out.toString() +
                "The file table is:" + "\n" + fileTable.toString() + "\n" + "\n";
    }

    public String presentationInMenu(){
        return exeStack.toStringMenu();
    }

    public IStack<IStatement> getExeStack(){
        return exeStack;
    }

    public IDictionary<String,IValue> getSymTable(){
        return symTable;
    }

    public IList<IValue> getOut(){
        return out;
    }

    public IFileTable<String,BufferedReader> getFileTable(){
        return fileTable;
    }

    public IHeap getHeap() { return heap; }

    public ILockTable<Integer,Integer> getLockTable(){ return lockTable;}

    public Integer getId() { return this.id; }

    public Boolean isNotCompleted(){
        return !(this.exeStack.isEmpty());
    }

    public ProgramStatement oneStep() throws MyException, Exception{

        if(exeStack.isEmpty())
            throw new MyException("prgstate stack is empty");
        IStatement currentStatement = exeStack.pop();

        return currentStatement.execute(this);
    }


}
