package anaydis.sort;

import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

public class TopDownMergeSort extends AbstractSorter {

    public TopDownMergeSort() {
        super(SorterType.MERGE_TOP_DOWN);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        topDownSort(comparator, list, 0, list.size() - 1);
    }

    public <T> void topDownSort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int l, int r) {
        if (r <= l) return;
        int m = (r+l)/2;
        topDownSort(comparator, list, l, m);
        topDownSort(comparator, list, m+1, r);
        merge(comparator, list,l , m, r);
    }

}