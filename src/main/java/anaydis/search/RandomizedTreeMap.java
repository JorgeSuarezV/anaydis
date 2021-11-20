package anaydis.search;

import anaydis.immutable.DoubleNode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class RandomizedTreeMap<K,V> extends TreeMap<K, V> {


    private DoubleNode<K, V> head;
    private final Comparator<K> comparator;

    public RandomizedTreeMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    @Override
    public boolean containsKey(@NotNull K key) {
        return find(head, key) != null;
    }

    @Override
    public V get(@NotNull K key) {
        final DoubleNode<K, V> result = find(head, key);
        return result == null ? null : result.getValue();
    }

    @Override
    public V put(@NotNull K key, V value) {
        if(Math.random() < 0.5) head = putRoot(head,key,value);
        else head = putAux(head, key, value);
        final V result = prevValue;
        prevValue = null;
        return result;
    }



    private DoubleNode<K,V> rootPut(DoubleNode<K,V> node, @NotNull K key, V value) {

    }

    private DoubleNode<K,V> rotateRight(DoubleNode<K, V> node) {
        DoubleNode<K,V> result = node.getLeft().getCopy();
        node.setLeft(result.getRight());
        result.setRight(node);
        return result;
    }

    private DoubleNode<K,V> rotateLeft(DoubleNode<K, V> node) {
        DoubleNode<K, V> result = node.getRight().getCopy();
        node.setRight(result.getLeft());
        result.setLeft(node);
        return result;
    }

    @Override
    public void clear() {
        size = 0;
        head = null;
    }

    @Override
    public Iterator<K> keys() {
        ArrayList<K> keys = new ArrayList<>();
        keysInOrder(head, keys);
        return keys.iterator();
    }

    private void keysInOrder(DoubleNode<K, V> node, @NotNull ArrayList<K> result){
        if(node != null) {
            keysInOrder(node.getLeft(), result);
            result.add(node.getKey());
            keysInOrder(node.getRight(), result);
        }
    }

    private DoubleNode<K,V> find(DoubleNode<K, V> node, @NotNull K key){
        if(node == null) return null;
        int cmp = comparator.compare(key, node.getKey());
        if(cmp == 0) return node;
        if(cmp < 0) return find(node.getLeft(), key);
        return find(node.getRight(), key);
    }


}
