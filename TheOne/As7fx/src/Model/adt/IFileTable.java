package Model.adt;

import Model.Value.IValue;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public interface IFileTable<String,BufferedReader>  {
    public BufferedReader lookup(Object file);
    public void add(String file, BufferedReader br);
    public void remove(String file);
    public HashMap<String,BufferedReader> getElements();
    public ArrayList<ArrayList<java.lang.String>> getElementsStrings();
}

