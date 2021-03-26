package Model.Statement.HeapStatement;

import Model.adt.IDictionary;
import Model.adt.IHeap;
import Model.adt.MyDictionary;
import Model.ProgramStatement;
import Model.Exceptions.MyException;
import Model.Expression.IExpression;
import Model.Type.IType;
import Model.Type.ReferenceType;
import Model.Value.IValue;
import Model.Value.ReferenceValue;
import Model.Statement.IStatement;

public class WriteHeapStatement implements IStatement{

    String variable_name;
    IExpression expression;
    public WriteHeapStatement(String heap_adress, IExpression expression){
        this.variable_name = heap_adress;
        this.expression = expression;
    }

    @Override
    public ProgramStatement execute(ProgramStatement state) throws Exception {
        IDictionary<String, IValue> symbolTable = state.getSymTable();
        IHeap heap = state.getHeap();

        if(!symbolTable.isDefined(variable_name))
            throw new MyException(variable_name + "is not defined");

        IValue variable = symbolTable.lookup(variable_name);
        if(!(variable.getType() instanceof ReferenceType))
            throw new MyException(variable_name + "is not of type Reference");

        int address = ((ReferenceValue) variable).getAddress();

        if(!heap.isDefined(address))
            throw new MyException(variable_name + "is not defined in the heap");

        IValue value = expression.evaluate(symbolTable, heap);

        if(!value.getType().equals(((ReferenceValue)variable).getLocationType()))
            throw new MyException(value + "is of " + ((ReferenceValue)variable).getLocationType() + " type" );

        heap.update(address, value);

        return null;

    }
    @Override
    public String toString(){
        return "writeHeap( " + this.variable_name + ", " + expression + " )";
    }

    @Override
    public MyDictionary<String, IType> typecheck(MyDictionary<String, IType> typeEnv) throws MyException {
        if (typeEnv.lookup(variable_name).equals(new ReferenceType(expression.typecheck(typeEnv))))
            return typeEnv;
        else
            throw new MyException("WriteHeap: right hand side and left hand side have different types");
    }
}
