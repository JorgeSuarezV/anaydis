package anaydis.immutable;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class BinaryTree<K, V> implements Map<K, V> {

    private final DoubleNode<K,V> head;
    private int size = 0;
    private final Comparator<K> comparator;

    public BinaryTree(Comparator<K> comparator) {
        head = null;
        this.comparator = comparator;
    }

    private BinaryTree(Comparator<K> comparator, DoubleNode<K,V> head, int size) {
        this.comparator = comparator;
        this.head = head;
        this.size = size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(@NotNull K key) {
        return find(head, key) != null;
    }

    @Override
    public V get(@NotNull K key) {
        DoubleNode<K, V> result = find(head, key);
        if (result == null) return null;
        return result.getValue();
    }

    @Override
    public Map<K, V> put(@NotNull K key, V value) {
        PutResult<K,V> result = put(head, key, value);
        return new BinaryTree<>(comparator, result.node, result.inserted ? size + 1 : size);
    }

    private PutResult<K,V> put(DoubleNode<K,V> node, K key, V value) {
        if(node == null){
            return new PutResult<>(new DoubleNode<>(key, value), true);
        }
        else {
            int cmp = comparator.compare(key, node.getKey());
            PutResult<K,V> result;
            if(cmp < 0) {
                PutResult<K,V> leftR = put(node.getLeft(), key, value);
                result = new PutResult<>(new DoubleNode<>(node.getKey(), node.getValue(), leftR.node, node.getRight()), leftR.inserted);
            }
            else if(cmp > 0) {
                PutResult<K,V> rightR = put(node.getRight(), key, value);
                result = new PutResult<>(new DoubleNode<>(node.getKey(), node.getValue(), node.getLeft(), rightR.node), rightR.inserted);
            }
            else {
                DoubleNode<K,V> copyNode = node.getCopy();
                copyNode.setValue(value);
                result = new PutResult<>(copyNode, false);
            }
            return result;
        }

    }

    @Override
    public Iterator<K> keys() {
        ArrayList<K> keys = new ArrayList<>();
        getKeys(head, keys);
        return keys.iterator();
    }

    private void getKeys(DoubleNode<K,V> node, @NotNull ArrayList<K> result){
        if(node != null) {
            getKeys(node.getLeft(), result);
            result.add(node.getKey());
            getKeys(node.getRight(), result);
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
