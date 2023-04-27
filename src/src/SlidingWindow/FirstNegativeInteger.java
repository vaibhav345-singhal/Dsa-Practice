package SlidingWindow;

import java.util.*;

public class FirstNegativeInteger {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int k = sc.nextInt();

        System.out.println(Arrays.toString(printFirstNegativeInteger(arr, k, n)));
    }

    public static int[] printFirstNegativeInteger(int[] arr, int k, int n) {
        int[] ans = new int[n - k + 1];

        int i = 0;
        int j = 0;

        Queue<Integer> q = new LinkedList<>();
        int index = 0;
        while (j < n) {

            if (arr[j] < 0) {
                q.offer(arr[j]);
            }

            if (j - i + 1 < k) {
                j++;
            }

            else if (j - i + 1 == k) {
                // if q size is greater than 0 means this will be the first negative for our
                // window always
                if (q.size() > 0)
                    ans[index++] = q.peek();
                else
                    ans[index++] = 0;

                // if element is matching the front of queue than its meaning that we will have
                // to remove this from queue because now that element is not present in our
                // window
                if (q.size() > 0 && arr[i] == q.peek())
                    q.poll();
                i++;
                j++;
            }
        }
        return ans;
    }
}
