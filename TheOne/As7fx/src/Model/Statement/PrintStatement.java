package Model.Statement;

import Model.adt.IList;
import Model.adt.MyDictionary;
import Model.ProgramStatement;
import Model.Exceptions.MyException;
import Model.Expression.*;
import Model.Type.IType;

public class PrintStatement implements IStatement {
    IExpression expr;

    public PrintStatement(IExpression expr){
        this.expr = expr;
    }

    @Override
    public String toString(){
        return "print(" +expr.toString()+")";
    }

    @Override
    public MyDictionary<String, IType> typecheck(MyDictionary<String, IType> typeEnv) throws MyException {
        expr.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public ProgramStatement execute(ProgramStatement state) throws Exception {
        try {
            IList out = state.getOut();
            out.add(expr.evaluate(state.getSymTable(), state.getHeap()).toString());
        }catch (MyException exception){
            throw new MyException(exception.getMessage());
        }

        return null;
    }

}

