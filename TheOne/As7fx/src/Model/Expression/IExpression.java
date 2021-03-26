package Model.Expression;

import Model.adt.IDictionary;
import Model.adt.IHeap;
import Model.adt.MyDictionary;
import Model.Exceptions.MyException;
import Model.Type.IType;
import Model.Value.*;

public interface IExpression {
    public IValue evaluate(IDictionary<String, IValue> table, IHeap heap) throws MyException, Exception;
    public String toString();
    IType typecheck(MyDictionary<String,IType> typeEnv) throws MyException;
}

