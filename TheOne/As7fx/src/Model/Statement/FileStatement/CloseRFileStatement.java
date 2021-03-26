package Model.Statement.FileStatement;

import Model.adt.IFileTable;
import Model.adt.MyDictionary;
import Model.ProgramStatement;
import Model.Exceptions.MyException;
import Model.Expression.IExpression;
import Model.Type.IType;
import Model.Type.StringType;
import Model.Value.IValue;
import Model.Statement.IStatement;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStatement implements IStatement {
    IExpression expression;

    public CloseRFileStatement(IExpression exp){
        this.expression = exp;
    }

    @Override
    public ProgramStatement execute(ProgramStatement state) throws Exception {
        IValue value = expression.evaluate(state.getSymTable(), state.getHeap());
        if(value.getType().equals(new StringType())){
            String new_value = String.valueOf(value);
            IFileTable<String, BufferedReader> fileTable = state.getFileTable();
            if(fileTable.lookup(new_value) != null) {
                BufferedReader br = state.getFileTable().lookup(new_value);
                try {
                    br.close();
                    fileTable.remove(new_value);
                }
                catch(IOException exc){
                    throw new MyException(exc.getMessage());
                }
            }
            else throw new MyException("Undefined file_name");
        }
        else throw new MyException("File name is not a string");


        return null;
    }

    @Override
    public String toString(){
        return "close " + this.expression.toString();
    }

    @Override
    public MyDictionary<String, IType> typecheck(MyDictionary<String, IType> typeEnv) throws MyException {
        expression.typecheck(typeEnv);
        return typeEnv;
    }

}
