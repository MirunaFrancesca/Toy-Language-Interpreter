package Model.Value;

import Model.Type.*;

public interface IValue {
    public IType getType();
    public boolean isEqual(Object v);
    IValue copy();
}

