package anaydis.sort.NameSort;

import anaydis.sort.DataSetGenerator;
import anaydis.sort.StringDataSetGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FullNameGenerator implements DataSetGenerator<FullName>{


    @Override
    public @NotNull List<FullName> createAscending(int length) {
        return null;
    }

    @Override
    public @NotNull List<FullName> createDescending(int length) {
        return null;
    }

    @Override
    public @NotNull List<FullName> createRandom(int length) {
        StringDataSetGenerator generator = new StringDataSetGenerator();
        List<String> lastNames = generator.createRandom(length);
        List<String> firstNames = generator.createRandom(length);
        List<FullName> fullNames = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            fullNames.add(new FullName(firstNames.get(i), lastNames.get(i)));
        }
        return fullNames;
    }

    @Override
    public @NotNull Comparator<FullName> getComparator() {
        return null;
    }
}
