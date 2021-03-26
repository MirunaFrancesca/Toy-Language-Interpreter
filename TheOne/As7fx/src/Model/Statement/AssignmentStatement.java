package Model.Statement;

import Model.adt.IDictionary;
import Model.adt.IStack;
import Model.adt.MyDictionary;
import Model.ProgramStatement;
import Model.Exceptions.MyException;
import Model.Exceptions.VariableNotDefinedInSymTableException;
import Model.Expression.IExpression;
import Model.Value.*;
import Model.Type.*;

public class AssignmentStatement implements IStatement {
    String variable;
    IExpression expression;

    public AssignmentStatement(String variable, IExpression expression){
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public String toString(){
        return variable +"="+ expression.toString();
    }

    @Override
    public MyDictionary<String, IType> typecheck(MyDictionary<String, IType> typeEnv) throws MyException {
        IType typevar = typeEnv.lookup(variable);
        IType typexp = expression.typecheck(typeEnv);
        if (typevar.equals(typexp))
            return typeEnv;
        else
            throw new MyException("ASSIGNMENT statment: right hand side and left hand side have different types ");
    }

    @Override
    public ProgramStatement execute(ProgramStatement state) throws Exception {
        IStack<IStatement> stack = state.getExeStack();
        IDictionary<String,IValue> symTable = state.getSymTable();

        if(symTable.isDefined(variable)){

            IValue value = expression.evaluate(symTable, state.getHeap());
            IType typeVariable = symTable.lookup(variable).getType();

            if (value.getType().equals(typeVariable)){
                symTable.update(variable,value);
            }
            else
                throw new MyException("declared type of variable " + variable + " and type of  the assigned expression do not match");
        }
        else throw new VariableNotDefinedInSymTableException("the variable " + variable + " was not declared before");

        return null;
    }
}
