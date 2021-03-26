package Model.Statement;
import Model.adt.IStack;
import Model.adt.MyDictionary;
import Model.ProgramStatement;
import Model.Exceptions.MyException;
import Model.Type.IType;

public class CompoundStatement implements IStatement {

    IStatement first;
    IStatement second;

    public CompoundStatement(IStatement first, IStatement second){
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "( " + first.toString() + "; " + second.toString() + " )";
    }

    @Override
    public MyDictionary<String, IType> typecheck(MyDictionary<String, IType> typeEnv) throws MyException {
        return second.typecheck(first.typecheck(typeEnv));
    }

    @Override
    public ProgramStatement execute(ProgramStatement state)throws Exception {
        IStack<IStatement> stack = state.getExeStack();
        stack.push(second);
        stack.push(first);
        return null;

    }
}
