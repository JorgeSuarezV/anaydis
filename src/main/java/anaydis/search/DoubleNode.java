package anaydis.search;

public class DoubleNode<K, V> {
    private final K key;
    private V value;
    private DoubleNode<K, V> left;
    private DoubleNode<K, V> right;

    public DoubleNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public DoubleNode(K key, V value, DoubleNode<K, V> left, DoubleNode<K, V> right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public K getKey() {
        return key;
    }

    public DoubleNode<K, V> getLeft() {
        return left;
    }

    public DoubleNode<K, V> getRight() {
        return right;
    }

    public DoubleNode<K, V> getCopy() {
        return new DoubleNode<>(key, value, left, right);
    }

    public void setLeft(DoubleNode<K, V> left) {
        this.left = left;
    }

    public void setRight(DoubleNode<K, V> right) {
        this.right = right;
    }
}