package anaydis.sort;

import anaydis.sort.correctnessTests.IntegerDataSetGenerator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ShellSortPerformanceTest {

    @Test public void shellSortTest() {
        ArrayList<Integer> list16577 = new ArrayList<>();
        list16577.add(1);
        list16577.add(8);
        list16577.add(23);
        list16577.add(77);
        list16577.add(281);
        list16577.add(1073);
        list16577.add(4193);
        list16577.add(16577);
        ArrayList<Integer> list9841 = new ArrayList<>();
        list9841.add(1);
        list9841.add(4);
        list9841.add(13);
        list9841.add(40);
        list9841.add(121);
        list9841.add(364);
        list9841.add(1093);
        list9841.add(3280);
        list9841.add(9841);
        ArrayList<Integer> sizes = new ArrayList<>();
        sizes.add(1000);
        sizes.add(5000);
        sizes.add(10000);
        ShellSort sorter = new ShellSort();
        MetricListener listener = new MetricListener();
        sorter.addSorterListener(listener);
        IntegerDataSetGenerator integerDataSetGenerator = new IntegerDataSetGenerator();
        testByList(listener, sizes, integerDataSetGenerator, sorter, list16577);
        testByList(listener, sizes, integerDataSetGenerator, sorter, list9841);
    }

        public void testByList(MetricListener listener, ArrayList<Integer> sizes, IntegerDataSetGenerator integerDataSetGenerator,  ShellSort sorter, ArrayList<Integer> list){
            for (Integer size : sizes) {
                List<Integer> rdm = integerDataSetGenerator.createRandom(size);
                long startTime = System.nanoTime();
                sorter.sort(Comparator.naturalOrder(), rdm, list);
                long endTime = System.nanoTime();
                long totalTime = endTime - startTime;
                System.out.println(size + " " + list +" number of comparisons: " + listener.getComparisons() + " running time: " + totalTime / 1000 + " micro seconds");
            }
        }
    }


