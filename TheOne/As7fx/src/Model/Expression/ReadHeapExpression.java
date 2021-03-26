package Model.Expression;

import Model.adt.IDictionary;
import Model.adt.IHeap;
import Model.adt.MyDictionary;
import Model.Exceptions.MyException;
import Model.Type.IType;
import Model.Type.ReferenceType;
import Model.Value.IValue;
import Model.Value.ReferenceValue;

import java.sql.Ref;

public class ReadHeapExpression implements IExpression{
    IExpression expression;

    public ReadHeapExpression(IExpression expression){
        this.expression = expression;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> table, IHeap heap) throws Exception {
        IValue value = expression.evaluate(table, heap);

        if(!(value.getType() instanceof ReferenceType))
            throw new MyException(value + " is not of Reference type");

        int address = ((ReferenceValue)value).getAddress();

        if(!heap.isDefined(address))
            throw new MyException(value + " is not defined in the heap");

        return heap.getValue(address);
    }

    @Override
    public String toString(){
        return "readHeap( " + expression + " )";
    }

    @Override
    public IType typecheck(MyDictionary<String, IType> typeEnv) throws MyException {
        IType type=expression.typecheck(typeEnv);
        if (type instanceof ReferenceType) {
            ReferenceType ref_type =(ReferenceType) type;
            return ref_type.getInner();
        } else
            throw new MyException("the rH argument is not a Ref Type");
    }

}
