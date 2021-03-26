package Model.Value;

import Model.Type.IType;
import Model.Type.IntType;

public class IntValue implements IValue{

    int val;
    public IntValue(int v){
        val = v;
    }

    public int getVal(){
        return val;
    }

    @Override
    public IType getType() {
        return new IntType();
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
        return new IntValue(val);
    }
}
