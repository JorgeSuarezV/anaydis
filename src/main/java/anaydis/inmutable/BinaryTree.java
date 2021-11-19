package anaydis.inmutable;


import anaydis.immutable.Map;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class BinaryTree implements Map {

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return Map.super.isEmpty();
    }

    @Override
    public boolean containsKey(@NotNull Object key) {
        return false;
    }

    @Override
    public Object get(@NotNull Object key) {
        return null;
    }

    @Override
    public Map put(@NotNull Object key, Object value) {
        return null;
    }

    @Override
    public Iterator keys() {
        return null;
    }
}
