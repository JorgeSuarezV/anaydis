package anaydis.sort.correctnessTests;

import anaydis.sort.DataSetGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class IntegerDataSetGenerator implements DataSetGenerator<Integer> {

    List<Integer> ints;

    public void intsGenerator(int length){
        ints = new ArrayList<>();
        Random generator = new Random(15);
        for (int i = 0; i < length; i++) {
            ints.add(generator.nextInt());
        }
    }

    @Override
    public @NotNull List<Integer> createAscending(int length) {
        intsGenerator(length);
        ArrayList<Integer> asc = new ArrayList<>(ints.subList(0,length));
        asc.sort(getComparator());
        return asc;
    }

    @Override
    public @NotNull List<Integer> createDescending(int length) {
        intsGenerator(length);
        ArrayList<Integer> des = new ArrayList<>(ints.subList(0,length));
        des.sort(getComparator().reversed());
        return des;
    }

    @Override
    public @NotNull List<Integer> createRandom(int length) {
        intsGenerator(length);
        return new ArrayList<>(ints.subList(0,length));
    }

    @Override
    public @NotNull Comparator<Integer> getComparator() {
        return Comparator.naturalOrder();
    }

}
