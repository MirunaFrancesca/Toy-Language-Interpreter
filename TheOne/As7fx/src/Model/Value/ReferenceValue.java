package Model.Value;

import Model.Type.*;

public class ReferenceValue implements IValue{

    int heap_adress;
    IType locationType;
    public ReferenceValue(int heap_adress, IType location){
        this.heap_adress = heap_adress;
        this.locationType = location;
    }

    public int getAddress(){
        return this.heap_adress;
    }
    public IType getLocationType(){ return this.locationType; }

    @Override
    public IType getType() {
        return new ReferenceType(locationType);
    }

    @Override
    public boolean isEqual(Object v) {
        return false;
    }

    @Override
    public IValue copy() {
        return new ReferenceValue(heap_adress,locationType);
    }

    @Override
    public String toString(){
        return  "(" + heap_adress + "," + locationType.toString() + ")";
    }
}

