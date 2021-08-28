package anaydis.sort;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

    public class SorterProviderImpl implements SorterProvider{

        private final Map<SorterType,Sorter> sorters = new EnumMap<>(SorterType.class);

        public SorterProviderImpl(){
            final SelectionSort selection = new SelectionSort();
            sorters.put(selection.getType(), selection);
            final BubbleSort bubble = new BubbleSort();
            sorters.put(bubble.getType(), bubble);
            final InsertionSort insertion = new InsertionSort();
            sorters.put(insertion.getType(), insertion);
        }

        @Override
        public @NotNull Iterable<Sorter> getAllSorters() {
            return new ArrayList<>(sorters.values());
        }

        @Override
        public @NotNull Sorter getSorterForType(@NotNull SorterType type) {
            return sorters.get(type);
        }
    }

