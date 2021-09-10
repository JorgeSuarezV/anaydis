package anaydis.sort;

import anaydis.sort.gui.SorterListener;
import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

public class QuickSortCut extends AbstractQuicksort {

    final InsertionSort insertionSort = new InsertionSort();

    protected QuickSortCut() {
        super(SorterType.QUICK_CUT);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sortByM(comparator, list, 6, 0, list.size()-1);
    }

    public <T> void sortByM(@NotNull Comparator<T> comparator, @NotNull List<T> list, int M, int min, int max) {
        if (max - min <= M) {
            insertionSort.sort(comparator, list, min, max);
            return;
        }
        int i = partition(comparator, list, min, max);
        sortByM(comparator, list, M, min, i-1);
        sortByM(comparator, list, M,i+1, max);
    }
    @Override
    public void addSorterListener(@NotNull SorterListener listener) {
        insertionSort.addSorterListener(listener);
        super.addSorterListener(listener);
    }


    public void removeSorterListener(@NotNull final SorterListener listener){
        insertionSort.removeSorterListener(listener);
        super.removeSorterListener(listener);
    }
}
