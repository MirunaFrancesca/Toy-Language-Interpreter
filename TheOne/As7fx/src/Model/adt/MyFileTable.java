package Model.adt;

import java.util.*;

public class MyFileTable<String,BufferedReader> implements IFileTable<String, BufferedReader> {

    HashMap<String,BufferedReader> dict;

    public MyFileTable(){
        dict = new HashMap<>();
    }

    @Override
    public BufferedReader lookup(Object file) { return dict.get(file); }

    @Override
    public void add(String file, BufferedReader br) {
        this.dict.put(file,br);
    }

    @Override
    public void remove(String file) { this.dict.remove(file); }

    @Override
    public HashMap<String, BufferedReader> getElements() {
         return dict;
    }

    @Override
    public java.lang.String toString(){
        return dict.toString() + "\n";
    }

    @Override
    public ArrayList<ArrayList<java.lang.String>> getElementsStrings() {
        ArrayList<ArrayList<java.lang.String>> elements = new ArrayList<>();
        for (String key : this.dict.keySet()){
            ArrayList<java.lang.String> list = new ArrayList<>();
            list.add(key.toString());
            list.add(dict.get(key).toString());
            elements.add(list);
        }
        return elements;
    }

}

