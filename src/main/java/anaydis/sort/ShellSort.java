package anaydis.sort;

import anaydis.sort.gui.SorterListener;
import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

public class ShellSort extends AbstractSorter{

    HSort hSort = new HSort();

    public ShellSort() {
        super(SorterType.SHELL);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        int h = 1;
        while (h <= (list.size()-1)/9){
            h = 3*h+1;
        }
        for ( ; h > 0; h /= 3){
            hSort.sort(comparator, list, h);
        }
    }

    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list , List<Integer> hs) {
        for (int i = hs.size()-1; i >= 0; i--) {
            int h = hs.get(i);
            if (h < list.size()) {
                hSort.sort(comparator, list, h);
            }
        }
    }

    @Override
    public void addSorterListener(@NotNull SorterListener listener) {
        hSort.addSorterListener(listener);
    }
}
