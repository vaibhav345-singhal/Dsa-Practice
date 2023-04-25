package Arrays;

import java.util.*;

public class TrappingRainWater {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            System.out.println(rainWater(arr));
        }
    }

    // o(n) with o(n) space
    public static int rainWater(int[] height) {
        int n = height.length;

        int[] maxLeft = new int[n];
        int[] maxRight = new int[n];

        maxLeft[0] = 0;

        for (int i = 1; i < n; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i - 1]);
        }

        maxRight[n - 1] = 0;

        for (int i = n - 2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i + 1], height[i + 1]);
        }

        int ans = 0;

        // System.out.println(Arrays.toString(maxLeft));
        // System.out.println(Arrays.toString(maxRight));

        for (int i = 0; i < n; i++) {
            int minHeight = Math.min(maxLeft[i], maxRight[i]);
            ans += minHeight - height[i] >= 0 ? minHeight - height[i] : 0;
        }
        return ans;
    }

    // o(n) with o(1) space
    public static int rainWaterBetter(int[] height) {
        int n = height.length;

        int l = 0;
        int r = n - 1;

        // initializing maxL and maxR 
        int maxL = height[l];
        int maxR = height[r];

        int ans = 0;

        while (l < r) {
            // whichever is smaller moving that and if both same than move any one
            if (maxR > maxL) {
                l++;
                // calculating new maxL for new l
                // and checking if it can store any water or not
                maxL = Math.max(maxL, height[l]);
                if (maxL - height[l] > 0) {
                    ans += maxL - height[l];
                }
            } else {
                r--;
                maxR = Math.max(maxR, height[r]);
                if (maxR - height[r] > 0) {
                    ans += maxR - height[r];
                }
            }
        }
        return ans;
    }
}
