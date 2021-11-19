package anaydis.immutable;


import org.jetbrains.annotations.NotNull;

public class BankersQueue<T> implements Queue<T> {

    private final List<T> in;
    private final List<T> out;

    public BankersQueue(List<T> in, List<T> out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public @NotNull Queue<T> enqueue(@NotNull T value) {
        return new BankersQueue<>(in, List.cons(value, out));
    }

    @Override
    public @NotNull Result<T> dequeue() {
        if(in.isEmpty() && !out.isEmpty()){
            List<T> inAux = List.nil();
            List<T> outAux = in.reverse();
            return new Result<>(outAux.head(), new BankersQueue<>(inAux, outAux.tail()));
        }
        return new Result<>(out.head(), new BankersQueue<>(in, out.tail()));
    }

    @Override
    public boolean isEmpty() {
        return in.isEmpty() && out.isEmpty();
    }
}
