package Model.Expression;

import Model.adt.IDictionary;
import Model.adt.IHeap;
import Model.adt.MyDictionary;
import Model.Exceptions.MyException;
import Model.Type.*;
import Model.Value.*;

public class LogicExpression implements IExpression{

    IExpression e1;
    IExpression e2;
    int operand; //1-and, 2-or, 3-not

    public LogicExpression(IExpression e1, IExpression e2, int operand){
        this.e1 = e1;
        this.e2 = e2;
        this.operand = operand;
    }

    public LogicExpression(IExpression e1, int operand){
        this.e1 = e1;
        IValue value = new BoolValue(false);
        this.e2 = new ValueExpression(value);
        this.operand = operand;
    }

    @Override
    public String toString(){
        if(operand == 1)
            return this.e1.toString() + " and " + this.e2.toString();
        else
        if(operand == 2)
            return this.e1.toString() + " or " + this.e2.toString();
        else
            return " not " + this.e1.toString();
    }

    @Override
    public IType typecheck(MyDictionary<String, IType> typeEnv) throws MyException {
        IType type1, type2;
        type1=e1.typecheck(typeEnv);
        type2=e2.typecheck(typeEnv);

        if(type1.equals(new BoolType())){

            if(operand == 3)
                return new BoolType();

            if(type2.equals(new BoolType())){
                return new BoolType();
            } else throw new MyException("second operand is not boolean");

        }else throw new MyException("first operand is not boolean");
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> table, IHeap heap) throws Exception {
        IValue v1,v2;
        v1= e1.evaluate(table, heap);

        if (v1.getType().equals(new BoolType())) {

            v2 = e2.evaluate(table, heap);
            if (v2.getType().equals(new BoolType())) {

                BoolValue i1 = (BoolValue)v1;
                BoolValue i2 = (BoolValue) v2;

                boolean n1, n2;
                n1= i1.getVal();
                n2 = i2.getVal();

                if (operand==1)
                    return new BoolValue(n1 && n2);
                if (operand ==2)
                    return new BoolValue(n1 || n2);
                if(operand==3)
                    return new BoolValue(!n1);
            }
            else
                throw new MyException("second operand is not boolean");
        }
        else
            throw new MyException("first operand is not boolean");

        return null;
    }
}
