package Model.Statement;

import Model.adt.MyDictionary;
import Model.ProgramStatement;
import Model.Exceptions.MyException;
import Model.Expression.IExpression;
import Model.Type.BoolType;
import Model.Type.IType;
import Model.Value.BoolValue;
import Model.Value.IValue;

public class IfStatement implements IStatement {
    IExpression expression;
    IStatement thenStatement;
    IStatement elseStatement;

    public IfStatement(IExpression e, IStatement t, IStatement el) {
        expression = e;
        thenStatement = t;
        elseStatement = el;
    }

    @Override
    public String toString(){
        return "(IF("+ expression.toString()+") THEN(" +thenStatement.toString() +")ELSE("+elseStatement.toString()+"))";
    }

    @Override
    public MyDictionary<String, IType> typecheck(MyDictionary<String, IType> typeEnv) throws MyException {
        IType typexp=expression.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            thenStatement.typecheck(typeEnv);
            elseStatement.typecheck(typeEnv);
            return typeEnv;
        }
        else
            throw new MyException("IF statement: the condition of IF has not the type bool");
    }

    @Override
    public ProgramStatement execute(ProgramStatement state) throws Exception {

        IValue a = new BoolValue(false);
        IValue ifStatement = expression.evaluate(state.getSymTable(), state.getHeap());
        if (ifStatement.getType().equals(a.getType())){
            var condition = ((BoolValue) ifStatement).getVal();
            if(condition){
                thenStatement.execute(state);
            }
            else{
                elseStatement.execute(state);
            }
        }
        else{
            throw new MyException("If statement is not a boolean value!");
        }
        return null;
    }
}
