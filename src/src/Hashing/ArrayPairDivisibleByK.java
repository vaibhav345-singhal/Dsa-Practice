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

    // this will work for all positive and negative numbers
    public boolean arrayPairBetter(int[] arr, int k){

        HashMap<Integer,Integer> map = new HashMap<>();

        for(int num : arr){
            int rem = num %k;

            // for negative 
            if(rem <0) rem+=k;

            map.put(rem,map.getOrDefault(rem,0)+1);
        }


        for(int num : arr){
            int rem = num %k;

            if(rem == 0){ // if rem is zero than it should be even to make pair
                int freq = map.get(0);
                if(freq % 2 != 0) return false;
            }
            else if(2*rem == k){
                    // dont use k/2 because it will work only if num%rem == k/2 so that its k-rem is also k/2 but if num is like 11 than its 11%2 = 5 that is not equal to its complementry rem which is k - rem = 6 
                int freq = map.getOrDefault(rem,0);
                if(freq % 2 != 0) return false;
            }else{ // rem and k-rem freq should be equal to make pair
                int freq = map.getOrDefault(rem,0);
                int complementryFreq = map.getOrDefault(k-rem,0);
                if(freq != complementryFreq) return false;
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