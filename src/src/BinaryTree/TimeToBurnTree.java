package BinaryTree;

import java.util.*;

public class TimeToBurnTree {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) arr[i] = sc.next();
        int k = sc.nextInt();
        sc.close();
        BinaryTreeNode<Integer> tree = levelOrderCreateTree(arr);
        System.out.println(timeToBurnTree(tree, k));
    }

    public static void getParent(BinaryTreeNode<Integer> root, HashMap<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> parent) {
        if (root == null) return;

        if (root.left != null) {
            parent.put(root.left, root);
        }

        if (root.right != null) {
            parent.put(root.right, root);
        }

        getParent(root.left, parent);
        getParent(root.right, parent);

    }

    public static BinaryTreeNode<Integer> find(BinaryTreeNode<Integer> root, int target) {
        if (root == null) return null;

        if (root.data == target) {
            return root;
        }

        BinaryTreeNode<Integer> l = find(root.left, target);
        BinaryTreeNode<Integer> r = find(root.right, target);

        if (l != null && r != null) {
            return root;
        }

        if (l != null) return l;
        else if (r != null) return r;
        else return null;
    }

    public static int timeToBurnTree(BinaryTreeNode<Integer> root, int target) {
        HashMap<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> parent = new HashMap<>();

        getParent(root, parent);

        BinaryTreeNode<Integer> src = find(root, target);

        int level = 0;

        HashSet<BinaryTreeNode<Integer>> vis = new HashSet<>();

        Queue<BinaryTreeNode<Integer>> q = new LinkedList<>();
        q.add(src);
        vis.add(src);


        while (q.size() != 0) {
            int size = q.size();

            while (size-- > 0) {
                BinaryTreeNode<Integer> node = q.poll();

                if (node.left != null && vis.contains(node.left) == false) {
                    q.add(node.left);
                    vis.add(node.left);
                }

                if (node.right != null && vis.contains(node.right) == false) {
                    q.add(node.right);
                    vis.add(node.right);
                }

                if (parent.containsKey(node) && vis.contains(parent.get(node)) == false) {
                    q.add(parent.get(node));
                    vis.add(parent.get(node));
                }

            }
            level++;
        }
        return level - 1;
    }

    static void createTree(BinaryTreeNode<Integer> node, int i, String[] arr) {
        if (node != null) {
            if (2 * i + 1 < arr.length) {
                if (arr[2 * i + 1].equals("null")) {
                    node.left = null;
                } else {
                    node.left = new BinaryTreeNode<>(Integer.parseInt(arr[2 * i + 1]));
                }
                createTree(node.left, 2 * i + 1, arr);
            }
            if (2 * i + 2 < arr.length) {
                if (arr[2 * i + 2].equals("null")) {
                    node.right = null;
                } else {
                    node.right = new BinaryTreeNode<>(Integer.parseInt((arr[2 * i + 2])));
                }
                createTree(node.right, 2 * i + 2, arr);
            }
        }
    }

    static BinaryTreeNode<Integer> levelOrderCreateTree(String[] arr) {
        if (arr.length == 0) return null;
        BinaryTreeNode<Integer> head = new BinaryTreeNode<>(Integer.parseInt(arr[0]));
        createTree(head, 0, arr);
        return head;
    }
}

class BinaryTreeNode<T> {

    T data;
    BinaryTreeNode<T> left;
    BinaryTreeNode<T> right;

    public BinaryTreeNode(T data) {
        this.data = data;
    }
}