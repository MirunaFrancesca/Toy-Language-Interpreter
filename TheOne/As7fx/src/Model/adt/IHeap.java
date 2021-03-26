package Model.adt;

import Model.Value.IValue;

import java.util.ArrayList;
import java.util.Map;

public interface IHeap {
    public int allocate(IValue value);
    public IValue getValue(int address);
    public boolean isDefined(int key);
    void update(int address, IValue value);
    void setContent(Map<Integer, IValue> newContent);
    public Map<Integer,IValue> getContent();
    public String to_string();
    public ArrayList<ArrayList<String>> getElementsStrings();
}

