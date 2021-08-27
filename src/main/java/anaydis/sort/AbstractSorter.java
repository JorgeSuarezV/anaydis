package anaydis.sort;

import anaydis.sort.gui.ObservableSorter;
import anaydis.sort.gui.SorterListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/**
 * Abstract sorter: all sorter implementations should subclass this class.
 */
public abstract class AbstractSorter implements ObservableSorter {

    ArrayList<MetricListener> listeners = new ArrayList<>();

    private final SorterType type;

    public AbstractSorter(SorterType type) {
        this.type = type;
    }

    <T> boolean greater(Comparator<T> comparator, List<T> list, int i, int j){
        return comparator.compare(list.get(i), list.get(j)) > 0;
    }

    <T> boolean less(Comparator<T> comparator, List<T> list, int i, int j){
        return comparator.compare(list.get(i), list.get(j)) < 0;
    }

    <T> void swap(List<T>  list, int i, int j){
        list.set(j, list.set(i, list.get(j)));
    }

    @NotNull public SorterType getType(){
        return type;
    }

    public void notifyc(int i, int j){
        for (int k = 0; i < listeners.size(); i++) {
            listeners.get(k).greater(i,j);
        }
    }

    public void notifys(int i, int j){
        listeners.get(0).swap(i,j);
        for (int k = 0; i < listeners.size(); i++) {
            listeners.get(k).swap(i,j);
        }
    }

    @Override
    public void addSorterListener(@NotNull SorterListener listener) {
        listeners.add((MetricListener) listener);
    }

    @Override
    public void removeSorterListener(@NotNull SorterListener listener) {
        MetricListener listener1 = (MetricListener) listener;
        for (int i = 0; i < listeners.size(); i++) {
            if (listener1.equals(listeners.get(i))){
                listeners.remove(i);
            }
        }
    }
}
