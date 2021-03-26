package Model.Value;

import Model.Type.*;

public class StringValue implements IValue{

    String val;

    public StringValue(String v){
        val = v;
    }

    public String getVal(){
        return val;
    }

    @Override
    public IType getType() {
        return new StringType();
    }

    @Override
    public boolean isEqual(Object v) {
        return equals(val);
    }

    @Override
    public IValue copy() {
        return new StringValue(val);
    }

    @Override
    public boolean equals(Object another){
        return (this == another);
    }

    @Override
    public String toString(){
        return val + "";
    }
}

