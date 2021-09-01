package anaydis.sort.performanceTests;

import java.io.IOException;

public class MainPerformanceTest {
    public static void main(String[] args) throws IOException {
        SortPerformanceTest sortPerformanceTest = new SortPerformanceTest();
        sortPerformanceTest.performanceTestToCSV();
    }
}
