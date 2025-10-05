package com.assignment2.algorithms;

import com.assignment2.metrics.PerformanceTracker;

public class SelectionSort {
    private PerformanceTracker tracker;

    public SelectionSort() {
        this.tracker = new PerformanceTracker();
    }

    public SelectionSort(PerformanceTracker tracker) {
        this.tracker = tracker;
    }

    public int[] sort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }

        tracker.reset();
        tracker.startTimer();

        int n = arr.length;

        if (n <= 1) {
            tracker.stopTimer();
            return arr.clone();
        }

        int[] result = arr.clone();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            boolean sorted = true;

            for (int j = i + 1; j < n; j++) {
                tracker.recordComparison();

                if (result[j] < result[minIndex]) {
                    minIndex = j;
                    sorted = false;
                } else if (j > i + 1 && result[j] < result[j - 1]) {
                    sorted = false;
                }
            }

            tracker.recordComparison();
            if (minIndex != i) {
                swap(result, i, minIndex);
                tracker.recordSwap();
            }

            if (sorted) {
                break;
            }
        }

        tracker.stopTimer();
        return result;
    }

    public int[] traditionalSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }

        PerformanceTracker traditionalTracker = new PerformanceTracker();
        traditionalTracker.reset();
        traditionalTracker.startTimer();

        int n = arr.length;
        int[] result = arr.clone();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                traditionalTracker.recordComparison();
                if (result[j] < result[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                swap(result, i, minIndex);
                traditionalTracker.recordSwap();
            }
        }

        traditionalTracker.stopTimer();
        return result;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        tracker.recordArrayAccess(4);
    }

    public PerformanceTracker getPerformanceTracker() {
        return tracker;
    }

    public static boolean isSorted(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return true;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
