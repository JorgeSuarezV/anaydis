package anaydis.sort.performanceTests;

import anaydis.sort.SorterProviderImpl;
import anaydis.sort.SorterType;
import anaydis.sort.correctnessTests.SorterTest;
import org.junit.Test;
import java.util.List;

public class SortPerformanceTest extends SorterTest {
    @Test
    public void performanceTest(){
        Integer[] sizes = new Integer[]{100, 500, 1000, 2000, 5000};
        SorterProviderImpl sorterProvider = (SorterProviderImpl) getSorterProvider();
        List<SorterType> sorterTypes = sorterProvider.getAllSorterTypes();
        sorterTypes.remove(SorterType.H);
        SwapsAndComparisonTest test = new SwapsAndComparisonTest();
        for (SorterType sorterType : sorterTypes) {
            for (Integer size : sizes) {
                test.asc_Des_Rdm(sorterType, size, sorterProvider);
            }
        }
    }
}

