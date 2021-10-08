package anaydis.search;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ArrayMap<K, V> implements Map<K, V> {

    private final Comparator<K> comparator;
    private final List<K> keys;
    private final List<V> values;
    private int size = 0;

    public ArrayMap(Comparator<K> comparator) {
        this.comparator = comparator;
        keys = fillK(20);
        values = fillV(20);
    }

    public ArrayMap(Comparator<K> comparator, int max) {
        this.comparator = comparator;
        keys = fillK(max);
        values = fillV(max);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return Map.super.isEmpty();
    }

    @Override
    public boolean containsKey(@NotNull K key) {
        for (int i = 0; i <= size; i++) {
            if (key.equals(keys.get(i))) return true;
        }
        return false;
    }

    @Override
    public V get(@NotNull K key) {
        int index = find(key, 0, size-1);
        if (index < 0) return null;
        return values.get(index);
    }

    @Override
    public V put(@NotNull K key, V value) {
        int index = find(key, 0, size-1);
        V toReturn = null;
        if(index < 0) {
            index = (-index) -1;
            for (int i = size + 1; i > index; i--) {
                keys.set(i, keys.get(i - 1));
                values.set(i, values.get(i - 1));
            }
            size++;
            keys.set(index, key);
        } else {
            toReturn = values.get(index);
        }
        values.set(index, value);
        return toReturn;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Iterator<K> keys() {
        return keys.iterator();
    }

    private @NotNull List<K> fillK(int max){
        List<K> list = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            list.add(null);
        }
        return list;
    }

    private @NotNull List<V> fillV(int max){
        List<V> list = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            list.add(null);
        }
        return list;
    }

    private int find(K key, int low,int high) {
        if(low > high) return - (low + 1);
        int middle = (low + high) / 2;
        int cmp = comparator.compare(key, keys.get(middle));
        if(cmp == 0) return middle;
        if(cmp < 0) return find(key, low, middle - 1);
        else return find(key, middle + 1, high);
    }
}