package SlidingWindow;

import java.util.*;

public class LongestSubstringWithAllUniqueCharacters {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
       
        System.out.println(longestSubstr(s, s.length()));
    }

    public static int longestSubstr(String s, int n) {
        int i = 0;
        int j = 0;

        int ans = 0;

        HashMap<Character,Integer> map = new HashMap<>();

        while(j<n){
            char c = s.charAt(j);
            map.put(c,map.getOrDefault(c,0)+1);

            // here k is not given so we will have to think of condition on our own so we want all unique chars so map size should be equal to window size than we have all unique chars in that window
            if(map.size() == (j-i+1)){
                ans = Math.max(ans,map.size());
                j++;
            }

            // if our window size is greater than map size that means we have some duplicate char in our window size so side the window now to get more answers 
            else if((j-i+1) > map.size()){
                while((j-i+1) > map.size()){
                    c = s.charAt(i);
                    if(map.containsKey(c)){
                        map.put(c,map.getOrDefault(c,0)-1);

                        if(map.get(c) == 0){
                            map.remove(c);
                        }
                    }
                    i++;
                }
                j++;
            }
            
        }
        return ans;
    }
}