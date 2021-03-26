package Model.adt;

import Model.Value.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MyHeap implements IHeap{

    Map<Integer,IValue> heap;

    AtomicInteger new_free_location;

    public MyHeap(){
        this.heap = new HashMap<Integer, IValue>();
        this.new_free_location = new AtomicInteger(0);
    }

    @Override
    public int allocate(IValue value){
        int new_location = this.new_free_location.incrementAndGet();
        heap.put(new_location, value);
        return new_location;
    }

    @Override
    public IValue getValue(int address) {
        return heap.get(address);
    }

    @Override
    public boolean isDefined(int key) {
        return (heap.get(key) != null);
    }

    @Override
    public void update(int address, IValue value) { heap.put(address,value); }

    @Override
    public void setContent(Map<Integer, IValue> newContent) {
        heap = new HashMap<>();
        heap.putAll(newContent);
    }

    @Override
    public Map<Integer, IValue> getContent() {
        return heap;
    }

    @Override
    public String to_string() {
        String to_return="{";
        for (Map.Entry<Integer,IValue> el:heap.entrySet()) {
            to_return+="("+el.getKey()+"->"+el.getValue()+") ";
        }
        to_return+="}";
        return to_return;
    }

    @Override
    public ArrayList<ArrayList<String>> getElementsStrings() {
        ArrayList<ArrayList<String>> elements = new ArrayList<>();
        for (Integer key : this.heap.keySet()){
            ArrayList<String> list = new ArrayList<>();
            list.add(key.toString());
            list.add(heap.get(key).toString());
            elements.add(list);
        }
        return elements;
    }
}
