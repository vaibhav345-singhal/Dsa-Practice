package SlidingWindow;

import java.util.*;

public class MinimumWindowSubstring {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.next();

        String t = sc.next();

        System.out.println(findMinWindowSubstring(s, t));
    }

    private static String findMinWindowSubstring(String s, String t) {

        HashMap<Character, Integer> map = new HashMap<>();
        for (int x = 0; x < t.length(); x++) {
            char c = t.charAt(x);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int i = 0;
        int j = 0;
        int min = Integer.MAX_VALUE;
        int temp = min;
        // so that we can avoid map traversing again and again
        int count = map.size();

        // to keep track the window starting index
        int start = 0;

        while (j < s.length()) {
            char c = s.charAt(j);

            // if char exist in the map than reduce the count by one nad if it becomes zero
            // than reduce count by one
            if (map.containsKey(c)) {
                map.put(c, map.getOrDefault(c, 0) - 1);
                if (map.get(c) == 0) {
                    count--;
                }
            }

            // if count > 0 than increase j because we didnt find the answer
            if (count > 0) {
                j++;
            }

            // if count is zero than we found a answer store it in min and update start with
            // i to keep track of window starting index
            if (count == 0) {
                // to compare with min so that we can update our start pointer
                temp = min;
                min = Math.min(min, j - i + 1);
                if (temp != min) {
                    start = i;
                }

                // now we got the all chars but we want to minimize it so we will now start
                // moving the i pointer if we have that char freq in negative in our map and
                // while doing this count becomes the > 0 than it means we now dont have the all
                // chars in our window
                while (count == 0) {
                    char c2 = s.charAt(i);
                    if (map.containsKey(c2)) {
                        map.put(c2, map.getOrDefault(c2, 0) + 1);
                        if (map.get(c2) == 1) {
                            count++;
                        }
                    }
                    i++;
                    // after coming out from while loop if our window size is updated than we will
                    // store it and update the start pointer
                    if (count == 0) {
                        min = Math.min(min, j - i + 1);

                        if (temp != min) {
                            start = i;
                        }
                    }
                }
                // slide the window to search for other answers
                j++;
            }
        }

        if (min == Integer.MAX_VALUE) {
            return "";
        }

        return s.substring(start, start + min);

    }

}
