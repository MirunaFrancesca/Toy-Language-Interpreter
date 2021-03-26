package Model.adt;

import Model.Type.IType;

import java.util.*;

public class MyDictionary<TElem,KValue> implements IDictionary<TElem,KValue>{

    HashMap<TElem,KValue> dict;

    public MyDictionary(){
        dict = new HashMap<TElem,KValue>();
    }

    @Override
    public boolean isEmpty(){
        return dict.isEmpty();
    }

    @Override
    public Map<TElem, KValue> getContent() {
        return (Map<TElem, KValue>) dict;
    }

    @Override
    public void setContent(Map<TElem, KValue> newContent) {
        dict = new HashMap<>();
        dict.putAll(newContent);
    }

    @Override
    public ArrayList<ArrayList<String>> getElementsStrings() {
        ArrayList<ArrayList<String>> elements = new ArrayList<>();
        for (TElem key : this.dict.keySet()){
            ArrayList<String> list = new ArrayList<>();
            list.add(key.toString());
            list.add(dict.get(key).toString());
            elements.add(list);
        }
        return elements;
    }

    @Override
    public KValue lookup(TElem v) { return dict.get(v); }

    @Override
    public boolean isDefined(TElem v) {
        if (dict.get(v) == null)
            return false;
        return true;
    }

    @Override
    public void update(TElem v, KValue k) {
        dict.put(v,k);
    }

    @Override
    public String toString(){
        return dict.toString();
    }

    @Override
    public MyDictionary<String, IType> clone() {
        return this.clone();
    }

}
