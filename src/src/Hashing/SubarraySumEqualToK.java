package Hashing;

import java.util.HashMap;
import java.util.Scanner;

public class SubarraySumEqualToK {
    public static int subarraySum(int[] arr, int k) {
        int ans = 0;

        int n = arr.length;

        int currSum = 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(0, 1); // because initial sum is zero

        for (int i = 0; i < n; i++) {
            currSum += arr[i];

            // finding currsum - k which means if we get currSum - k than if we
            // add k in it than it will become currSum which means that we got one subarray that is giving k sum
            // and we will add frequency in it because if we got two frequency
            // than it means there are two subarrays so add freq and than update frequency
            if (map.containsKey(currSum - k)) {
                ans += map.get(currSum - k);
            }

            map.put(currSum, map.getOrDefault(currSum, 0) + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        int ans = subarraySum(arr, k);
        System.out.println(ans);
    }
}
