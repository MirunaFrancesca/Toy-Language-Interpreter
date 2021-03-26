package Model.adt;
import Model.Statement.*;
import Model.Type.*;
import Model.Value.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IDictionary<TElem,KValue> {
    public KValue lookup(TElem v);
    public boolean isDefined(TElem v);
    public void update(TElem v, KValue k);
    public boolean isEmpty();
    public Map<TElem,KValue> getContent();
    void setContent(Map<TElem, KValue> newContent);
    ArrayList<ArrayList<String>> getElementsStrings();
}
