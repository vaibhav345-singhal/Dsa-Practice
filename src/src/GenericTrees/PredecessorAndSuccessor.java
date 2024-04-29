package GenericTrees;

import java.util.Scanner;

import GenericTrees.Tree.Node;

public class PredecessorAndSuccessor {

    private static Node predecessor;
    private static Node successor;
    private static int state;

    public static void main(String[] args) {
        Node root = Tree.makeTree();
        // Tree.display(root);
        predecessor = null;
        successor = null;
        state = 0;

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter Node Data :  ");
            int data = sc.nextInt();

            solve(root, data);
        }
        if (predecessor == null) {
            System.out.println("Predecessor is null  and  Successor is " + successor);
        } else if (successor == null) {
            System.out.println("Predecessor is " + predecessor + "  and  Successor is null");
        } else {
            System.out.println("Predecessor is " + predecessor + "  and  Successor is " + successor);
        }
    }

    public static void solve(Node node, int data) {

        if (state == 0) {
            if (node.data == data) {
                state = 1;
            } else {
                predecessor = node;
            }
        } else if (state == 1) {
            successor = node;
            state = 2;
        }

        for (Node child : node.children) {
            solve(child, data);
        }
    }
}
