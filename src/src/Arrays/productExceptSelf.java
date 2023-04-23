package Arrays;

import java.util.Arrays;
import java.util.Scanner;

public class productExceptSelf {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(Arrays.toString(productExceptSelf(arr)));
    }

    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] pre = new int[n];
        pre[0] = nums[0];
        for (int i = 1; i < n; i++) {
            pre[i] = nums[i] * pre[i - 1];
        }

        int[] suf = new int[n];
        suf[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suf[i] = suf[i + 1] * nums[i];
        }

        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                ans[i] = suf[i + 1];
            } else if (i == n - 1) {
                ans[i] = pre[i - 1];
            } else {
                ans[i] = pre[i - 1] * suf[i + 1];
            }
        }
        return ans;
    }
}
