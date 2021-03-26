package Model.Expression;

import Model.adt.IDictionary;
import Model.adt.IHeap;
import Model.adt.MyDictionary;
import Model.Exceptions.MyException;
import Model.Exceptions.VariableNotDefinedInSymTableException;
import Model.Type.IType;
import Model.Value.IValue;

public class VariableExpression implements IExpression{

    String variable;

    public VariableExpression(String variable){
        this.variable = variable;
    }

    @Override
    public String toString(){
        return variable;
    }

    @Override
    public IType typecheck(MyDictionary<String, IType> typeEnv) throws MyException {
        return typeEnv.lookup(variable);
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> table, IHeap heap) throws Exception {
        if(!table.isDefined(variable))
            throw new VariableNotDefinedInSymTableException("Variable " + variable + " is not defined!");
        return table.lookup(variable);
    }
}
