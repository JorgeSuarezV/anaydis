package anaydis.sort;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractMergeSort extends AbstractSorter{

    protected AbstractMergeSort(SorterType type) {
        super(type);
    }

    protected  <T> void merge(Comparator<T> comparator, List<T> list, List<T> aux, int l, int m, int r) {
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

    private <T> void copy(List<T> list1, int i, List<T> list2, int j){
        T obj2 = list2.get(j);
        list1.set(i, obj2);
    }

    protected <T> List<T> auxProvide(@NotNull List<T> list){
        List<T> aux = new ArrayList<>(list.size());
        for (int i = 0; i < list.size(); i++) {
            aux.add(null);
        }
        return aux;
    }
}
