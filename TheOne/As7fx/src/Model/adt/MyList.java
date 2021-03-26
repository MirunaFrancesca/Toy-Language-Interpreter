package Model.adt;
import Model.Exceptions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyList<TElem> implements IList<TElem> {
    List<TElem> list;

    public MyList(){
        list = new ArrayList<TElem>();
    }

    @Override
    public String toString(){
        String to_return = "";
        for(TElem i: list){
            to_return += i.toString();
            to_return += "\n";
        }
        return to_return;
    }

    @Override
    public void add(TElem toPrint) {
        list.add(toPrint);
    }

    @Override
    public ArrayList<String> getElementsStrings() {
        ArrayList<String> elements = new ArrayList<>();
        for (var element: this.list) {
            elements.add(element.toString());
        }
        return elements;
    }

    @Override
    public java.util.List<TElem> getContent() {
        return list;
    }

    @Override
    public void setContent(java.util.List<TElem> newContent) {
        list = new ArrayList<TElem>(newContent);
    }
}
