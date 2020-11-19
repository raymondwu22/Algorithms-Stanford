/**
 * Given an array A, calculate the number of inversions in the given array, where an
 * inversion is defined as a pair of numbers with indices i and j, s.t. i < j
 * and A[i] > A[j].
 *
 * Naive implementation: O(n^2)
 *
 * Algorithm: Accompanying with MergeSort, we can count the # of inversions at the same
 * time in the merging step.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Count {
    public static long count(int[] list, int n) {
        if (n == 0 || list == null) return 0;
        // copy over array [O(n)]
        int[] listCopy = new int[n];
        System.arraycopy(list, 0, listCopy, 0, n);
        return mergeSortHelper(listCopy, 0, n - 1, new int[n]);
    }

    /**
     * Divide-and-Conquer O(nlogn) running time.
     */
    public static long mergeSortHelper(int[] list, int left, int right, int[] aux) {
        // Base case
        if (left == right) {
            return 0;
        }
        // Recursive case
        int mid = left + (right - left) / 2;
        long leftCount = mergeSortHelper(list, left, mid, aux);
        long rightCount = mergeSortHelper(list, mid + 1, right, aux);
        long crossCount = merge(list, left, mid, right, aux);
//        System.out.println(leftCount + " " + rightCount + " " + crossCount);
        return leftCount + rightCount + crossCount;
    }

    public static int merge(int[] list, int left, int mid, int right, int[] aux) {
        int leftPointer = left;
        int rightPointer = mid + 1;
        int temp = left;
        int inversions = 0;

        while ((leftPointer <= mid) && (rightPointer <= right)) {
            if (list[leftPointer] <= list[rightPointer]) {
                aux[temp] = list[leftPointer];
                ++leftPointer;
                inversions += rightPointer - (mid + 1);
            } else {
                aux[temp] = list[rightPointer];
                ++rightPointer;
            }
            ++temp;
        }
        // if right pointer is exhausted
        while (leftPointer <= mid) {
            aux[temp] = list[leftPointer];
            ++leftPointer;
            ++temp;
            inversions += right + 1 - (mid + 1);
        }
        // if left pointer is exhausted
        while (rightPointer <= right) {
            aux[temp] = list[rightPointer];
            ++rightPointer;
            ++temp;
        }

        System.arraycopy(aux, left, list, left, right-left+1);
        return inversions;
    }

    /* Brute-force O(n^2) running time */
    public static long countNaive(int[] list, int n) {
        long count = 0;
        System.out.println(count);
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (list[j] < list[i]) {
                    count++;
                }
            }

        }
        return count;
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
//        long numInversions = countNaive(list, list.length);
        long numInversions = count(list, list.length);
        System.out.println(numInversions);
    }
}
