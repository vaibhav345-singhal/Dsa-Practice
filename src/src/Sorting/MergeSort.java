package Sorting;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {3, 10, 4, 1, 5, 8, -1, -4, -10};
        int n = arr.length;
        mergeSort(arr, n);
        System.out.println(Arrays.toString(arr));
    }

    private static void mergeSort(int[] arr, int n) {
        divide(arr, 0, n - 1);
    }

    private static void divide(int[] arr, int s, int e) {
        if (s >= e) return;

        int mid = s + (e - s) / 2;

        divide(arr, s, mid);
        divide(arr, mid + 1, e);

        conquer(arr, s, mid, e);
    }

    private static void conquer(int[] arr, int s, int mid, int e) {
        int[] ans = new int[e - s + 1];

        int s1 = s;
        int s2 = mid + 1;
        int x = 0;

        while (s1 <= mid && s2 <= e) {
            if (arr[s1] <= arr[s2]) ans[x++] = arr[s1++];
            else ans[x++] = arr[s2++];
        }
        while (s1 <= mid) ans[x++] = arr[s1++];
        while (s2 <= e) ans[x++] = arr[s2++];

        for (int i = 0, j = s; i < ans.length; i++, j++) {
            arr[j] = ans[i];
        }
    }
}
