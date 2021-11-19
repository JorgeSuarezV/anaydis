package anaydis.immutable;

public class DoubleNode <K, V> {
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

    public DoubleNode<K, V> getCopy(DoubleNode<K, V> node) {
        return new DoubleNode<>(node.getKey(), node.getValue(), node.left, node.right);
    }
}