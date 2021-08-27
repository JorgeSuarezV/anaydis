package anaydis.sort;

import anaydis.sort.gui.SorterListener;

public class MetricListener implements SorterListener  {

    int swaps = 0;
    int comparisons = 0;


    @Override
    public void box(int from, int to) {}

    @Override
    public void copy(int from, int to, boolean copyToAux) {}

    @Override
    public void equals(int i, int j) {}

    @Override
    public void greater(int i, int j) {
        comparisons++;
    }

    @Override
    public void swap(int i, int j) {
        swaps++;
    }

    public int getComparisons() {
        return comparisons;
    }

    public int getSwaps() {
        return swaps;
    }
}
