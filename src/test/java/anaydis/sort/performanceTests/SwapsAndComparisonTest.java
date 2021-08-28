package anaydis.sort.performanceTests;

import anaydis.sort.*;
import anaydis.sort.correctnessTests.IntegerDataSetGenerator;
import org.junit.Test;
import java.util.Comparator;
import java.util.List;

public class SwapsAndComparisonTest{

    public void asc_Des_Rdm(SorterType sorterType, Integer size, SorterProviderImpl sorterProvider){
        MetricListener listener = new MetricListener();
        AbstractSorter sorter = (AbstractSorter) sorterProvider.getSorterForType(sorterType);
        sorter.addSorterListener(listener);
        IntegerDataSetGenerator dataSetGenerator = new IntegerDataSetGenerator();
        List<Integer> asc = dataSetGenerator.createAscending(size);
        List<Integer> des = dataSetGenerator.createDescending(size);
        List<Integer> rdm = dataSetGenerator.createRandom(size);
        sorter.sort(Comparator.naturalOrder(), asc);
        printResults(sorterType, "asc",size, listener.getSwaps(), listener.getComparisons());
        listener.reset();
        sorter.sort(Comparator.naturalOrder(), des);
        printResults(sorterType, "des",size, listener.getSwaps(), listener.getComparisons());
        listener.reset();
        sorter.sort(Comparator.naturalOrder(), rdm);
        printResults(sorterType, "rdm",size, listener.getSwaps(), listener.getComparisons());
    }

    public void printResults(SorterType sorterType, String dataSetType, int size, int swaps, int comparisons){
        System.out.println(sorterType + " " + dataSetType + " " + size+ ": swaps; " + swaps + " comparisons; " + comparisons);
    }

    public void printToCsv(SorterType sorterType, String dataSetType, int size, int swaps, int comparisons){
        System.out.println(sorterType + "," + dataSetType + "," + size+ "," + swaps + "," + comparisons);
    }
}
