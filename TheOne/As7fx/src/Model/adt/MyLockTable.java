package Model.adt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLockTable<TElem, KValue> implements ILockTable<TElem, KValue>{
    HashMap<TElem, KValue> dict;
    AtomicInteger address;
    private Lock lock = new ReentrantLock();

    public MyLockTable(){
        dict = new HashMap<>();
        address = new AtomicInteger(0);
    }

    @Override
    public Integer getLockAddress(){
        return address.incrementAndGet();
    }

    @Override
    public void put(TElem location, KValue value) {
        lock.lock();
        dict.put(location, value);
        lock.unlock();
    }

    @Override
    public boolean isDefined(TElem key) {
        lock.lock();
        Boolean b = dict.containsKey(key);
        lock.unlock();
        return b;
    }

    @Override
    public KValue get(TElem key) {
        lock.lock();
        KValue v = dict.get(key);
        lock.unlock();
        return v;
    }



}
