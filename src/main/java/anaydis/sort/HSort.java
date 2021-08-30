package anaydis.sort;

import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

public class HSort extends AbstractSorter{

    private Comparator<Object> comparator;
    private List<Object> list;
    private int h;

    public HSort() {
        super(SorterType.H);
    }

    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list){
        sort(comparator, list, 1);
    }

    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int h) {
        for (int i = h; i < list.size(); i++) {
            int j = i;
            while (j >= h && less(comparator, list, i, j - h)) {
                list.set(j, list.get(j - h));
                j -= h;
            }
            list.set(j, list.get(i));
        }
    }
}