package Model.Statement;

import Model.Exceptions.MyException;
import Model.Expression.IExpression;
import Model.ProgramStatement;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Type.ReferenceType;
import Model.Value.IValue;
import Model.Value.IntValue;
import Model.adt.IDictionary;
import Model.adt.ILockTable;
import Model.adt.MyDictionary;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewLockStatement implements IStatement{

    String variable;
    private Lock lock = new ReentrantLock();

    public NewLockStatement(String variable){
        this.variable = variable;
    }

    @Override
    public String toString(){
        return "newLock(" + variable + ")";
    }

    @Override
    public ProgramStatement execute(ProgramStatement state) throws Exception {
        lock.lock();

        ILockTable<Integer, Integer> LockTable = state.getLockTable();
        IDictionary<String, IValue> SymTable = state.getSymTable();

        Integer location = LockTable.getLockAddress();
        LockTable.put(location, -1);

        if(SymTable.isDefined(variable)){
            SymTable.update(variable, new IntValue(location));
        }
        else SymTable.update(variable, new IntValue(location));

        lock.unlock();
        return null;
    }

    @Override
    public MyDictionary<String, IType> typecheck(MyDictionary<String, IType> typeEnv) throws MyException {
        IType typevar = typeEnv.lookup(variable);
        if (typevar instanceof IntType)
            return typeEnv;
        else
            throw new MyException("NEW LOCK statement: right hand side and left hand side have different types ");
    }

}
