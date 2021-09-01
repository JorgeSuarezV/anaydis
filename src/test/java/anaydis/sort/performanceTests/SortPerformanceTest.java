package anaydis.sort.performanceTests;

import anaydis.sort.SorterProviderImpl;
import anaydis.sort.SorterType;
import anaydis.sort.correctnessTests.SorterTest;
import org.junit.Test;
import java.io.IOException;
import java.util.List;

public class SortPerformanceTest extends SorterTest {
    @Test
    public void performanceTest() throws IOException {
        Integer[] sizes = new Integer[]{100, 500, 1000, 2000, 5000};
        SorterProviderImpl sorterProvider = (SorterProviderImpl) getSorterProvider();
        List<SorterType> sorterTypes = sorterProvider.getAllSorterTypes();
        sorterTypes.remove(SorterType.H);
        SwapsAndComparisonTest test = new SwapsAndComparisonTest();
        CSVWriter csvWriter = new CSVWriter("sort data");
        for (SorterType sorterType : sorterTypes) {
            for (Integer size : sizes) {
                test.asc_Des_Rdm(sorterType, size, sorterProvider);
            }
        }
    }
    public void performanceTestToCSV() throws IOException {
        Integer[] sizes = new Integer[]{100, 500, 1000, 2000, 5000};
        SorterProviderImpl sorterProvider = (SorterProviderImpl) getSorterProvider();
        List<SorterType> sorterTypes = sorterProvider.getAllSorterTypes();
        sorterTypes.remove(SorterType.H);
        SwapsAndComparisonTest test = new SwapsAndComparisonTest();
        CSVWriter csvWriter = new CSVWriter("sort data");
        for (SorterType sorterType : sorterTypes) {
            for (Integer size : sizes) {
                test.asc_Des_RdmToCSV(csvWriter, sorterType, size, sorterProvider);
            }
        }
    }
}

