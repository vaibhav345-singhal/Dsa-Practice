package Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class IEAC {

    public int precedence(char c) {
        if (c == '+' || c == '-') return 1;
        else if (c == '*' || c == '/') return 2;
        else return -1;
    }

    public int evaluation(int v1, int v2, char c) {
        if (c == '+') return v1 + v2;
        else if (c == '-') return v2 - v1;
        else if (c == '*') return v2 * v1;
        else if (c == '/') return v2 / v1;
        else return 0;
    }

    public void evaluate(String exp) {

        Stack<Character> operator = new Stack<>();
        Stack<Integer> operand = new Stack<>();
        Stack<String> prefix = new Stack<>();
        Stack<String> postfix = new Stack<>();

        for (char c : exp.toCharArray()) {
            if (c == '(') {
                operator.push(c);
            } else if (Character.isDigit(c)) {
                operand.push(c - '0');
                // to evaluate prefix and post fix string
                prefix.push(c + "");
                postfix.push(c + "");
            } else if (c == ')') {
                while (operator.peek() != '(') { // run loop till we get opening bracket on top
                    char op = operator.pop();
                    int v1 = operand.pop();
                    int v2 = operand.pop();

                    int ans = evaluation(v1, v2, op);
                    operand.push(ans); // add ans in stack to cal further

                    // add these strings into there stacks
                    String p2 = prefix.pop();
                    String p1 = prefix.pop();
                    String pre = op + p1 + p2 + "";

                    String s2 = postfix.pop();
                    String s1 = postfix.pop();
                    String post = s1 + s2 + op + "";

                    prefix.push(pre);
                    postfix.push(post);
                }
                operator.pop(); // remove open bracket
            } else { // if its a operator
                while (!operator.isEmpty() && operator.peek() != '(' && precedence(c) <= precedence(operator.peek())) {
                    // evaluate that operator first whose present in the stack and have higher precedence
                    char op = operator.pop();
                    int v1 = operand.pop();
                    int v2 = operand.pop();

                    int ans = evaluation(v1, v2, op);
                    operand.push(ans);

                    String p2 = prefix.pop();
                    String p1 = prefix.pop();
                    String pre = op + p1 + p2 + "";

                    String s2 = postfix.pop();
                    String s1 = postfix.pop();
                    String post = s1 + s2 + op + "";
                    prefix.push(pre);
                    postfix.push(post);
                }
                operator.push(c);
            }
        }
        while (!operator.isEmpty()) {
            char op = operator.pop();
            int v1 = operand.pop();
            int v2 = operand.pop();
            int ans = evaluation(v1, v2, op);

            operand.push(ans);

            String p2 = prefix.pop();
            String p1 = prefix.pop();
            String pre = op + p1 + p2 + "";

            String s2 = postfix.pop();
            String s1 = postfix.pop();
            String post = s1 + s2 + op + "";

            prefix.push(pre);
            postfix.push(post);
        }
        System.out.println(operand.peek());
        System.out.println(postfix.peek());
        System.out.println(prefix.peek());

    }
}

public class InfixEvaluationAndConversion {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();
        IEAC ob = new IEAC();
        ob.evaluate(exp);
    }
}
