package anaydis.sort.performanceMains;

import anaydis.sort.MetricListener;
import anaydis.sort.ShellSort;
import anaydis.sort.correctnessTests.IntegerDataSetGenerator;
import java.util.Comparator;
import java.util.List;

public class ShellSortPerformanceMain {
    public static void main(String[] args) {

        Integer[] list16577 = new Integer[]{1, 8, 23, 77, 281, 1073, 4193, 16577};
        Integer[] list9841 = new Integer[]{1, 4, 13, 40, 121, 364, 1093, 3280, 9841};
        Integer[] sizes = new Integer[]{1000, 5000, 10000};
        ShellSort sorter = new ShellSort();
        MetricListener listener = new MetricListener();
        sorter.addSorterListener(listener);
        IntegerDataSetGenerator integerDataSetGenerator = new IntegerDataSetGenerator();
        testByList(listener, sizes, integerDataSetGenerator, sorter, list16577, "list16577");
        testByList(listener, sizes, integerDataSetGenerator, sorter, list9841, "list9841");
    }

        public static void testByList(MetricListener listener, Integer[] sizes, IntegerDataSetGenerator integerDataSetGenerator, ShellSort sorter, Integer[] list, String listName){
            for (Integer size : sizes) {
                List<Integer> rdm = integerDataSetGenerator.createRandom(size);
                long startTime = System.nanoTime();
                sorter.sort(Comparator.naturalOrder(), rdm, list);
                long endTime = System.nanoTime();
                long totalTime = endTime - startTime;
                System.out.println(size + " " + listName +" number of comparisons: " + listener.getComparisons() + " running time: " + totalTime / 1000 + " micro seconds");
            }
        }
    }


