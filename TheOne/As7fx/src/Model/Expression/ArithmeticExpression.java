package Model.Expression;

import Model.adt.IDictionary;
import Model.adt.IHeap;
import Model.adt.MyDictionary;
import Model.Exceptions.MyException;
import Model.Type.*;
import Model.Value.*;

import Model.Exceptions.*;

public class ArithmeticExpression implements IExpression{

    IExpression e1;
    IExpression e2;
    int operand; //1-plus, 2-minus, 3-star, 4-divide

    public ArithmeticExpression(IExpression e1, IExpression e2, int operand){
        this.e1 = e1;
        this.e2 = e2;
        this.operand = operand;
    }

    @Override
    public String toString(){
        String symbol = "";
        if(operand == 1)
            symbol = "+";
        if(operand == 2)
            symbol = "-";
        if(operand == 3)
            symbol = "*";
        if(operand == 4)
            symbol = "/";

        return this.e1.toString() + symbol + this.e2.toString();

    }

    @Override
    public IType typecheck(MyDictionary<String, IType> typeEnv) throws MyException {
        IType type1, type2;
        type1=e1.typecheck(typeEnv);
        type2=e2.typecheck(typeEnv);

        if(type1.equals(new IntType())){

            if(type2.equals(new IntType())){
                return new IntType();
            } else throw new MyException("second operand is not an integer");

        }else throw new MyException("first operand is not an integer");
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> table, IHeap heap) throws Exception{
        IValue v1,v2;
        v1= e1.evaluate(table, heap);

        if (v1.getType().equals(new IntType())) {

            v2 = e2.evaluate(table, heap);
            if (v2.getType().equals(new IntType())) {

                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;

                int n1, n2;
                n1= i1.getVal();
                n2 = i2.getVal();

                if (operand==1)
                    return new IntValue(n1+n2);
                if (operand ==2)
                    return new IntValue(n1-n2);
                if(operand==3)
                    return new IntValue(n1*n2);
                if(operand==4) {
                    if(n2==0)
                        throw new DivisionByZeroException("division by zero");
                    return new IntValue(n1 / n2);

                }
            }
            else
                throw new MyException("second operand is not an integer");
        }
        else
            throw new MyException("first operand is not an integer");

        return null;
    }
}
