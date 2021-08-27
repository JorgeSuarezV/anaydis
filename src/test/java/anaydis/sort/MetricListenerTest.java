package anaydis.sort;

import anaydis.sort.gui.ObservableSorter;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        ObservableSorter sorter1 = (ObservableSorter) sorterProvider.getSorterForType(SorterType.BUBBLE);
        ObservableSorter sorter2 = (ObservableSorter) sorterProvider.getSorterForType(SorterType.INSERTION);
        ObservableSorter sorter3 = (ObservableSorter) sorterProvider.getSorterForType(SorterType.SELECTION);
        MetricListener listener1 = new MetricListener();
        MetricListener listener2 = new MetricListener();
        MetricListener listener3 = new MetricListener();
        List<Integer> copy1 = new ArrayList<>(array);
        List<Integer> copy2 = new ArrayList<>(array);
        List<Integer> copy3 = new ArrayList<>(array);
        sorter1.sort(Comparator.naturalOrder(), copy1);
        sorter2.sort(Comparator.naturalOrder(), copy2);
        sorter3.sort(Comparator.naturalOrder(), copy3);
        Assert.assertEquals(listener1.getComparisons(), 63);
        Assert.assertEquals(listener1.getSwaps(), 22);
        Assert.assertEquals(listener2.getComparisons(), 31);
        Assert.assertEquals(listener2.getSwaps(), 7);
        Assert.assertEquals(listener3.getComparisons(), 45);
        Assert.assertEquals(listener3.getSwaps(), 22);
    }
}
