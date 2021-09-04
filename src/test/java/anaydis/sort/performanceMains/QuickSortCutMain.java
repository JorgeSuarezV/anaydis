package anaydis.sort.performanceMains;

import anaydis.sort.MetricListener;
import anaydis.sort.QuickSortCut;
import anaydis.sort.correctnessTests.IntegerDataSetGenerator;
import java.util.Comparator;
import java.util.List;

public class QuickSortCutMain {
    public static void main(String[] args) {

        Integer[] sizes = new Integer[]{1000, 5000, 10000, 50000, 100000, 500000, 1000000};
        QuickSortCut sorter = new QuickSortCut();
        MetricListener listener = new MetricListener();
        sorter.addSorterListener(listener);
        IntegerDataSetGenerator integerDataSetGenerator = new IntegerDataSetGenerator();
        for (int size : sizes) {
            for (int m = 5; m < 26; m++) {
                List<Integer> rdm = integerDataSetGenerator.createRandom(size);
                long startTime = System.nanoTime();
                sorter.sortByM(Comparator.naturalOrder(), rdm, m, 0,size-1);
                long endTime = System.nanoTime();
                long totalTime = endTime - startTime;
                System.out.println(size + "," + m + "," + totalTime / 1000);
            }
        }
    }
}