package Repository;
import Model.adt.*;
import Model.Exceptions.MyException;
import Model.Expression.*;
import Model.ProgramStatement;
import Model.Statement.*;
import Model.Type.*;
import Model.Value.*;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Repo implements IRepo{

    List<ProgramStatement> statements;
    int current;
    String logFilePath;

    public Repo(String logFilePath){
        current = 0;
        statements = new ArrayList<ProgramStatement>();
        this.logFilePath = logFilePath;
    }

    public Repo(){
        current = 0;
        statements = new ArrayList<ProgramStatement>();
        this.logFilePath = "";
    }

    public void clearFile() throws FileNotFoundException {
        File path = new File(logFilePath);
        boolean exists = path.exists();
        if(exists)
        {
            PrintWriter writer = new PrintWriter(path);
            writer.print("");
            writer.close();
        }
    }
    @Override
    public void setCurrent(int position){
        current = position;
        try {
            clearFile();
        }
        catch (Exception exc) {
        }
    }

    @Override
    public void add(ProgramStatement p) {
        statements.add(p);
    }

    @Override
    public ProgramStatement getCurrent() {
        return statements.get(current);
    }

    @Override
    public List<ProgramStatement> getAll() {
        return this.statements;
    }

    @Override
    public void setProgramList(List<ProgramStatement> statements) {
        this.statements = statements;
    }

    @Override
    public String getLogFilePath(){
        return this.logFilePath;
    }

    @Override
    public ProgramStatement getProgramStatementById(int index) {
        for (ProgramStatement state:this.statements)
            if (state.getId()==index)
                return this.getCurrent();
        return null;
    }

    @Override
    public void logProgramStatementExec(ProgramStatement statement) throws MyException, IOException {
        Path fileName = Path.of(logFilePath);
        String allLines = Files.readString(fileName);
        Files.writeString(fileName,  allLines + statement.printToFile());
    }
}
