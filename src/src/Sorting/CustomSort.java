package Sorting;

import java.util.Arrays;

public class CustomSort {
    public static void main(String[] args) {
        int[] arr = {3, 10, 4, 1, 5, 8, -1, -4, -10};


        int n = arr.length;
        selectionSort(arr, n);
        System.out.println(Arrays.toString(arr));
        bubbleSort(arr, n);
        System.out.println(Arrays.toString(arr));
        insertionSort(arr, n);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertionSort(int[] arr, int n) {

        // its like we are considering first part sorted and pulling elements from unsorted part and
        // inserting them in sorted part at their correct position
        // for starters we are

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && key < arr[j]) {
                arr[j++] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }


    public static void bubbleSort(int[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) swap(arr, j, j + 1);
            }
        }
    }

    public static void selectionSort(int[] arr, int n) {

        for (int i = 0; i <= n - 2; i++) {
            int min = i;
            for (int j = i + 1; j <= n - 1; j++) {
                if (arr[j] < arr[min]) min = j;
            }
            swap(arr, i, min);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
