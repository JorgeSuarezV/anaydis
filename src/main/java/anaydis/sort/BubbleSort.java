package anaydis.sort;

import anaydis.sort.gui.SorterListener;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class BubbleSort extends AbstractSorter{

    public BubbleSort() {
        super(SorterType.BUBBLE);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        for (int i = 0; i < list.size()-1; i++) {
            int x = 1;
            for (int j = list.size()-1; j > i; j--) {
                if (greater(comparator, list , j-1, j)){
                    swap(list, j-1, j);
                    notifys(j-1,j);
                    x = 0;
                }notifyc(j-1, j);
            }
            if (x == 1) return;
        }
    }

    @Override
    public @NotNull SorterType getType() {
        return SorterType.BUBBLE;
    }
}
