package anaydis.search;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class RandomizedTreeMap<K,V> extends TreeMap<K, V>{

    private DoubleNode<K,V> head;
    private final Comparator<K> comparator;

    public RandomizedTreeMap(Comparator<K> comparator) {
        initRandTreeMap();
        this.comparator = comparator;
    }
    private void initRandTreeMap(){
        this.size = 0;
        this.head = null;
    }

    @Override
    public boolean containsKey(@NotNull K key) {
        return find(head,key) != null;
    }

    @Override
    public V get(@NotNull K key) {
        DoubleNode<K,V> doubleNode = find(head,key);
        if(doubleNode == null) return null;
        return doubleNode.getValue();
    }
    private DoubleNode<K,V> find(DoubleNode<K,V> doubleNode, K key){
        if(doubleNode == null) return null;
        int n = comparator.compare(key, doubleNode.getKey());
        if(n == 0){
            return doubleNode;
        }else if(n < 0){
            return find(doubleNode.getLeft(), key);
        }else{
            return find(doubleNode.getRight(),key);
        }
    }

    @Override
    public V put(@NotNull K key, V value) {
        if(Math.random() < 0.5) head = putRoot(head,key,value);
        else head = putAux(head, key, value);
        final V result = prevValue;
        prevValue = null;
        return result;
    }

    private DoubleNode<K,V> putAux(DoubleNode<K,V> node, K key, V value) {
        if(node == null){
            size++;
            return new DoubleNode<>(key,value);
        }
        else{
            int n = comparator.compare(key,node.getKey());
            if (n < 0) node.setLeft(putAux(node.getLeft(),key,value));
            else if(n > 0) node.setRight(putAux(node.getRight(),key,value));
            else{
                prevValue = node.getValue();
                node.setValue(value);
            }
            return node;
        }
    }

    private DoubleNode<K,V> putRoot(DoubleNode<K,V> doubleNode, K key, V value) {
        if (doubleNode == null) {
            size++;
            return new DoubleNode<>(key, value);
        }else{
            int n = comparator.compare(key, doubleNode.getKey());
            if (n < 0){
                doubleNode.setLeft(putRoot(doubleNode.getLeft(), key, value));
                return rotateRight(doubleNode);
            } else if (n > 0) {
                doubleNode.setRight(putRoot(doubleNode.getRight(), key, value));
                return rotateLeft(doubleNode);
            }else{
                prevValue = doubleNode.getValue();
                doubleNode.setValue(value);
                return doubleNode;
            }
        }
    }

    private DoubleNode<K,V> rotateLeft(DoubleNode<K,V> doubleNode){
        DoubleNode<K,V> result = doubleNode.getRight().getCopy();
        doubleNode.setRight(result.getLeft());
        result.setLeft(doubleNode);
        return result;
    }

    private DoubleNode<K,V> rotateRight(DoubleNode<K,V> doubleNode){
        DoubleNode<K,V> result = doubleNode.getLeft().getCopy();
        doubleNode.setLeft(result.getRight());
        result.setRight(doubleNode);
        return result;
    }

    @Override
    public void clear() {
        initRandTreeMap();
    }

    @Override
    public Iterator<K> keys() {
        List<K> list = new ArrayList<>();
        keysAux(head, list);
        return list.iterator();
    }

    private void keysAux(DoubleNode<K,V> node, List<K> keys) {
        if(node != null){
            keysAux(node.getLeft(),keys);
            keys.add(node.getKey());
            keysAux(node.getRight(),keys);
        }
    }
}