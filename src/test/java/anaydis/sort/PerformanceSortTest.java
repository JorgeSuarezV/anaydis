package anaydis.sort;
import org.junit.Test;


public class PerformanceSortTest extends SorterTest{

    @Test public void testBubbleWithIntegerGenerator() {
        testSorter(createIntegerDataSetGenerator(), SorterType.BUBBLE, 1000);
        testSorter(createIntegerDataSetGenerator(), SorterType.BUBBLE, 5000);
        testSorter(createIntegerDataSetGenerator(), SorterType.BUBBLE, 10000);
        testSorter(createIntegerDataSetGenerator(), SorterType.BUBBLE, 20000);
        testSorter(createIntegerDataSetGenerator(), SorterType.BUBBLE, 40000);
        testSorter(createIntegerDataSetGenerator(), SorterType.BUBBLE, 100000);
    }

    @Test public void testInsertionWithIntegerGenerator() {
        testSorter(createIntegerDataSetGenerator(), SorterType.INSERTION, 1000);
        testSorter(createIntegerDataSetGenerator(), SorterType.INSERTION, 5000);
        testSorter(createIntegerDataSetGenerator(), SorterType.INSERTION, 10000);
        testSorter(createIntegerDataSetGenerator(), SorterType.INSERTION, 20000);
        testSorter(createIntegerDataSetGenerator(), SorterType.INSERTION, 40000);
        testSorter(createIntegerDataSetGenerator(), SorterType.INSERTION, 100000);
    }

    @Test public void testSelectionWithIntegerGenerator() {
        testSorter(createIntegerDataSetGenerator(), SorterType.SELECTION, 1000);
        testSorter(createIntegerDataSetGenerator(), SorterType.SELECTION, 5000);
        testSorter(createIntegerDataSetGenerator(), SorterType.SELECTION, 10000);
        testSorter(createIntegerDataSetGenerator(), SorterType.SELECTION, 20000);
        testSorter(createIntegerDataSetGenerator(), SorterType.SELECTION, 40000);
        testSorter(createIntegerDataSetGenerator(), SorterType.SELECTION, 100000);
    }

}
