package anaydis.sort.performanceTests;

import anaydis.sort.SorterProviderImpl;
import anaydis.sort.SorterType;
import org.junit.Test;

import java.util.ArrayList;

public class SortPerformanceTest {
    @Test
    public void performanceTest() {
        ArrayList<SorterType> sorterTypes = new ArrayList();
        ArrayList<Integer> sizes = new ArrayList();
        SorterProviderImpl sorterProvider = new SorterProviderImpl();
        sorterTypes.add(SorterType.BUBBLE);
        sorterTypes.add(SorterType.INSERTION);
        sorterTypes.add(SorterType.SELECTION);
        sizes.add(100);
        sizes.add(500);
        sizes.add(1000);
        sizes.add(2000);
        sizes.add(5000);
        SwapsAndComparisonTest test = new SwapsAndComparisonTest();
        for (SorterType sorterType : sorterTypes) {
            for (Integer size : sizes) {
                test.asc_Des_Rdm(sorterType, size, sorterProvider);
            }
        }
    }
}

