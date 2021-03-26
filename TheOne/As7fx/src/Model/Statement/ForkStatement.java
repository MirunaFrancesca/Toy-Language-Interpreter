package Model.Statement;

import Model.Value.IValue;
import Model.adt.*;
import Model.ProgramStatement;
import Model.Exceptions.MyException;
import Model.Type.BoolType;
import Model.Type.IType;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ForkStatement implements IStatement{
    IStatement statement;

    public ForkStatement(IStatement statement){
        this.statement = statement;
    }

    @Override
    public ProgramStatement execute(ProgramStatement state) throws MyException {
        IDictionary<String, IValue> newSymbolTable = new MyDictionary<>();
        newSymbolTable.setContent(
                state.getSymTable().getContent().entrySet().stream()
                        .map((Map.Entry<String, IValue> entry) -> new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue().copy()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

        ProgramStatement p = new ProgramStatement(new MyStack<>(), newSymbolTable, state.getOut(), state.getFileTable(), state.getHeap(), state.getLockTable(), statement);
        return p;
    }

    @Override
    public String toString() {
        return "Fork(" + statement.toString() + ")";
    }

    @Override
    public MyDictionary<String, IType> typecheck(MyDictionary<String, IType> typeEnv) throws MyException {
        statement.typecheck(typeEnv);
        return typeEnv;
    }
}
