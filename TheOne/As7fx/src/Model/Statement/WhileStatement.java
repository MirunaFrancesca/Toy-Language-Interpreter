package Model.Statement;

import Model.adt.IDictionary;
import Model.adt.IStack;
import Model.adt.MyDictionary;
import Model.ProgramStatement;
import Model.Exceptions.MyException;
import Model.Expression.IExpression;
import Model.Type.BoolType;
import Model.Type.IType;
import Model.Value.BoolValue;
import Model.Value.IValue;

public class WhileStatement implements IStatement{
    IExpression condition;
    IStatement statement;

    public WhileStatement(IExpression Condition, IStatement Statement){
        this.condition=Condition;
        this.statement=Statement;
    }

    @Override
    public ProgramStatement execute(ProgramStatement state) throws Exception {
        IDictionary<String, IValue> symbolTable = state.getSymTable();
        IStack<IStatement> stack = state.getExeStack();
        IValue value = condition.evaluate(symbolTable, state.getHeap());

        if(value instanceof BoolValue){
            BoolValue newValue = (BoolValue)value;
            if(newValue.getVal()){
                stack.push(new WhileStatement(condition, statement));
                stack.push(statement);
            }
        }
        else throw new MyException("The condition expression is not of type boolean!");

        return null;
    }

    @Override
    public String toString(){
        return "while(" + condition.toString() + ") " +statement.toString();
    }

    @Override
    public MyDictionary<String, IType> typecheck(MyDictionary<String, IType> typeEnv) throws MyException {
        IType typexp=condition.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            statement.typecheck(typeEnv);
            return typeEnv;
        }
        else
            throw new MyException("WHILE statement: the condition of WHILE has not the type bool");
    }
}

