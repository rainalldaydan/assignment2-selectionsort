package com.assignment2.cli;

import com.assignment2.algorithms.SelectionSort;
import com.assignment2.metrics.PerformanceTracker;
import java.util.Arrays;
import java.util.Random;

public class BenchmarkRunner {

    public static void main(String[] args) {
        if (args.length == 0) {
            runDefaultBenchmarks();
        } else {
            switch (args[0]) {
                case "test":
                    runCorrectnessTests();
                    break;
                case "benchmark":
                    runPerformanceBenchmarks();
                    break;
                case "custom":
                    if (args.length > 1) {
                        runCustomTest(args[1]);
                    } else {
                        System.out.println("Please provide array size for custom test");
                    }
                    break;
                default:
                    System.out.println("Unknown command. Use: test, benchmark, or custom <size>");
            }
        }
    }

    private static void runDefaultBenchmarks() {
        System.out.println("=== Selection Sort Benchmark Runner ===");
        runCorrectnessTests();
        System.out.println("\n=== Performance Benchmarks ===");
        runPerformanceBenchmarks();
    }

    private static void runCorrectnessTests() {
        System.out.println("=== Correctness Tests ===");

        SelectionSort sorter = new SelectionSort();

        testSort(sorter, new int[]{}, "Empty array");
        testSort(sorter, new int[]{5}, "Single element");
        testSort(sorter, new int[]{1, 2, 3, 4, 5}, "Already sorted");
        testSort(sorter, new int[]{5, 4, 3, 2, 1}, "Reverse sorted");
        testSort(sorter, new int[]{3, 1, 4, 1, 5, 9, 2, 6}, "Random array with duplicates");
        testSort(sorter, new int[]{7, 7, 7, 7}, "All duplicates");
    }

    private static void testSort(SelectionSort sorter, int[] input, String testName) {
        System.out.print("Testing " + testName + ": ");

        int[] sorted = sorter.sort(input);
        PerformanceTracker tracker = sorter.getPerformanceTracker();

        if (SelectionSort.isSorted(sorted)) {
            System.out.println("PASS - " + tracker.getSummary());
        } else {
            System.out.println("FAIL");
            System.out.println("  Input: " + Arrays.toString(input));
            System.out.println("  Output: " + Arrays.toString(sorted));
        }
    }

    private static void runPerformanceBenchmarks() {
        int[] sizes = {100, 1000, 5000};

        System.out.println("Size\tTime(ms)\tComparisons\tSwaps");
        System.out.println("----\t--------\t-----------\t-----");

        for (int size : sizes) {
            benchmarkSize(size);
        }
    }

    private static void benchmarkSize(int size) {
        SelectionSort sorter = new SelectionSort();
        int[] randomArray = generateRandomArray(size);

        sorter.sort(Arrays.copyOf(randomArray, randomArray.length));

        int[] testArray = Arrays.copyOf(randomArray, randomArray.length);
        sorter.sort(testArray);
        PerformanceTracker tracker = sorter.getPerformanceTracker();

        System.out.printf("%d\t%.3f\t%d\t%d\n",
                size, tracker.getTimeMillis(), tracker.getComparisons(), tracker.getSwaps());
    }

    private static void runCustomTest(String sizeStr) {
        try {
            int size = Integer.parseInt(sizeStr);
            if (size <= 0) {
                System.out.println("Size must be positive");
                return;
            }

            int[] array = generateRandomArray(size);
            SelectionSort sorter = new SelectionSort();

            System.out.println("Original array (first 20 elements): ");
            printFirstElements(array, 20);

            int[] sorted = sorter.sort(array);
            PerformanceTracker tracker = sorter.getPerformanceTracker();

            System.out.println("Sorted array (first 20 elements): ");
            printFirstElements(sorted, 20);

            System.out.println("\nPerformance Metrics:");
            System.out.println(tracker.getSummary());

        } catch (NumberFormatException e) {
            System.out.println("Invalid size: " + sizeStr);
        }
    }

    private static int[] generateRandomArray(int size) {
        Random random = new Random(42);
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size * 10);
        }
        return array;
    }

    private static void printFirstElements(int[] array, int count) {
        int printCount = Math.min(array.length, count);
        for (int i = 0; i < printCount; i++) {
            System.out.print(array[i] + " ");
        }
        if (array.length > count) {
            System.out.print("...");
        }
        System.out.println();
    }
}
