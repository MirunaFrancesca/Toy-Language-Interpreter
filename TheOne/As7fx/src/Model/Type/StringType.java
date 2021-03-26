package Model.Type;

import Model.Value.IValue;
import Model.Value.StringValue;

public class StringType implements IType{
    public boolean equals(Object another){
        if(another instanceof StringType)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "String";
    }

    @Override
    public IValue defaultValue() {
        return new StringValue("");
    }
}

