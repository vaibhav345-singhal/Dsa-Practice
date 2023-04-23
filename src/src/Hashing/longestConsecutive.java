package Hashing;

import java.util.*;

public class longestConsecutive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(longestConsecutiveSol(arr));
    }

    public static int longestConsecutiveSol(int[] nums) {

        // making hashset
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int longStreak = 0;
        for (int num : nums) {

            // if num-1 exists meaning we will have to start our counting from that so we
            // will keep finding till when we will not found num-1 than
            // we will start counting the sequence. O(3n)
            if (!set.contains(num - 1)) {
                int currNum = num;
                int currStreak = 0;

                while (set.contains(currNum)) {
                    currNum += 1;
                    currStreak += 1;
                }

                longStreak = Math.max(longStreak, currStreak);
            }
        }
        return longStreak;
    }
}
