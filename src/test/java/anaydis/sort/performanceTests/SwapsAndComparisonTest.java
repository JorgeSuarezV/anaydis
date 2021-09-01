package anaydis.sort.performanceTests;

import anaydis.sort.*;
import anaydis.sort.correctnessTests.IntegerDataSetGenerator;
import anaydis.sort.MetricListener;
import anaydis.sort.SorterProviderImpl;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class SwapsAndComparisonTest{

    public void asc_Des_Rdm(CSVWriter csvWriter, SorterType sorterType, Integer size, SorterProviderImpl sorterProvider) throws IOException {
        MetricListener listener = new MetricListener();
        AbstractSorter sorter = (AbstractSorter) sorterProvider.getSorterForType(sorterType);
        sorter.addSorterListener(listener);
        IntegerDataSetGenerator dataSetGenerator = new IntegerDataSetGenerator();
        List<Integer> asc = dataSetGenerator.createAscending(size);
        List<Integer> des = dataSetGenerator.createDescending(size);
        List<Integer> rdm = dataSetGenerator.createRandom(size);
        sorter.sort(Comparator.naturalOrder(), asc);
        csvWriter.write(sorterType + ",asc," + size + "," + listener.getSwaps() + "," + listener.getComparisons());
        printResults(sorterType, "asc", size, listener.getSwaps(), listener.getComparisons());
        listener.reset();
        sorter.sort(Comparator.naturalOrder(), des);
        csvWriter.write(sorterType + ",des," + size + "," + listener.getSwaps() + "," + listener.getComparisons());
        printResults(sorterType, "asc", size, listener.getSwaps(), listener.getComparisons());
        listener.reset();
        sorter.sort(Comparator.naturalOrder(), rdm);
        csvWriter.write(sorterType + ",rdm," + size + "," + listener.getSwaps() + "," + listener.getComparisons());
        printResults(sorterType, "asc", size, listener.getSwaps(), listener.getComparisons());
    }

    public void printResults(SorterType sorterType, String dataSetType, int size, int swaps, int comparisons){
        System.out.println(sorterType + " " + dataSetType + " " + size+ ": swaps; " + swaps + " comparisons; " + comparisons);
    }
}
