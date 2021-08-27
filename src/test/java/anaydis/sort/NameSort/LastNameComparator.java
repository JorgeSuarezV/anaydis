package anaydis.sort.NameSort;

import java.util.Comparator;

public class LastNameComparator implements Comparator<FullName> {

    @Override
    public int compare(FullName o1, FullName o2) {
        return o1.getLastName().compareTo(o2.getLastName());
    }
}
