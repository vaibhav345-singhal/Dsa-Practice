package Hashing;

import java.io.*;
import java.util.*;

public class SubarraySumDivisibleByK {

    public static int subarrayDivisbleByK(int nums[], int n, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // to handle 0 case

        int runningSum = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            runningSum += nums[i];

            int rem = runningSum % k;

            if (rem < 0) {
                rem += k;
            }

            if (!map.containsKey(rem)) {
                map.put(rem, 1);
            } else {
                ans += map.get(rem);
                map.put(rem, map.getOrDefault(rem, 0) + 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = sc.nextInt();
        }
        int result = subarrayDivisbleByK(arr, n, k);
        System.out.print(result);
        System.out.println('\n');
    }
}
