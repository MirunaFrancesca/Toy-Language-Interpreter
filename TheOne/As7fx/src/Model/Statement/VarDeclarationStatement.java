package Model.Statement;

import Model.adt.IDictionary;
import Model.adt.IStack;
import Model.adt.MyDictionary;
import Model.ProgramStatement;
import Model.Exceptions.MyException;
import Model.Exceptions.VariableNotDefinedInSymTableException;
import Model.Type.*;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;

public class VarDeclarationStatement implements IStatement {

    String name;
    IType type;

    public VarDeclarationStatement(String name, IType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type.toString() + " " + this.name;
    }

    @Override
    public MyDictionary<String, IType> typecheck(MyDictionary<String, IType> typeEnv) throws MyException {
        typeEnv.update(name,type);
        return typeEnv;
    }

    @Override
    public ProgramStatement execute(ProgramStatement state) throws Exception {

        IDictionary<String, IValue> symTable = state.getSymTable();

        if (!symTable.isDefined(name)) {
            symTable.update(name,this.type.defaultValue());

        } else throw new MyException("the used variable" + name + " was declared before!");

        return null;

    }
}
