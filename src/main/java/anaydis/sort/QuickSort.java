package anaydis.sort;

import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

public class QuickSort extends AbstractSorter{

    public QuickSort() {
        super(SorterType.QUICK);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        auxSort(comparator, list, 0 , list.size()-1);
    }

    public <T> void auxSort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int min, int max){
        if (max <= min) return;
        int i = partition(comparator, list, min, max);
        auxSort(comparator, list, min, i-1);
        auxSort(comparator, list, i+1, max);
    }

    public <T> int partition(@NotNull Comparator<T> comparator, @NotNull List<T> list, int min, int max){
        int i = min - 1;
        int j = max;
        while(true) {
            while(less(comparator, list, ++i, max))
                if (i == max) break;
            while(less(comparator, list, max, --j))
                if (j == min) break;
            if (i >= j) break;
            swap(list, i, j);
        }
        swap(list, i, max);
        return i;
    }
}
