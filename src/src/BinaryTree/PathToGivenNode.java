package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.io.*;
import java.util.*;


public class PathToGivenNode {
    static Node buildTree(String str) {
        if (str.length() == 0 || str.charAt(0) == 'N') {
            return null;
        }
        String ip[] = str.split(" ");
        Node root = new Node(Integer.parseInt(ip[0]));
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (queue.size() > 0 && i < ip.length) {
            Node currNode = queue.peek();
            queue.remove();
            String currVal = ip[i];
            if (!currVal.equals("N")) {
                currNode.left = new Node(Integer.parseInt(currVal));
                queue.add(currNode.left);
            }
            i++;
            if (i >= ip.length)
                break;
            currVal = ip[i];
            if (!currVal.equals("N")) {
                currNode.right = new Node(Integer.parseInt(currVal));
                queue.add(currNode.right);
            }
            i++;
        }
        return root;
    }


    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String s = sc.nextLine();
        int b = sc.nextInt();
        Node root = buildTree(s);
        Solution tree = new Solution();
        ArrayList<Integer> ans = tree.solve(root, b);
        for (int i = 0; i < ans.size(); i++)
            System.out.print(ans.get(i) + " ");

        System.out.println();

        sc.close();
    }
}


class Solution {

    public boolean helper(Node root, int b, ArrayList<Integer> ans) {
        if (root == null) return false;

        if (root.data == b) {
            ans.add(root.data);
            return true;
        }

        boolean leftAns = helper(root.left, b, ans);
        if (leftAns == true) {
            ans.add(root.data);
            return true;
        }

        boolean rightAns = helper(root.right, b, ans);
        if (rightAns == true) {
            ans.add(root.data);
            return true;
        }
        return false;
    }

    public ArrayList<Integer> solve(Node root, int b) {
        ArrayList<Integer> ans = new ArrayList<>();
        helper(root, b, ans);
        Collections.reverse(ans);
        return ans;
    }
}
