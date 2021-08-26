package anaydis.sort;

import java.util.Comparator;
import java.util.List;
/**
 * Abstract sorter: all sorter implementations should subclass this class.
 */
abstract class AbstractSorter implements Sorter {

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

}
