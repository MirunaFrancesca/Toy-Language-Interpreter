package Model.Type;

import Model.Value.IValue;
import Model.Value.IntValue;

public class IntType implements IType {

    public boolean equals(Object another){
        if(another instanceof IntType)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public IValue defaultValue() {
        return new IntValue(0);
    }
}
