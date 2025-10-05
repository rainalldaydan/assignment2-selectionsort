package com.assignment2.algorithms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class SelectionSortTest {
    private SelectionSort sorter;

    @BeforeEach
    void setUp() {
        sorter = new SelectionSort();
    }

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            sorter.sort(null);
        });
    }

    @Test
    void testEmptyArray() {
        int[] input = {};
        int[] result = sorter.sort(input);
        assertArrayEquals(new int[]{}, result);
        assertTrue(SelectionSort.isSorted(result));
    }

    @Test
    void testSingleElement() {
        int[] input = {5};
        int[] result = sorter.sort(input);
        assertArrayEquals(new int[]{5}, result);
        assertTrue(SelectionSort.isSorted(result));
    }

    @Test
    void testAlreadySorted() {
        int[] input = {1, 2, 3, 4, 5};
        int[] result = sorter.sort(input);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result);
        assertTrue(SelectionSort.isSorted(result));
    }

    @Test
    void testReverseSorted() {
        int[] input = {5, 4, 3, 2, 1};
        int[] result = sorter.sort(input);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result);
        assertTrue(SelectionSort.isSorted(result));
    }

    @Test
    void testRandomArray() {
        int[] input = {3, 1, 4, 1, 5, 9, 2, 6};
        int[] result = sorter.sort(input);
        assertArrayEquals(new int[]{1, 1, 2, 3, 4, 5, 6, 9}, result);
        assertTrue(SelectionSort.isSorted(result));
    }

    @Test
    void testDuplicates() {
        int[] input = {7, 7, 7, 7};
        int[] result = sorter.sort(input);
        assertArrayEquals(new int[]{7, 7, 7, 7}, result);
        assertTrue(SelectionSort.isSorted(result));
    }

    @Test
    void testLargeArray() {
        int[] input = new int[1000];
        for (int i = 0; i < input.length; i++) {
            input[i] = input.length - i - 1;
        }

        int[] result = sorter.sort(input);
        assertTrue(SelectionSort.isSorted(result));
        assertEquals(input.length, result.length);
        for (int i = 0; i < result.length; i++) {
            assertEquals(i, result[i]);
        }
    }

    @Test
    void testTraditionalVsOptimized() {
        int[] input = {5, 3, 8, 1, 2};

        int[] traditionalResult = sorter.traditionalSort(input);
        int[] optimizedResult = sorter.sort(input);

        assertArrayEquals(traditionalResult, optimizedResult);
        assertTrue(SelectionSort.isSorted(traditionalResult));
        assertTrue(SelectionSort.isSorted(optimizedResult));
    }
}
