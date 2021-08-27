package anaydis.sort.NameSort;

import anaydis.sort.Sorter;
import anaydis.sort.SorterProviderImpl;
import org.junit.Assert;

import java.util.List;

public class FullNameTest {
    public static void main(String[] args) {
        testFullNameSort();
    }
    public static void testFullNameSort(){
        FullNameGenerator fullNameGenerator = new FullNameGenerator();
        List<FullName> fullNames = fullNameGenerator.createRandom(50);
        FullNameSort fullNameSort = new FullNameSort();
        fullNameSort.sort(fullNames);
        for (int i = 0; i < fullNames.size(); i++) {
            fullNames.get(i).printName();
        }
    }

}
