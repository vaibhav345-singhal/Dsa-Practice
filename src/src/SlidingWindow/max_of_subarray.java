package SlidingWindow;

import java.util.*;

public class max_of_subarray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int k = sc.nextInt();

        System.out.println(maxSubarray(arr, k));
    }

    public static ArrayList<Integer> maxSubarray(int[] arr, int k) {
        int n = arr.length;
        ArrayList<Integer> ans = new ArrayList<>();

        int i = 0;
        int j = 0;

        Deque<Integer> q = new LinkedList<>();

        while (j < n) {
// if deque front element is smaller than current element that is of no use for us thats why removing those than adding the element
            while (q.size() > 0 && q.peekLast() < arr[j]) {
                q.removeLast();
            }
            q.addLast(arr[j]);

            if (j - i + 1 < k) {
                j++;
            } else if (j - i + 1 == k) {

                ans.add(q.peek());
// if deque peek is equal to arr[i] than removing first element from the deque because it is no longer belongs to our window
                if (q.peek() == arr[i]) {
                    q.removeFirst();
                }
                i++;
                j++;
            }
        }
        return ans;
    }

}
