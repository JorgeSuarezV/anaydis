package anaydis.sort;

import anaydis.sort.gui.ObservableSorter;
import anaydis.sort.gui.SorterListener;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractSorter implements ObservableSorter {

    private final ArrayList<SorterListener> listeners = new ArrayList<>();
    private final SorterType type;

    public AbstractSorter(SorterType type) {
        this.type = type;
    }


    <T> boolean greater(Comparator<T> comparator, List<T> list, int i, int j){
        notifyC(i,j);
        return comparator.compare(list.get(i), list.get(j)) > 0;
    }

    <T> boolean less(Comparator<T> comparator, List<T> list, int i, int j){
        notifyC(i,j);
        return comparator.compare(list.get(i), list.get(j)) < 0;
    }

    <T> boolean equals(Comparator<T> comparator, List<T> list, int i, int j){
        return  comparator.compare(list.get(i), list.get(j)) == 0;
    }

    <T> void swap(List<T>  list, int i, int j){
        notifyS(i,j);
        list.set(j, list.set(i, list.get(j)));
    }

    @NotNull public SorterType getType(){
        return type;
    }

    public void notifyC(int i, int j){
        for (SorterListener listener : listeners) {
            listener.greater(i, j);
        }
    }

    public void notifyS(int i, int j){
        for (SorterListener listener : listeners) {
            listener.swap(i, j);
        }
    }

    @Override
    public void addSorterListener(@NotNull SorterListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeSorterListener(@NotNull SorterListener listener) {
        listeners.remove(listener);
    }

    public ArrayList<SorterListener> getListeners() {
        return listeners;
    }

    public <T> int partition(@NotNull Comparator<T> comparator, @NotNull List<T> list, int min, int max){
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

    public <T> void merge(Comparator<T> comparator, List<T> list, int l, int m, int r) {
        List<T> aux = new ArrayList<>(list.size());
        for (int i = 0; i < r + 1; i++) {
            aux.add(null);
        }
        for (int i = l; i <= m; i++) {
            copy(aux, i, list, i);
        }
        for (int j = m + 1; j <= r; j++) {
            copy(aux, r + (m + 1) - j, list, j);
        }
        for (int k = l, i = l, j = r; k <= r; k++) {
            if(greater(comparator, aux, i, j)) {
                copy(list, k, aux, j--);
            } else {
                copy(list, k, aux, i++);
            }
        }
    }

    public <T> void copy(List<T> list1, int i, List<T> list2, int j){
        T obj2 = list2.get(j);
        list1.set(i, obj2);
    }
}
