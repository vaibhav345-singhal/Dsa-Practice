package Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Solution {

    // with multiple passes start
//    public static void nsel(long[] hist, long n, int[] nsel) {
//        Stack<Integer> st = new Stack<>();
//
//        for (int i = (int) n - 1; i >= 0; i--) {
//
//            long ele = hist[i];
//
//            while (!st.isEmpty() && hist[st.peek()] > ele) {
//                int idx = st.pop();
//                nsel[idx] = i;
//            }
//
//            st.push(i);
//        }
//
//        while (!st.isEmpty()) {
//            int idx = st.pop();
//            nsel[idx] = -1;
//        }
//    }
//
//    public static void nser(long[] hist, long n, int[] nser) {
//        Stack<Integer> st = new Stack<>();
//
//        for (int i = 0; i < (int) n; i++) {
//            long ele = hist[i];
//            while (!st.isEmpty() && hist[st.peek()] > ele) {
//                int idx = st.pop();
//                nser[idx] = i;
//            }
//            st.push(i);
//        }
//
//        while (!st.isEmpty()) {
//            int idx = st.pop();
//            nser[idx] = (int) n;
//        }
//    }
//
//
//    public static long maximumArea(long hist[], long n) {
//        int[] nsel = new int[(int) n];
//        int[] nser = new int[(int) n];
//
//        nsel(hist, n, nsel);
//        nser(hist, n, nser);
//
//        long maxArea = 0;
//
//        for (int i = 0; i < (int) n; i++) {
//            maxArea = Math.max(maxArea, (nser[i] - nsel[i] - 1) * hist[i]);
//        }
//        return maxArea;
//    }

    // end


    // in single pass solution
    public static long maximumArea(long hist[], long n) {
        long maxArea = 0;
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < (int) n; i++) {

            long ele = hist[i];

            while (!st.isEmpty() && hist[st.peek()] > ele) {
                int idx = st.pop();
                int rb = i;
                int lb = -1; // if left boundary doesn't exist than it is -1
                if (st.size() > 0) lb = st.peek();

                maxArea = Math.max(maxArea, (rb - lb - 1) * hist[idx]);
            }

            st.push(i);
        }

        while (!st.isEmpty()) {
            int idx = st.pop();
            int rb = (int) n; // right boundary doesn't exist than it is n
            int lb = -1;
            if (st.size() > 0) lb = st.peek();
            maxArea = Math.max(maxArea, (rb - lb - 1) * hist[idx]);
        }
        return maxArea;
    }
}

public class LargestHistogramArea {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine().trim());
        String[] inputLine = br.readLine().trim().split(" ");
        long[] arr = new long[(int) n];
        for (int i = 0; i < n; i++) arr[i] = Long.parseLong(inputLine[i]);
        System.out.println(new Solution().maximumArea(arr, n));
    }
}
