package anaydis.sort.performanceMains;

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
        performanceTest(0);
    }
    public static void performanceTest(int csv) throws IOException {
        Integer[] sizes = new Integer[]{100, 500, 1000, 2000, 5000};
        SorterProviderImpl sorterProvider = new SorterProviderImpl();
        List<SorterType> sorterTypes = sorterProvider.getAllSorterTypes();
        sorterTypes.remove(SorterType.H);
        CSVWriter csvWriter = new CSVWriter("sort data");
        for (SorterType sorterType : sorterTypes) {
            for (Integer size : sizes) {
                    asc_Des_Rdm(csv, csvWriter, sorterType, size, sorterProvider);
            }
        }
    }

    public static void asc_Des_Rdm(int csv, CSVWriter csvWriter, SorterType sorterType, Integer size, SorterProviderImpl sorterProvider) throws IOException {
        MetricListener listener = new MetricListener();
        AbstractSorter sorter = (AbstractSorter) sorterProvider.getSorterForType(sorterType);
        sorter.addSorterListener(listener);
        IntegerDataSetGenerator dataSetGenerator = new IntegerDataSetGenerator();
        List<Integer> asc = dataSetGenerator.createAscending(size);
        List<Integer> des = dataSetGenerator.createDescending(size);
        List<Integer> rdm = dataSetGenerator.createRandom(size);
        sorter.sort(Comparator.naturalOrder(), asc);
        if (csv != 1) {
            printResults(sorterType, "asc", size, listener);
            listener.reset();
            sorter.sort(Comparator.naturalOrder(), des);
            printResults(sorterType, "des", size, listener);
            listener.reset();
            sorter.sort(Comparator.naturalOrder(), rdm);
            printResults(sorterType, "rdm", size, listener);
        }else {
            toCSV(csvWriter, sorterType, size, listener);
            listener.reset();
            sorter.sort(Comparator.naturalOrder(), des);
            toCSV(csvWriter, sorterType, size, listener);
            listener.reset();
            sorter.sort(Comparator.naturalOrder(), rdm);
            toCSV(csvWriter, sorterType,  size, listener);
        }
    }

    public static void printResults(SorterType sorterType, String dataSetType, int size, MetricListener listener){
        System.out.println(sorterType + " " + dataSetType + " " + size+ ": swaps; " + listener.getSwaps() + " comparisons; " + listener.getComparisons());
    }

    public static void toCSV(CSVWriter csvWriter, SorterType sorterType, int size, MetricListener listener) throws IOException {
        csvWriter.write(sorterType + ",rdm," + size + "," + listener.getSwaps() + "," + listener.getComparisons());
    }
}
