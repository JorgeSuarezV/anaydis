package anaydis.sort;

import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("ALL")
public class QuickSort3WayPart extends AbstractQuicksort {

    protected QuickSort3WayPart() {
        super(SorterType.QUICK_THREE_PARTITION);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, 0, list.size()-1);
    }

    private <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int l, int r){
        if (r <= l) return;
        int i = l-1, j = r, p = l-1, q = r, k;
        while (true){
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
        sort(comparator, list, l, j);
        sort(comparator, list, i, r);
    }
}
