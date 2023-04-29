package SlidingWindow;

import java.util.*;

public class LongestSubstringWithKUniqueCharacters {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int k = sc.nextInt();

        System.out.println(longestkSubstr(s, s.length(), k));
    }

    public static int longestkSubstr(String s, int n, int k) {
        int i = 0;
        int j = 0;

        int max = -1;
        // storing characters frequency so that we can use that to determine our answer 
        HashMap<Character, Integer> map = new HashMap<>();

        while (j < n) {
            // storing the frequency 
            char c = s.charAt(j);
            map.put(c, map.getOrDefault(c, 0) + 1);

            // if size is less than k than increase j and store more characters 
            if (map.size() < k) {
                j++;
            }
            // if map size is equals to k than calculate the answer and increase j 
            else if (map.size() == k) {

                max = Math.max(max, j - i + 1);
                j++;
            }
            // and if map size is greater than k than we will have to start removing the characters from i th position so that we can calculate further answers  
            else if (map.size() > k) {
                while (map.size() > k) {
                    c = s.charAt(i);
                    map.put(c, map.getOrDefault(c, 0) - 1);

                    if (map.get(c) == 0) {
                        map.remove(c);
                    }
                    i++;
                }
                j++;
            }
        }
        return max;
    }

}
