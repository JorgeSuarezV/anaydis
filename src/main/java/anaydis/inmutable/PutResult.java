package anaydis.inmutable;

public class PutResult<K, V> {
    DoubleNode<K,V> node;
    boolean inserted;

    PutResult(DoubleNode<K, V> node, boolean inserted) {
        this.node = node;
        this.inserted = inserted;
    }
}

