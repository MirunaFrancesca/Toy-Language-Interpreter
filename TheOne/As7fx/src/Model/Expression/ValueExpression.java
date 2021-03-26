package Model.Expression;

import Model.adt.IDictionary;
import Model.adt.IHeap;
import Model.adt.MyDictionary;
import Model.Exceptions.MyException;
import Model.Type.IType;
import Model.Value.IValue;

public class ValueExpression implements IExpression{

    IValue expression;

    public ValueExpression(IValue expression){
        this.expression = expression;
    }

    @Override
    public String toString(){
        return expression.toString();
    }

    @Override
    public IType typecheck(MyDictionary<String, IType> typeEnv) throws MyException {
        return expression.getType();
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> table, IHeap heap) throws Exception {
        return expression;
    }
}
