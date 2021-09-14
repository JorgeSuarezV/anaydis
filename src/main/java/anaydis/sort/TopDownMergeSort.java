package anaydis.sort;

import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

public class TopDownMergeSort extends AbstractMergeSort {

    protected TopDownMergeSort() {
        super(SorterType.MERGE_TOP_DOWN);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, auxProvide(list), 0, list.size() - 1);
    }

    private <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, @NotNull List<T> aux, int l, int r) {
        if (r <= l) return;
        int m = (r+l)/2;
        sort(comparator, list, aux, l, m);
        sort(comparator, list, aux, m+1, r);
        merge(comparator, list, aux, l , m, r);
    }

}