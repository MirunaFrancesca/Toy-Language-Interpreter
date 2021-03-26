package Model.Type;

import Model.Value.IValue;

public interface IType {
    public String toString();
    public IValue defaultValue();
    public boolean equals(Object another);
}

