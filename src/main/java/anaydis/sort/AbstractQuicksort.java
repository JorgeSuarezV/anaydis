package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractQuicksort extends AbstractSorter {

    protected AbstractQuicksort(SorterType type) {
        super(type);
    }

    protected  <T> int partition(@NotNull Comparator<T> comparator, @NotNull List<T> list, int min, int max){
        int i = min - 1;
        int j = max;
        while(true) {
            while(less(comparator, list, ++i, max))
                if (i == max) break;
            while(less(comparator, list, max, --j))
                if (j == min) break;
            if (i >= j) break;
            swap(list, i, j);
        }
        swap(list, i, max);
        return i;
    }

}
