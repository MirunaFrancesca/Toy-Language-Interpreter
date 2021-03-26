package Model.Statement;

import Model.adt.MyDictionary;
import Model.ProgramStatement;
import Model.Exceptions.MyException;
import Model.Type.IType;

public class NopStatement implements IStatement {

    @Override
    public ProgramStatement execute(ProgramStatement state) throws Exception {
        return null;
    }

    @Override
    public MyDictionary<String, IType> typecheck(MyDictionary<String, IType> typeEnv) throws MyException {
        return null;
    }
}

