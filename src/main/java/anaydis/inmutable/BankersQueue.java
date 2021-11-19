package anaydis.inmutable;


import anaydis.immutable.List;
import anaydis.immutable.Queue;
import org.jetbrains.annotations.NotNull;

public class BankersQueue<T> implements Queue<T> {

    private List<T> in;
    private List<T> out;

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
        if(out.isEmpty()){
            List<T> inAux = List.nil();
            List<T> outAux = in.reverse();
            return new Result<T>(outAux.head(), new BankersQueue<T>(inAux, outAux.tail()));
        }
        return new Result<>(out.head(), new BankersQueue<T>(in, out.tail()));
    }

    @Override
    public boolean isEmpty() {
        return in.isEmpty() && out.isEmpty();
    }
}
