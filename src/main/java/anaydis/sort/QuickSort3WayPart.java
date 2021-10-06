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
        sort(comparator,list,0,list.size()-1);
    }

    private  <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int lo, int hi) {
        if (hi<=lo) return;
        T pivot = list.get(lo);
        int i = lo+1;int p=lo;int q =hi;
        while(i<=q){
            if (less(comparator,list,lo,i))swap(list,i,q--);
            else if (equals(comparator,list,lo,i)) i++;
            else swap(list,p++,i++);
        }
        sort(comparator,list,lo,p-1);
        sort(comparator,list,q+1,hi);
    }

    private <T> void auxSort(List<T> list, int lo, int hi, Comparator<T> comparator) {
// Bentley-McIlroy 3-way partitioning
        int i = lo, j = hi + 1;
        int p = lo, q = hi + 1;
        if (hi <= lo) return;
        T v = list.get(lo);
        while (true) {
            while (less(comparator, list, ++i, lo))
                if (i == hi) break;
            while (less(comparator, list, lo, --j))
                if (j == lo) break;
// pointers cross
            if (i == j && list.get(i).equals(v))
                swap(list, ++p, i);
            if (i >= j) break;
            swap(list, i, j);
            if (list.get(i).equals(v)) swap(list, ++p, i);
            if (list.get(j).equals(v)) swap(list, --q, j);
        }
        i = j + 1;
        for (int k = lo; k <= p; k++)
            swap(list, k, j--);
        for (int k = hi; k >= q; k--)
            swap(list, k, i++);
        auxSort(list, lo, j, comparator);
        auxSort(list, i, hi, comparator);
    }


    private <T> void auxSort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int l, int r){
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
        auxSort(comparator, list, l, j);
        auxSort(comparator, list, i, r);
    }
}
