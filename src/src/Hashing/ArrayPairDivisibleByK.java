package Hashing;

// if we have only positive values in array than this solution will work

import java.util.*;

class Solution {
    public boolean arrayPairs(int[] arr, int k) {
        // two numbers are divisible by k if they are indivisually divisible by k
        // and if they are not divisible indivisually than there sum should be divisible
        // by k like (a+b)%k ==0 . for this lets say after dividing a remainder is
        // r1 and after b remainder is r2 than to be divisible by k their remainder sum
        // should
        // be equal to k or multiple of k

        HashMap<Integer, Integer> map = new HashMap<>();

        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int rem = arr[i] % k;
            map.put(rem, map.getOrDefault(rem, 0) + 1);
        }

        for (int i = 0; i < k; i++) {
            if (i == 0) {
                if (map.getOrDefault(i, 0) % 2 != 0) {
                    return false;
                }
            } else if (map.getOrDefault(i, 0) != map.getOrDefault(k - i, 0)) {
                return false;
            }
        }
        return true;
    }
}

public class ArrayPairDivisibleByK {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, k;
        n = sc.nextInt();
        k = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        Solution Obj = new Solution();
        if (Obj.arrayPairs(arr, k)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}