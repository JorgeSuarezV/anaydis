package anaydis.sort;

import java.util.ArrayList;
import java.util.Comparator;

public class NameSort {

    BubbleSort sorter = new BubbleSort();
    Comparator<FullName> comparator = Comparator.comparing(FullName::getFirstName).thenComparing(FullName::getLastName);


    public void NameSort(ArrayList<FullName> fullNames) {
        sorter.sort(comparator, fullNames);
    }

}
