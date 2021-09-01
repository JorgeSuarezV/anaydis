package anaydis.sort.performanceTests;

import anaydis.sort.AbstractSorter;
import anaydis.sort.MetricListener;
import anaydis.sort.SorterProviderImpl;
import anaydis.sort.SorterType;
import anaydis.sort.correctnessTests.IntegerDataSetGenerator;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class MainPerformanceTest {
    public static void main(String[] args) throws IOException {
        performanceTestToCSV();
    }

    public static void performanceTestToCSV() throws IOException {
        Integer[] sizes = new Integer[]{100, 500, 1000, 2000, 5000};
        SorterProviderImpl sorterProvider = new SorterProviderImpl();
        List<SorterType> sorterTypes = sorterProvider.getAllSorterTypes();
        sorterTypes.remove(SorterType.H);
        CSVWriter csvWriter = new CSVWriter("sort data");
        for (SorterType sorterType : sorterTypes) {
            for (Integer size : sizes) {
                asc_Des_RdmToCSV(csvWriter, sorterType, size, sorterProvider);
            }
        }
    }

    public static void asc_Des_RdmToCSV(CSVWriter csvWriter, SorterType sorterType, Integer size, SorterProviderImpl sorterProvider) throws IOException {
        MetricListener listener = new MetricListener();
        AbstractSorter sorter = (AbstractSorter) sorterProvider.getSorterForType(sorterType);
        sorter.addSorterListener(listener);
        IntegerDataSetGenerator dataSetGenerator = new IntegerDataSetGenerator();
        List<Integer> asc = dataSetGenerator.createAscending(size);
        List<Integer> des = dataSetGenerator.createDescending(size);
        List<Integer> rdm = dataSetGenerator.createRandom(size);
        sorter.sort(Comparator.naturalOrder(), asc);
        csvWriter.write(sorterType + ",asc," + size + "," + listener.getSwaps() + "," + listener.getComparisons());
        listener.reset();
        sorter.sort(Comparator.naturalOrder(), des);
        csvWriter.write(sorterType + ",des," + size + "," + listener.getSwaps() + "," + listener.getComparisons());
        listener.reset();
        sorter.sort(Comparator.naturalOrder(), rdm);
        csvWriter.write(sorterType + ",rdm," + size + "," + listener.getSwaps() + "," + listener.getComparisons());
    }
}
