package Model.adt;


public interface ILockTable<TElem, KValue>{

    public Integer getLockAddress();
    public void put(TElem location, KValue value);
    public boolean isDefined(TElem key);
    public KValue get(TElem key);
}
