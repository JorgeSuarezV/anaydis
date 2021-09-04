package anaydis.sort;

import anaydis.sort.correctnessTests.SorterTest;

public class QuickSort3WayPartTest extends SorterTest {
    SorterProvider sorterProvider = getSorterProvider();
    QuickSort3WayPart sorter = (QuickSort3WayPart) sorterProvider.getSorterForType(SorterType.QUICK_THREE_PARTITION);

}
