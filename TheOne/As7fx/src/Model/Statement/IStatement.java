package Model.Statement;

import Model.adt.MyDictionary;
import Model.ProgramStatement;
import Model.Exceptions.MyException;
import Model.Type.IType;

public interface IStatement {
    public ProgramStatement execute(ProgramStatement state) throws Exception;
    public String toString();
    MyDictionary<String, IType> typecheck(MyDictionary<String,IType> typeEnv) throws MyException;
}

