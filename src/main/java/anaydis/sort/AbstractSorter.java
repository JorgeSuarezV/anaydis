package anaydis.sort;

import anaydis.sort.gui.ObservableSorter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/**
 * Abstract sorter: all sorter implementations should subclass this class.
 */
public abstract class AbstractSorter implements ObservableSorter {

    ArrayList<MetricListener> listeners = new ArrayList<MetricListener>();

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
        for (int k = 0; i < listeners.size(); i++) {
            listeners.get(k).swap(i,j);
        }
    }
}
