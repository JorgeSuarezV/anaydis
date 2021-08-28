package anaydis.sort.nameSort;

import anaydis.sort.Sorter;
import anaydis.sort.SorterProviderImpl;
import anaydis.sort.SorterType;
import java.util.List;

public class FullNameSort {

    FirstNameComparator firstNameComparator = new FirstNameComparator();
    LastNameComparator lastNameComparator = new LastNameComparator();

    public <T> void sort(List<FullName> list){
        SorterProviderImpl sorterProvider = new SorterProviderImpl();
        Sorter sorter = sorterProvider.getSorterForType(SorterType.BUBBLE);
        sorter.sort(firstNameComparator, list);
        sorter.sort(lastNameComparator, list);
    }

}
