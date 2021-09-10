package anaydis.sort;

import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

public class QuickSort extends AbstractQuicksort {

    protected QuickSort() {
        super(SorterType.QUICK);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        auxSort(comparator, list, 0 , list.size()-1);
    }

    private <T> void auxSort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int min, int max){
        if (max <= min) return;
        int i = partition(comparator, list, min, max);
        auxSort(comparator, list, min, i-1);
        auxSort(comparator, list, i+1, max);
    }
}
