package anaydis.search;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RWayTrieMap<V> extends TreeMap<String,V>{

    private Node<V> head = null;

    private static class Node<V> {
        private V value;
        private final List<Node<V>> next= new ArrayList<>(256);

        private Node(V value) {
            this.value = value;
            for (int i = 0; i < 256; i++) {
                next.add(null);
            }
        }
    }

    public RWayTrieMap() {
        initialize();
    }

    private void initialize() {
        this.size = 0;
        this.head = null;
    }

    @Override
    public  boolean containsKey(@NotNull String key) {
        return find(head, key,0) != null;
    }

    @Override
    public V get (@NotNull String key) {
        final Node<V> toReturn = find(head, key, 0);
        return toReturn == null ? null : toReturn.value;
    }

    @Override
    public V put(@NotNull String word, V value){
        head = putAux(head, word, value, 0);
        return prevValue;
    }

    private Node<V> putAux(Node<V> node, String word, V value, int lvl) {
        if(node == null){
            Node<V> toReturn = new Node<>(null);
            if(lvl < word.length()){
                int next = word.charAt(lvl);
                toReturn.next.set(next, putAux(toReturn.next.get(next), word, value, lvl+1));
            }else{
                size++;
                prevValue = null;
                toReturn.value = value;
            }
            return toReturn;
        }else if(lvl == word.length()){
            prevValue = node.value;
            node.value = value;
            return node;
        }else{
            int next = word.charAt(lvl);
            node.next.set(next, putAux(node.next.get(next), word, value, lvl+1));
            return node;
        }
    }

    private Node<V> find(Node<V> node, String key, int lvl) {
        if (node == null) return null;
        if (lvl == key.length()) return node;
        int next = key.charAt(lvl);
        return find(node.next.get(next), key, lvl+1);
    }

    @Override
    public void clear() {
        initialize();
    }

    @Override
    public Iterator<String> keys() {
        if(head == null) return null;
        ArrayList<String> list = new ArrayList<>();
        keysAux(head,"",list);
        return list.iterator();
    }

    private void keysAux(Node<V> node, String actualKey, List<String> keys){
        List<Node<V>> arrayList = node.next;
        for (int i = 0; i < arrayList.size()-1; i++) {
            if(arrayList.get(i) != null){
                Node<V> actualNode = arrayList.get(i);
                String nodeKey = actualKey + (char) i;
                if(actualNode.value != null) keys.add(nodeKey);
                keysAux(actualNode,nodeKey,keys);
            }
        }
    }
}