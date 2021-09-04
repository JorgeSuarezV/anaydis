package anaydis.sort;

import anaydis.sort.*;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

    public class SorterProviderImpl implements SorterProvider {

        private final Map<SorterType, Sorter> sorters = new EnumMap<>(SorterType.class);

        public SorterProviderImpl(){
            final SelectionSort selection = new SelectionSort();
            sorters.put(selection.getType(), selection);
            final BubbleSort bubble = new BubbleSort();
            sorters.put(bubble.getType(), bubble);
            final InsertionSort insertion = new InsertionSort();
            sorters.put(insertion.getType(), insertion);
            final QuickSort quick = new QuickSort();
            sorters.put(quick.getType(), quick);
            final HSort H = new HSort();
            sorters.put(H.getType(), H);
            final ShellSort shellSort = new ShellSort();
            sorters.put(shellSort.getType(), shellSort);
            final QuickSortNonRecursive quickSortNonRecursive = new QuickSortNonRecursive();
            sorters.put(quickSortNonRecursive.getType(), quickSortNonRecursive);
            final QuickSortCut quickSortCut = new QuickSortCut();
            sorters.put(quickSortCut.getType(), quickSortCut);
            final QuickSortMedOf3 quickSortMedOf3 = new QuickSortMedOf3();
            sorters.put(quickSortMedOf3.getType(), quickSortMedOf3);
            final QuickSort3WayPart quickSort3WayPart = new QuickSort3WayPart();
            sorters.put(quickSort3WayPart.getType(), quickSort3WayPart);
        }

        @Override
        public @NotNull Iterable<Sorter> getAllSorters(){
            return new ArrayList<>(sorters.values());
        }

        public @NotNull List<SorterType> getAllSorterTypes(){
            return  new ArrayList<>(sorters.keySet());
        }

        @Override
        public @NotNull Sorter getSorterForType(@NotNull SorterType type){
            return sorters.get(type);
        }
    }

