package Hashing;

import java.io.*;
import java.util.*;

class Solution2 {
	public int maxLen(int arr[]) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int n = arr.length;

		int maxLen = 0;
		int sum = 0;
		for (int i = 0; i < n; i++) {
			sum += arr[i];

			// if sum is zero means the whole elements till now giving zero sum
			// so we will take that index and add 1 to get length
			if (sum == 0) {
				map.put(sum, i);
				if (map.containsKey(sum)) {
					maxLen = Math.max(maxLen, map.get(sum) + 1);
				}
			}

			/*
			 * if sum is other than zero and if we encountering that again while traversing
			 * means we got the same sum so elements in between giving zero sum so we will
			 * take that length and update our maxLen and we will not update the index in
			 * map because we want largest length.
			 */

			if (map.containsKey(sum)) {
				maxLen = Math.max(maxLen, i - map.get(sum));
			} else {
				map.put(sum, i);
			}
		}
		return maxLen;
	}
}

public class LongestSubarrayWithZeroSum {

	public static void main(String arg[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = sc.nextInt();
		}
		Solution2 Obj = new Solution2();
		System.out.println(Obj.maxLen(nums));
	}
}
