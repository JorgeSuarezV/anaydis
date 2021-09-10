package anaydis.sort.correctnessTests;

import anaydis.sort.AbstractSorter;
import anaydis.sort.MetricListener;
import anaydis.sort.SorterProviderImpl;
import anaydis.sort.SorterType;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MetricListenerTest {

    @Test public void ListenerRecordsTest() {
        List<Integer> array = new ArrayList<>();
        array.add(80);
        array.add(287);
        array.add(972);
        array.add(781);
        array.add(893);
        array.add(452);
        array.add(307);
        array.add(247);
        array.add(423);
        array.add(540);
        SorterProviderImpl sorterProvider = new SorterProviderImpl();
        AbstractSorter sorter1 = (AbstractSorter) sorterProvider.getSorterForType(SorterType.BUBBLE);
        AbstractSorter sorter2 = (AbstractSorter) sorterProvider.getSorterForType(SorterType.INSERTION);
        AbstractSorter sorter3 = (AbstractSorter) sorterProvider.getSorterForType(SorterType.SELECTION);
        MetricListener listener1 = new MetricListener();
        MetricListener listener2 = new MetricListener();
        MetricListener listener3 = new MetricListener();
        List<Integer> copy1 = new ArrayList<>(array);
        List<Integer> copy2 = new ArrayList<>(array);
        List<Integer> copy3 = new ArrayList<>(array);
        sorter1.addSorterListener(listener1);
        sorter2.addSorterListener(listener2);
        sorter3.addSorterListener(listener3);
        sorter1.sort(Comparator.naturalOrder(), copy1);
        sorter2.sort(Comparator.naturalOrder(), copy2);
        sorter3.sort(Comparator.naturalOrder(), copy3);
        sorter1.removeSorterListener(listener1);
        sorter2.removeSorterListener(listener2);
        sorter3.removeSorterListener(listener3);
        Assert.assertEquals(44, listener1.getComparisons());
        Assert.assertEquals(22, listener1.getSwaps());
        Assert.assertEquals(31, listener2.getComparisons() );
        Assert.assertEquals(22, listener2.getSwaps());
        Assert.assertEquals(45, listener3.getComparisons());
        Assert.assertEquals(6, listener3.getSwaps());
    }
}

