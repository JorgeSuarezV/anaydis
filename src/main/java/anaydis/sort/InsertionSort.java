package anaydis.sort;

import anaydis.sort.gui.SorterListener;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class InsertionSort extends AbstractSorter{

    public InsertionSort() {
        super(SorterType.INSERTION);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j > 0; j--) {
                notifyc(j, j-1);
                if (less(comparator, list, j, j - 1)) {
                    swap(list, j, j - 1);
                    notifys(j,j-1);
                }else {
                    break;
                }
            }
        }
    }

    @Override
    public @NotNull SorterType getType() {
        return SorterType.INSERTION;
    }

    @Override
    public void addSorterListener(@NotNull SorterListener listener) {

    }

    @Override
    public void removeSorterListener(@NotNull SorterListener listener) {

    }
}
