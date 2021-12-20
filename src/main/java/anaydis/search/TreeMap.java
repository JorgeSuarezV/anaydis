package anaydis.search;

abstract class TreeMap<K,V> implements Map<K,V> {

    protected int size;
    protected V prevValue = null;

    @Override
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}

