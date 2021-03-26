package Model.Statement.HeapStatement;

import Model.adt.IDictionary;
import Model.adt.IHeap;
import Model.adt.MyDictionary;
import Model.ProgramStatement;
import Model.Exceptions.MyException;
import Model.Exceptions.VariableNotDefinedInSymTableException;
import Model.Expression.IExpression;
import Model.Type.IType;
import Model.Type.ReferenceType;
import Model.Value.IValue;
import Model.Value.ReferenceValue;
import Model.Statement.IStatement;

public class NewStatement implements IStatement{

    String variable;
    IExpression expression;

    public NewStatement(String variable, IExpression expression){
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public String toString(){
        return "new(" + variable +","+ expression.toString() + ")";
    }

    @Override
    public MyDictionary<String, IType> typecheck(MyDictionary<String, IType> typeEnv) throws MyException {
        IType typevar = typeEnv.lookup(variable);
        IType typexp = expression.typecheck(typeEnv);
        if (typevar.equals(new ReferenceType(typexp)))
            return typeEnv;
        else
            throw new MyException("NEW statement: right hand side and left hand side have different types ");
    }

    @Override
    public ProgramStatement execute(ProgramStatement state) throws Exception {
        IDictionary<String, IValue> symTable = state.getSymTable();

        if(symTable.isDefined(variable)){

            if(symTable.lookup(variable).getType() instanceof ReferenceType) {

                IValue value = expression.evaluate(symTable, state.getHeap());
                IValue locationType=symTable.lookup(variable);
                IHeap heap = state.getHeap();

                if(value.getType().equals(((ReferenceType)(locationType.getType())).getInner())) {
                    int address= heap.allocate(value);
                    symTable.update(variable, new ReferenceValue(address, value.getType()));
                }
                else throw new MyException("The variable " + variable + " and the assigned value do not match!");
            }
            else throw new MyException("the variable " + variable + " is not of type Reference");
        }
        else throw new VariableNotDefinedInSymTableException("the variable " + variable + " was not declared before");

        return null;
    }
}
