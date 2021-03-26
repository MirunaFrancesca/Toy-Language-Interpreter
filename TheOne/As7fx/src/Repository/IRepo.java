package Repository;
import Model.ProgramStatement;
import Model.adt.*;
import Model.Exceptions.MyException;
import Model.Expression.*;
import Model.Statement.*;
import Model.Type.*;
import Model.Value.*;

import java.io.IOException;
import java.util.List;

public interface IRepo {
    public void add(ProgramStatement p);
    public ProgramStatement getCurrent();
    public List<ProgramStatement> getAll();
    public void setProgramList(List<ProgramStatement> statements);
    public void logProgramStatementExec(ProgramStatement statement) throws MyException, IOException;
    public void setCurrent(int position);
    public String getLogFilePath();
    public ProgramStatement getProgramStatementById(int index);
}
