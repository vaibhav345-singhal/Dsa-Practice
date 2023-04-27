package SlidingWindow;

import java.util.Scanner;

public class MaxSubarraySumOfSizeK {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int k = sc.nextInt();

        System.out.println(maxSum(arr, k));
    }

    public static long maxSum(int[] arr, int k) {

        int n = arr.length;

        int i = 0;
        int j = 0;

        long currSum = 0;
        long Sum = 0;

        while (j < n) {
            currSum += arr[j];

            // if window size is less than k
            if (j - i + 1 < k) {
                j++;
            } else if (j - i + 1 == k) {
                Sum = Math.max(currSum, Sum);
                currSum -= arr[i];
                i++;
                j++;
            }
        }
        return Sum;
    }

}
