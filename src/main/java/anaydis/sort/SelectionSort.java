package anaydis.sort;

import anaydis.sort.gui.SorterListener;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class SelectionSort extends AbstractSorter{

    public SelectionSort() {
        super(SorterType.SELECTION);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            int min = i;
            for (int j = i + 1; j < list.size(); j++) {
                notifyc(min, j);
                if (greater(comparator, list, min, j)){
                    min = j;
                }
            }
            if(i != min) {
                swap(list, i, min);
                notifys(i,min);
            }
        }
    }

    @Override
    public @NotNull SorterType getType() {
        return SorterType.SELECTION;
    }

    @Override
    public void addSorterListener(@NotNull SorterListener listener) {

    }

    @Override
    public void removeSorterListener(@NotNull SorterListener listener) {

    }
}
