package Model.adt;

import java.util.ArrayList;

public interface IStack<TElem> {
    public TElem pop();
    public void push(TElem v);
    public TElem peek();
    public boolean isEmpty();
    public String toStringMenu();
    public Iterable<TElem> getElements();
    public ArrayList<String> getElementsStrings();

}
