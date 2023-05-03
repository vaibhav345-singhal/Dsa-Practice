package Stack;

import java.util.*;

class MinStack {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T > 0) {
            int q = sc.nextInt();
            Solution g = new Solution();
            while (q > 0) {
                int qt = sc.nextInt();

                if (qt == 1) {
                    int att = sc.nextInt();
                    g.push(att);
                } else if (qt == 2) {
                    System.out.print(g.pop() + " ");
                } else if (qt == 3) {
                    System.out.print(g.getMin() + " ");
                }

                q--;
            }
            System.out.println();
            T--;
        }
        sc.close();

    }
}

// Note: Methods pop, top and getMin operations will always be called on
// non-empty stacks.
// O(2N)

class Solution {

    static class Pair {
        int val;
        int min;

        Pair(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }

    Stack<Pair> st;

    Solution() {
        st = new Stack<Pair>();
    }

    void push(int val) {
        if (st.empty()) {
            st.push(new Pair(val, val));
        } else {
            int min = Math.min(val, st.peek().min);
            st.push(new Pair(val, min));
        }
    }

    int pop() {
        return st.peek().val;
    }

    int getMin() {
        return st.peek().min;
    }
}

//Better Solution
// Push (2*val - min)
// pop=> return minEle and prevMinEle =  (2 * min - val)
class Sol {
    int minEle;
    Stack<Integer> s;

    Sol() {
        s = new Stack<Integer>();
    }

    void push(int x) {
        if (s.empty()) {
            minEle = x;
            s.push(x);
        } else {
            if (x < minEle) {
                int valToPush = (2 * x - minEle);
                s.push(valToPush);
                minEle = x;
            } else {
                s.push(x);
            }
        }
    }

    int pop() {
        if (!s.empty()) {
            if (s.peek() < minEle) {
                int prevMin = 2 * minEle - s.pop();
                int originalValThatShouldBePresentOnTop = minEle;
                minEle = prevMin;
                return originalValThatShouldBePresentOnTop;
            } else {
                return s.pop();
            }
        }
        return -1;
    }

    int getMin() {
        return minEle;
    }
}