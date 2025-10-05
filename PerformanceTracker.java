package com.assignment2.metrics;

import java.util.ArrayList;
import java.util.List;

public class PerformanceTracker {
    private long comparisons;
    private long swaps;
    private long arrayAccesses;
    private long startTime;
    private long endTime;
    private List<Long> memoryUsage;

    public PerformanceTracker() {
        reset();
    }

    public void reset() {
        comparisons = 0;
        swaps = 0;
        arrayAccesses = 0;
        startTime = 0;
        endTime = 0;
        memoryUsage = new ArrayList<>();
    }

    public void startTimer() {
        startTime = System.nanoTime();
        recordMemoryUsage();
    }

    public void stopTimer() {
        endTime = System.nanoTime();
        recordMemoryUsage();
    }

    public void recordComparison() {
        comparisons++;
    }

    public void recordSwap() {
        swaps++;
    }

    public void recordArrayAccess(int count) {
        arrayAccesses += count;
    }

    private void recordMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        long memory = runtime.totalMemory() - runtime.freeMemory();
        memoryUsage.add(memory);
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getSwaps() {
        return swaps;
    }

    public long getArrayAccesses() {
        return arrayAccesses;
    }

    public long getTimeNanos() {
        return endTime - startTime;
    }

    public double getTimeMillis() {
        return (endTime - startTime) / 1_000_000.0;
    }

    public List<Long> getMemoryUsage() {
        return new ArrayList<>(memoryUsage);
    }

    public String getSummary() {
        return String.format(
                "Time: %.3f ms, Comparisons: %d, Swaps: %d, Array Accesses: %d",
                getTimeMillis(), getComparisons(), getSwaps(), getArrayAccesses()
        );
    }
}
