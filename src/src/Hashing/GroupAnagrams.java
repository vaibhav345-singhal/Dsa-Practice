package Hashing;

// A Java program to print all anagrams together
import java.util.*;

public class GroupAnagrams {
    // Given a list of words in wordArr[],
    static void printAnagramsTogether(String word[], int size) {
        List<List<String>> ans = new ArrayList<>();

        HashMap<String, List<String>> map = new HashMap<>();

        // lets create code first for every word to store them in map because freq map
        // will be same for all
        // anagrams

        for (String s : word) {

            int[] charArr = new int[26]; // space is O(26) which is constant
            for (char c : s.toCharArray()) {
                charArr[c - 'a'] += 1;
            }

            // creating code for string 
            StringBuilder code = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                if (charArr[i] > 0) {
                    code.append((char) (i + 'a'));
                    code.append(charArr[i]);
                }
            }
            String codes = code.toString();

            // checking if we have it in map or not
            if (!map.containsKey(codes)) {
                List<String> list = new ArrayList<>();
                list.add(s);
                map.put(codes, list);
            } else {
                List<String> list = map.get(codes);
                list.add(s);
                map.put(codes, list);
            }
        }

        for (String key : map.keySet()) {
            List<String> list = map.get(key);
            ans.add(list);
        }
        System.out.println(ans);
    }

    // Driver program to test above functions
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] wordArr = new String[n];
        for (int i = 0; i < n; i++)
            wordArr[i] = sc.next();
        sc.close();
        printAnagramsTogether(wordArr, n);
    }
}