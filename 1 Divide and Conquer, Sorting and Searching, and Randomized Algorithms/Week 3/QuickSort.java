// Three different partition functions:
//        1. Use the 1st element as pivot.
//        2. Use the last element as pivot
//        3. Use the median of three as pivot(first, last and middle elements).

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class QuickSort {

    public static int sort(int[] list) {
        if ((list == null || list.length == 0)) return 0;
        return quickSortHelper(list, 0, list.length - 1);
    }

    private static int quickSortHelper(int[] list, int lo, int hi) {
        if (hi - lo + 1 <= 1) return 0;
        int count = 0;
        int pivotIndex = partitionOne(list, lo, hi); // case #1, use first element as pivot
//        int pivotIndex = partitionTwo(list, lo, hi); // case #2, use last element as pivot
//        int pivotIndex = partitionThree(list, lo, hi); // case #3, use the median of three as pivot(first, last and middle elements)
        System.out.println(pivotIndex);
        int left = quickSortHelper(list, lo, pivotIndex-1);
        int right = quickSortHelper(list, pivotIndex+1, hi);
        count = left + right + hi - lo;
        return count;
    }

    private static void swap(int[] list, int i, int j) {
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }

    private static int partitionOne(int[] list, int lo, int hi) {
        int pivot = list[lo];
        int i = lo + 1; // track index of pivot
        int j = lo + 1; // track array indexes reviewed

        while (true) {
            // scan array until item out of place
            while ((j <= hi) && (list[j] > pivot)) {
                ++j;
            }

            if (j > hi) break;

            if (i != j) {
                swap(list, i, j);
            }
            ++j;
            ++i;
        }
        // swap pivot into array index 0
        if (lo != (i - 1)) {
            swap(list, lo, i - 1);
        }
        // return the position of the pivot in the partitioned array
        return i - 1;
    }

    private static int partitionTwo(int[] list, int lo, int hi) {
        int pivot = list[hi];
        int i = lo + 1; // track index of pivot
        int j = lo + 1; // track array indexes reviewed

        while (true) {
            // scan array until item out of place
            while ((j <= hi) && (list[j] > pivot)) {
                ++j;
            }

            if (j > hi) break;

            if (i != j) {
                swap(list, i, j);
            }
            ++j;
            ++i;
        }
        // swap pivot into array index 0
        if (lo != (i - 1)) {
            swap(list, lo, i - 1);
        }
        // return the position of the pivot in the partitioned array
        return i - 1;
    }

    private static int partitionThree(int[] list, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        int median = 0;
        if (list[lo] > list[mid]) {
            if (list[lo] < list[hi]) {
                median = list[lo];
            } else if (list[mid] > list[hi]) {
                median = list[mid];
            } else {
                median = list[hi];
            }
        } else {
            if (list[lo] > list[hi]) {
                median = list[lo];
            } else if (list[mid] < list[hi]) {
                median = list[mid];
            } else {
                median = list[hi];
            }
        }

        if (median == list[hi]) {
            swap(list, lo, hi);
        } else {
            swap(list, lo, mid);
        }

        int pivot = list[lo];
        int i = lo + 1; // track index of pivot
        int j = lo + 1; // track array indexes reviewed

        while (true) {
            // scan array until item out of place
            while ((j <= hi) && (list[j] > pivot)) {
                ++j;
            }

            if (j > hi) break;

            if (i != j) {
                swap(list, i, j);
            }
            ++j;
            ++i;
        }
        // swap pivot into array index 0
        if (lo != (i - 1)) {
            swap(list, lo, i - 1);
        }
        // return the position of the pivot in the partitioned array
        return i - 1;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide a file path");
            return;
        }
        File file = new File(args[0]);
        int[] list = new int[100000];
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                list[i] = Integer.parseInt(line);
                ++i;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int count = sort(list);
        System.out.println(count);
    }
}
