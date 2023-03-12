package Stack;

import java.util.Scanner;
import java.util.Stack;

class solution {
    int findCelebrity(int M[][], int n) {
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            st.push(i);
        }

        // keep doing this till only one person remains
        while (st.size() > 1) {

            int p1 = st.pop();
            int p2 = st.pop();

            if (M[p1][p2] == 1) { // means p1 knows p2 than it can't be celeb so put p2 in stack
                st.push(p2);
            }
            if (M[p1][p2] == 0) { // if p1 doesn't know p2 than p2 can't be celeb for sure
                st.push(p1);
            }
        }

        int pceleb = st.pop();

        for (int col = 0; col < n; col++) {
            if (M[pceleb][col] == 1) {
                pceleb = -1;
                break;
            }
        }

        for (int row = 0; row < n; row++) {
            if (row != pceleb && pceleb != -1 && M[row][pceleb] == 0) {
                pceleb = -1;
                break;
            }
        }

        return pceleb;

    }
}

public class CelebrityProblem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M[][] = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                M[i][j] = sc.nextInt();
        System.out.println(new solution().findCelebrity(M, N));
    }
}
