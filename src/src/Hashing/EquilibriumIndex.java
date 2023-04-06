package Hashing;
import java.util.*;

public class EquilibriumIndex {
    public static void main(String[] args) throws Throwable {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int ans = Solution3.findEquilibriumIndex(a);
        System.out.println(ans);
        System.out.println("Better Solution "+ Solution3.findEquilibriumIndexBetter(a));
    }
}

class Solution3 {
    // in this method using two extra array that is not necessary
    static int findEquilibriumIndex(int[] a) {
        int n = a.length;

		int[] lsum = new int[n];
		lsum[0] = 0;
		for(int i=1;i<n;i++){
			lsum[i] = lsum[i-1]+a[i-1];
		}

		int[] rsum = new int[n];
		rsum[n-1] = 0;
		for(int i=n-2; i>=0; i--){
			rsum[i] = rsum[i+1] + a[i+1];
		}
		
		for(int i=0; i<n;i++){
			if(lsum[i] == rsum[i]) return i;
		}
		return -1;
    }

    // in this we are not using any extra space

    static int findEquilibriumIndexBetter(int[] a){
        int n = a.length;
        int totalSum = 0;
        for(int i=0; i<n; i++){
            totalSum += a[i];
        }

        int rsum = totalSum;
        int lsum = 0;

        for(int i=0; i<n;i++){
            // right sum for curr element
            rsum -= a[i];

            if(lsum == rsum) return i;

            // calculating left sum for next element
            lsum += a[i];
        }
        return -1;
    }
}