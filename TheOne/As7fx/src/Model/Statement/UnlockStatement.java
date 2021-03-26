package Model.Statement;

import Model.Exceptions.MyException;
import Model.ProgramStatement;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Value.IValue;
import Model.Value.IntValue;
import Model.adt.ILockTable;
import Model.adt.MyDictionary;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnlockStatement implements IStatement{

    String variable;
    private Lock lock = new ReentrantLock();

    public UnlockStatement(String var){
        this.variable = var;
    }
    @Override
    public ProgramStatement execute(ProgramStatement state) throws Exception {
        lock.lock();
        ILockTable<Integer, Integer> LockTable = state.getLockTable();

        if(!state.getSymTable().isDefined(variable))
            throw new MyException("Variable is not defined in SymTable!");
        else {
            IValue foundIndex = state.getSymTable().lookup(variable);
            if(!(LockTable.isDefined(((IntValue) foundIndex).getVal()))){
                throw new MyException("Index " + foundIndex.toString() + " not found in Lock Table!");
            }
            else {
                if(LockTable.get(((IntValue) foundIndex).getVal()) == state.getId())
                    LockTable.put(((IntValue) foundIndex).getVal(),-1);
            }
        }

        lock.unlock();
        return null;
    }

    @Override
    public MyDictionary<String, IType> typecheck(MyDictionary<String, IType> typeEnv) throws MyException {
        if(typeEnv.lookup(variable) instanceof IntType)
            return typeEnv;
        else
            throw new MyException("variable not of type int!");
    }

    @Override
    public String toString(){
        return "Unlock(" + this.variable + ")";
    }
}
