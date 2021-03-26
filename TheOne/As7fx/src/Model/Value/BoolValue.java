package Model.Value;

import Model.Type.*;

public class BoolValue implements IValue{

    boolean val;
    public BoolValue(boolean v){
        val = v;
    }

    public boolean getVal(){
        return val;
    }

    @Override
    public IType getType() {
        return new BoolType();
    }

    @Override
    public boolean isEqual(Object v) {
        return equals(val);
    }

    @Override
    public boolean equals(Object another){
        return (this == another);
    }

    @Override
    public String toString(){
        return val + "";
    }

    @Override
    public IValue copy(){
        return new BoolValue(val);
    }
}
