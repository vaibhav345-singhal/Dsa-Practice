package Stack;

import java.util.*;

public class ValidParam {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(isValid(s));
    }

    public static boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(' || c == '{' || c == '[') {
                st.push(c);
            } else {
                if (st.empty())
                    return false;
                else if (checkTop(st, c))
                    st.pop();
                else
                    return false;
            }
        }
        if (st.empty())
            return true;
        else
            return false;
    }

    public static boolean checkTop(Stack<Character> st, char c) {
        if (st.peek() == '(' && c == ')')
            return true;
        else if (st.peek() == '{' && c == '}')
            return true;
        else if (st.peek() == '[' && c == ']')
            return true;
        else
            return false;
    }
}
