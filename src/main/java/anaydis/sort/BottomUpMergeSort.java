package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class BottomUpMergeSort extends AbstractSorter{

    public BottomUpMergeSort() {
        super(SorterType.MERGE_BOTTOM_UP);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        auxSort(comparator, list, 0, list.size()-1);
    }

    public <T> void auxSort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int l, int r) {
        if (r <= l) return;
        for (int m = 1; m <= r-l; m = m+m) {
            for (int i = l; i <= r - m; i += m + m) {
                merge(comparator, list, i, i + m - 1, Math.min(i + m + m - 1, r));
            }
        }
    }
}
