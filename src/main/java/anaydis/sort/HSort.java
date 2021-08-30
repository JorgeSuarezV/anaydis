package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class HSort extends AbstractSorter{
    public HSort() {
        super(SorterType.H);
    }

    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list){
        sort(comparator, list, 1);
    }

    public <T> void sort(Comparator<T> comparator, List<T> list, int h) {
        auxSort(comparator, list, h);
    }

    public <T> void auxSort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int h){
        for (int i = 1; i <= h; i++) {
            for (int k = 0; k < list.size()/h; k++) {
                for (int j = i-1; j < list.size(); j += h) {
                    if (j >= list.size()-h) continue;
                    if (less(comparator, list, j+h, j)) {
                        swap(list, j+h, j);
                    }else {
                        continue;
                    }
                }
            }
        }
    }
}