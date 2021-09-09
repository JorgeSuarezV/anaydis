package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class QuickSort3WayPart extends AbstractSorter{

    public QuickSort3WayPart() {
        super(SorterType.QUICK_THREE_PARTITION);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        auxSort(comparator, list, 0 , list.size()-1);
    }

    public <T> void auxSort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int l, int r){
        if (r <= l) return;
        int i = l-1, j = r, p = l-1, q = r, k;
        for (;;)
        {
            while (less(comparator, list, ++i, r)) ;
            while (less(comparator, list, r, --j)) if (j == l) break;
            if (i >= j) break;
            swap(list, i, j);
            if (equals(comparator, list, i, r)) {
                p++;
                swap(list, p, i);
            }
            if (equals(comparator, list, r, j)) {
                q--;
                swap(list, q, j);
            }
        }
        swap(list, i, r);
        j = i-1;
        i = i+1;
        for (k = l ; k <= p; k++,j--) swap(list, k, j);
        for (k = r-1; k >= q; k--,i++) swap(list, k, i);
        auxSort(comparator, list, l, j);
        auxSort(comparator, list, i, r);
    }

    @Override
    public <T> int partition(@NotNull Comparator<T> comparator, @NotNull List<T> list, int min, int max){
        int i = min - 1;
        int j = max;
        int p = min;
        int q = max;
        while(true) {
            while(less(comparator, list, ++i, max) || equals(comparator, list, i, max)) {
                if (equals(comparator, list, i, max)) swap(list, i, p++);
                if (i == max) break;
            }
            while(less(comparator, list, max, --j) || equals(comparator, list, j, max)) {
                if (equals(comparator, list, j, max)) swap(list, j, q++);
                if (j == min) break;
            }
            if (i >= j) break;
            swap(list, i, j);
        }
        swap(list, i, max);
        return i;
    }
}
