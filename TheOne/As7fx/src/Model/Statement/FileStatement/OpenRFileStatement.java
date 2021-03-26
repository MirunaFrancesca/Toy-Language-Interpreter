package Model.Statement.FileStatement;

import Model.adt.IFileTable;
import Model.adt.MyDictionary;
import Model.ProgramStatement;
import Model.Exceptions.MyException;
import Model.Expression.IExpression;
import Model.Type.IType;
import Model.Value.IValue;
import Model.Value.StringValue;
import Model.Statement.IStatement;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OpenRFileStatement implements IStatement{

    IExpression expression;

    public OpenRFileStatement(IExpression exp){
        expression = exp;
    }

    @Override
    public ProgramStatement execute(ProgramStatement state) throws Exception {

        IValue value = expression.evaluate(state.getSymTable(), state.getHeap());

        if(value.getType().toString() == "String")
        {
            StringValue file = (StringValue)value;
            IFileTable<String, BufferedReader> fileTable = state.getFileTable();

            if(fileTable.lookup(file.getVal()) == null){
                File tmpDir = new File(file.getVal());
                if(!tmpDir.exists()){
                    Path path = Paths.get(file.getVal());
                    Path p= Files.createFile(path);
                }
                BufferedReader br = new BufferedReader(new FileReader(file.getVal()));
                fileTable.add(file.getVal(),br);
            }
            else
                throw new MyException("File " + this.expression.toString() + " already opened!");
        }
        else
            throw new MyException("The file is not a string!");

        return null;
    }

    @Override
    public String toString(){
        return "open " + this.expression.toString();
    }

    @Override
    public MyDictionary<String, IType> typecheck(MyDictionary<String, IType> typeEnv) throws MyException {
        expression.typecheck(typeEnv);
        return typeEnv;
    }

}


