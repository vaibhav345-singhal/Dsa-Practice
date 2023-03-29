package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.io.*;
import java.util.*;


public class LCA {
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
            if (!currVal.equals("-1")) {
                currNode.left = new Node(Integer.parseInt(currVal));
                queue.add(currNode.left);
            }
            i++;
            if (i >= ip.length) break;
            currVal = ip[i];
            if (!currVal.equals("-1")) {
                currNode.right = new Node(Integer.parseInt(currVal));
                queue.add(currNode.right);
            }
            i++;
        }

        return root;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        Node root = buildTree(s);
        int x = sc.nextInt();
        int y = sc.nextInt();
        Solution3 g = new Solution3();
        Node ans = g.findLCA(root, x, y);
        System.out.println(ans.data);
    }
}
class Solution3 {
    public static Node findLCA(Node node, int n1, int n2) {
        if (node == null) return null;

        if (node.data == n1 || node.data == n2) {
            return node;
        }

        Node lAns = findLCA(node.left, n1, n2);
        Node rAns = findLCA(node.right, n1, n2);

        if (lAns != null && rAns != null) return node;

        if (lAns != null) return lAns;

        if (rAns != null) return rAns;

        return null;
    }
}
