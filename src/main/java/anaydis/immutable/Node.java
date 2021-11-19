package anaydis.immutable;


import org.jetbrains.annotations.NotNull;

public class Node<T> implements List<T> {

    T head;
    List<T> tail;

    public Node(T head, List<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return head;
    }

    @Override
    public @NotNull List<T> tail() {
        return tail;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public @NotNull List<T> reverse() {
        return reverse(new Node<>(head, tail), List.nil());
    }

    private List<T> reverse(List<T> current, List<T> target){
        if(current.isEmpty()) return target;
        return reverse(current.tail(), List.cons(current.head(), target));
    }
}
