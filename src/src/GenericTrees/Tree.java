package GenericTrees;

import java.util.*;

public class Tree {

    static class Node {
        int data;
        List<Node> children;

        Node(int data) {
            this.data = data;
            children = new ArrayList<>();
        }

        @Override
        public String toString() {
            return ""+data;
        }
    }

    private static Node root = null;

    public static Node build(int[] arr) {

        Stack<Node> st = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            Node node = new Node(arr[i]);

            if (arr[i] == -1) {
                st.pop();
            } else {
                if (st.size() > 0) {
                    st.peek().children.add(node);
                } else {
                    root = node;
                }
                st.push(node);
            }
        }
        return root;
    }

    public static void display(Node node) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(node.data + " -> ");

        for (Node child : node.children) {
            stringBuilder.append(child.data + ",");
        }
        stringBuilder.append(" .");

        System.out.println(stringBuilder.toString());

        for (Node child : node.children) {
            display(child);
        }
    }

    public static Node makeTree() {
        int[] arr = { 10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120, -1, -1, 90, -1, -1, 40, 100, -1, -1,
                -1 };
        return build(arr);
    }

    public static Node makeTreeInput(int[] arr){
        return build(arr);
    }
}