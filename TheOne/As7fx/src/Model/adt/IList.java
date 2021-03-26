package Model.adt;
import Model.Statement.*;
import Model.Type.*;
import Model.Value.*;

import java.util.ArrayList;

public interface IList<TElem> {
    public void add(TElem toPrint);
    public ArrayList<String> getElementsStrings();
    java.util.List<TElem> getContent();
    void setContent(java.util.List<TElem> newContent);
}
