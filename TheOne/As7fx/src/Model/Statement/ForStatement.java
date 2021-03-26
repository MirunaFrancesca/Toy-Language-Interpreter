package Model.Statement;

import Model.Exceptions.MyException;
import Model.Expression.IExpression;
import Model.Expression.RelationalExpression;
import Model.Expression.VariableExpression;
import Model.ProgramStatement;
import Model.Type.IType;
import Model.Value.IntValue;
import Model.adt.*;


public class ForStatement implements IStatement{
    String variable;
    IExpression exp1, exp2, exp3;
    IStatement statement;
    public ForStatement(String variable, IExpression ex1, IExpression ex2, IExpression ex3, IStatement statement) throws Exception, MyException {
        this.variable = variable;
        this.exp1 = ex1;
        this.exp2 = ex2;
        this.exp3 = ex3;
        this.statement = statement;
    }
    @Override
    public ProgramStatement execute(ProgramStatement state) throws Exception, MyException {
        if(!((exp1.evaluate(state.getSymTable(), state.getHeap())) instanceof IntValue))
            throw new Exception("Error");
        if(!((exp2.evaluate(state.getSymTable(), state.getHeap())) instanceof IntValue))
            throw new Exception("Error");
        if(!((exp3.evaluate(state.getSymTable(), state.getHeap())) instanceof IntValue))
            throw new Exception("Error");
        IStack<IStatement> stack = state.getExeStack();
        IStatement newStatement = new CompoundStatement(new AssignmentStatement("v", exp1),
                new WhileStatement(new RelationalExpression(new VariableExpression("v"), exp2, 1), new CompoundStatement(this.statement,
                        new AssignmentStatement("v", exp3))));
        stack.push(newStatement);
        return null;
    }
    @Override
    public MyDictionary<String, IType> typecheck(MyDictionary<String, IType> typeTable) throws MyException {
        return typeTable;
    }
    @Override
    public String toString(){
        return "for(" + variable + "= " + exp1.toString() + "; " + variable + "< " + exp2.toString() + "; " + variable + "= " + exp3.toString() + ") " +
                this.statement.toString();
    }
}

