package anaydis.sort;

import anaydis.sort.gui.SorterListener;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class QuickSortMedOf3 extends AbstractSorter{

    InsertionSort insertionSort = new InsertionSort();

    public QuickSortMedOf3() {
        super(SorterType.QUICK_MED_OF_THREE);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        quicksortMedOf3(comparator, list, 9, 0, list.size()-1);
    }
    private <T> void quicksortMedOf3(Comparator<T> comparator, List<T> list, int m, int min, int max)
    {
        if (max-min <= m){
            insertionSort.sortForQuick(comparator, list, min, max);
            return;
        }
        swap(list, (min+max)/2, max-1);
        if (greater(comparator, list, min, max-1)) swap(list, min, max-1);
        if (greater(comparator, list, min, max)) swap(list, min, max);
        if (greater(comparator, list, max-1, max)) swap(list, max-1, max);
        int i = partition(comparator, list, min+1, max-1);
        quicksortMedOf3(comparator, list, m, min, i-1);
        quicksortMedOf3(comparator, list, m, i+1, max);
    }
    @Override
    public void addSorterListener(@NotNull final SorterListener listener){
        insertionSort.addSorterListener(listener);
        getListeners().add(listener);
    }


    public void removeSorterListener(@NotNull final SorterListener listener){
        insertionSort.removeSorterListener(listener);
        getListeners().remove(listener);
    }
}
