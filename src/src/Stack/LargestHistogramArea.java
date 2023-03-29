package Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class LHA {

    // with multiple passes start
    public static void nextSmallerElementOnLeft(int[] hist, int n, int[] nsel) {
        Stack<Integer> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            int ele = hist[i]; // curr element that is looking for its smaller element on left side

            while (!st.isEmpty() && ele < hist[st.peek()]) {
                // if curr ele is smaller than put its index in nsel
                nsel[st.pop()] = i;
            }

            // will push curr ele index so that it can find it's next smaller in next term
            st.push(i);
        }

        // these elements are those which couldn't find their next smaller ele on left so put -1
        while (!st.isEmpty()) {
            nsel[st.pop()] = -1;
        }
    }

    public static void nextSmallerElementOnRight(int[] hist, int n, int[] nser) {
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            int ele = hist[i];

            while (!st.isEmpty() && ele < hist[st.peek()]) {
                nser[st.pop()] = i;
            }
            st.push(i);
        }

        while (!st.isEmpty()) {
            nser[st.pop()] = n;
        }
    }

    public static int maximumAreaOld(int[] hist, int n) {
        int[] nsel = new int[n];
        int[] nser = new int[n];

        nextSmallerElementOnLeft(hist, n, nsel);
        nextSmallerElementOnRight(hist, n, nser);

        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            int newArea = (nser[i] - nsel[i] - 1) * hist[i];
            maxArea = Math.max(maxArea, newArea);
        }
        return maxArea;
    }

    // end


    // in single pass solution
    public static int maximumArea(int hist[], int n) {
        int maxArea = 0;
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            int ele = hist[i];

            while (!st.isEmpty() && ele < hist[st.peek()]) {
                maxArea = getMaxArea(hist, maxArea, st, i);
            }
            st.push(i);
        }
        while (st.size() > 0) {
            maxArea = getMaxArea(hist, maxArea, st, n);
        }

        return maxArea;
    }

    private static int getMaxArea(int[] hist, int maxArea, Stack<Integer> st, int i) {
        int idx = st.pop();
        int rb = i;
        int lb = -1;
        if (!st.isEmpty()) lb = st.peek();
        int width = rb - lb - 1;
        int area = hist[idx] * width;
        maxArea = Math.max(area, maxArea);
        return maxArea;
    }
}

public class LargestHistogramArea {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        String inputLine[] = br.readLine().trim().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(inputLine[i]);
//        System.out.println(new LHA().maximumAreaOld(arr, n));
        System.out.println(new LHA().maximumArea(arr, n));
    }
}
