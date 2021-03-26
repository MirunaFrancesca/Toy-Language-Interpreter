package Model.Statement.FileStatement;

import Model.adt.IDictionary;
import Model.adt.IFileTable;
import Model.adt.MyDictionary;
import Model.ProgramStatement;
import Model.Exceptions.MyException;
import Model.Expression.IExpression;
import Model.Type.IType;
import Model.Type.StringType;
import Model.Value.IValue;
import Model.Value.IntValue;
import Model.Statement.IStatement;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements IStatement{

    IExpression expression;
    String variableName;

    public ReadFileStatement(IExpression exp, String variableName){
        this.expression = exp;
        this.variableName = variableName;
    }

    @Override
    public ProgramStatement execute(ProgramStatement state) throws Exception {

        IDictionary<String,IValue> symTable = state.getSymTable();

        if(symTable.isDefined(variableName)) {
            IType typeVariable = symTable.lookup(variableName).getType();

            if(typeVariable.toString() == "int"){
                IValue value = expression.evaluate(state.getSymTable(), state.getHeap());
                if(value.getType().toString() == "String") {

                    String name = String.valueOf(value);
                    IFileTable<String, BufferedReader> file_table = state.getFileTable();

                    if (file_table.lookup(name) != null) {
                        BufferedReader reader = file_table.lookup(name);

                        try {
                            String line = reader.readLine();
                            IntValue new_value = new IntValue(0);
                            if (line != null) {
                                try {
                                    Integer i = Integer.parseInt(line);
                                    new_value = new IntValue(Integer.parseInt(line));
                                }
                                catch(NumberFormatException exc)
                                {
                                    throw new MyException("Cannot convert " + line + " to a number");
                                }
                            }
                            symTable.update(variableName,new_value);
                        }
                        catch (IOException exc) {
                            throw new MyException(exc.getMessage());
                        }
                    }
                }
                else
                    throw new MyException("Expression's evaluation not a StringValue!");
            }
            else
                throw new MyException("Variable not int!");
        }
        else
            throw new MyException("The file does not exist!");

        return null;
    }

    @Override
    public String toString(){
        return "read from " + this.expression.toString();
    }

    @Override
    public MyDictionary<String, IType> typecheck(MyDictionary<String, IType> typeEnv) throws MyException {
        IType typexp = expression.typecheck(typeEnv);
        if (typexp.equals(new StringType()))
            return typeEnv;
        else
            throw new MyException("READ FILE statment: the expression is not a string ");
    }

}
