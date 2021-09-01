package anaydis.sort.correctnessTests;

import anaydis.sort.*;
import anaydis.sort.SorterProviderImpl;

/**
 * Sorter tests should subclass this abstract implementation
 */
abstract class SorterTest extends AbstractSorterTest {

    private final SorterProviderImpl provider = new SorterProviderImpl();

    @Override protected DataSetGenerator<String> createStringDataSetGenerator() {
        return new StringDataSetGenerator();
    }

    @Override protected DataSetGenerator<Integer> createIntegerDataSetGenerator() {
        return new IntegerDataSetGenerator();
    }

    @Override protected SorterProvider getSorterProvider() {
        return new SorterProviderImpl();
    }
}
