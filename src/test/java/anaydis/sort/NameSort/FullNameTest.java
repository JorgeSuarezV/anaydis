package anaydis.sort.nameSort;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class FullNameTest {

    @Test public void testFullNameSort(){
        FullNameGenerator fullNameGenerator = new FullNameGenerator();
        List<FullName> fullNames = fullNameGenerator.createRandom(50);
        FullNameSort fullNameSort = new FullNameSort();
        fullNameSort.sort(fullNames);
        for (int i = 1; i < fullNames.size(); i++) {
            if (fullNames.get(i-1).getLastName().compareTo(fullNames.get(i).getLastName()) == 0){
                Assert.assertTrue(fullNames.get(i-1).getFirstName().compareTo(fullNames.get(i).getFirstName()) < 0);
            }
            Assert.assertTrue(fullNames.get(i-1).getLastName().compareTo(fullNames.get(i).getLastName()) < 0);
        }
    }
}
