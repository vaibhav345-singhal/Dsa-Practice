package Sorting;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {3, 10, 4, 1, 5, 8, -1, -4, -10};
        int n = arr.length;
        quickSort(arr, 0, n - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int s, int e) {
        if (s < e) {
            int pivotIndex = mainPartitionWorkOfQuickSort(arr, s, e);

            quickSort(arr, s, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, e);
        }
    }

    private static int mainPartitionWorkOfQuickSort(int[] arr, int s, int e) {
        int pivot = arr[e];
        int i = s - 1;

        for (int j = s; j <= e; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        i++;
        int temp = arr[i];
        arr[i] = pivot;
        arr[e] = temp;

        return i;
    }
}
