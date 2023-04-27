package SlidingWindow;

import java.util.*;

public class countAnagrams {

    public static void main(String[] args) {
        String text = "forxxorfxdofr";
        String word = "for";
        System.out.println(countOccerancesAnagrams(text, word));
    }

    public static int countOccerancesAnagrams(String a, String b) {
        int ans = 0;

        HashMap<Character, Integer> map = new HashMap<>();

        for (char c : b.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // to track the characters so that we will not have to traverse the map
        // again and again to check if we got all the characters
        int count = map.size();

        // window size would be string length
        int k = b.length();

        int i = 0;
        int j = 0;

        int n = a.length();

        while (j < n) {

            // here we are doing calculation for our j 
            char c = a.charAt(j);
            if (map.containsKey(c)) {
                map.put(c, map.getOrDefault(c, 0) - 1);

                if (map.get(c) == 0) {
                    count--;
                }
            }

            if (j - i + 1 < k) {
                j++;
            } else if (j - i + 1 == k) {
                // if count zero means we got all characters. we are using count because we dont want to iterate over map again to check whether we found all char or not so we are using a var count
                if (count == 0) {
                    ans++;
                }
                // here we are removing our calculation for ith index so that we can slide our window
                c = a.charAt(i);

                if (map.containsKey(c)) {
                    map.put(c, map.getOrDefault(c, 0) + 1);

                    if (map.get(c) > 0) {
                        count++;
                    }
                }
                i++;
                j++;
            }
        }

        return ans;
    }

}
