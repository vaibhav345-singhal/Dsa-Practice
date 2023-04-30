package BinarySearch;

import java.util.*;

public class Cow {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        System.out.println(aggressiveCows(n, k, nums));
    }

    public static int aggressiveCows(int n, int k, int[] stalls) {
        Arrays.sort(stalls);

        int s = 1;
        int e = stalls[n - 1] - stalls[0];
        int ans = 0;

        while (s <= e) {

            int mid = (s + e) / 2;

            // checking if it is possible to arrange cows with max distance equal to mid if yes than we will maximize it further or else we will reduce our distance
            if (isValid(n, k, stalls, mid)) {
                ans = mid;
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        return ans;
    }

    private static boolean isValid(int n, int k, int[] stalls, int mid) {
        int pos = stalls[0];
        k--;
        for (int i = 1; i < n; i++) {
            if (stalls[i] - pos >= mid) {
                pos = stalls[i];
                if (k == 0)
                    break;
                k--;
            }
        }
        if (k == 0)
            return true;
        else
            return false;
    }
}
