package anaydis.sort;

import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

public class BottomUpMergeSort extends AbstractMergeSort{

    protected BottomUpMergeSort() {
        super(SorterType.MERGE_BOTTOM_UP);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, auxProvide(list),  list.size()-1);
    }

    private <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, @NotNull List<T> aux, int r) {
        if (r <= 0) return;
        for (int m = 1; m <= r; m = m+m) {
            for (int i = 0; i <= r - m; i += m + m) {
                merge(comparator, list, aux, i, i + m - 1, Math.min(i + m + m - 1, r));
            }
        }
    }
}
