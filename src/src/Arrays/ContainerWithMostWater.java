package Arrays;

import java.util.*;

public class ContainerWithMostWater {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            System.out.println(maxArea(arr));
        }
    }

    public static int maxArea(int[] height) {
        int n = height.length;

        int max = 0;
        int curr = 0;

        int s = 0;
        int e = n - 1;

        while (s < e) {

            int hgt = Math.min(height[s], height[e]);
            int width = e - s;

            curr = hgt * width;

            max = Math.max(curr, max);

            if (height[s] < height[e]) {
                s++;
            } else {
                e--;
            }

        }
        return max;
    }
}