package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IntegerDataSetGenerator implements DataSetGenerator<Integer>{

    List<Integer> ints;

    public IntegerDataSetGenerator() {
        ints = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            ints.add((int) ((Math.random() * (1000 - -1000)) + -1000));
        }
    }



    @Override
    public @NotNull List<Integer> createAscending(int length) {
        if (length > 10000) throw new IllegalArgumentException("Watch out! StringDataSetGenerator can generate up to " + 1000 + " values only.");
        ArrayList asc = new ArrayList(ints.subList(0,length));
        asc.sort(getComparator());
        return asc;
    }

    @Override
    public @NotNull List<Integer> createDescending(int length) {
        if (length > 10000) throw new IllegalArgumentException("Watch out! StringDataSetGenerator can generate up to " + 1000 + " values only.");
        ArrayList des = new ArrayList(ints.subList(0,length));
        des.sort(getComparator().reversed());
        return des;
    }

    @Override
    public @NotNull List<Integer> createRandom(int length) {
        if (length > 10000) throw new IllegalArgumentException("Watch out! StringDataSetGenerator can generate up to " + 1000 + " values only.");
        return new ArrayList<>(ints.subList(0,length));
    }

    @Override
    public @NotNull Comparator<Integer> getComparator() {
        return Comparator.naturalOrder();
    }

}
