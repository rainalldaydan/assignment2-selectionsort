# Assignment 2 — Selection Sort (Student B)

## Overview
This repository contains the **Selection Sort** implementation with optimizations (early termination for nearly-sorted input).  
Developed for Assignment 2 (Algorithmic Analysis and Peer Review).

## Repository Structure
- `src/main/java/com/assignment2/algorithms/SelectionSort.java` — algorithm implementation
- `src/main/java/com/assignment2/metrics/PerformanceTracker.java` — performance tracker
- `src/main/java/com/assignment2/cli/BenchmarkRunner.java` — benchmark runner
- `src/test/java/com/assignment2/algorithms/SelectionSortTest.java` — unit tests
- `docs/analysis-report.pdf` — analysis report
- `docs/performance-plots/` — performance plots

## How to Run
```bash
mvn clean install
mvn exec:java -Dexec.mainClass="com.assignment2.cli.BenchmarkRunner"
```

## Example Benchmark Output
```
Size    Time(ms)   Comparisons   Swaps
100     0.2        4950          97
1000    20         499500        1000
```

## Reports
- Individual analysis report: [docs/analysis-report.pdf](docs/analysis-report.pdf)
- Performance plots: in `docs/performance-plots/`
