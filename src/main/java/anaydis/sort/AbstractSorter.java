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

    protected AbstractSorter(SorterType type) {
        this.type = type;
    }


    protected <T> boolean greater(Comparator<T> comparator, List<T> list, int i, int j){
        notifyC(i,j);
        return comparator.compare(list.get(i), list.get(j)) > 0;
    }

    protected <T> boolean less(Comparator<T> comparator, List<T> list, int i, int j){
        notifyC(i,j);
        return comparator.compare(list.get(i), list.get(j)) < 0;
    }

    protected <T> boolean equals(Comparator<T> comparator, List<T> list, int i, int j){
        notifyE(i,j);
        return  comparator.compare(list.get(i), list.get(j)) == 0;
    }

    protected void notifyE(int i, int j){
        for (SorterListener listener : listeners) {
            listener.equals(i, j);
        }
    }

    protected <T> void swap(List<T>  list, int i, int j){
        notifyS(i,j);
        list.set(j, list.set(i, list.get(j)));
    }

    @NotNull public SorterType getType(){
        return type;
    }

    private void notifyC(int i, int j){
        for (SorterListener listener : listeners) {
            listener.greater(i, j);
        }
    }

    private void notifyS(int i, int j){
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
}
