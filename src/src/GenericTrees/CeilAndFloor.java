package GenericTrees;

import java.util.Scanner;

import GenericTrees.Tree.Node;

public class CeilAndFloor {

    static int ceil;
    static int floor;

    public static void main(String[] args) {
        Node root = Tree.makeTree();
        ceil = Integer.MAX_VALUE; // smallest amongst the largest
        floor = Integer.MIN_VALUE; // largest among the smaller

        Scanner sc = new Scanner(System.in);
        int data = sc.nextInt();

        ceilAndFloor(root, data);

        System.out.println("ceil is " + ceil + " floor is "+ floor);

        int k = 3; // means 3rd largest
        System.out.println("kth largest " + KthLargest(root, k));
    }

    public static void ceilAndFloor(Node node, int data) {

        if (node.data > data) {
            if (node.data < ceil) {
                ceil = node.data;
            }
        }

        if (node.data < data) {
            if (node.data > floor) {
                floor = node.data;
            }
        }

        for (Node child : node.children) {
            ceilAndFloor(child, data);
        }
    }

    public static int KthLargest(Node node , int k){
        floor = Integer.MIN_VALUE;
        int factor = Integer.MAX_VALUE;

        for(int i=0; i<k; i++){
            ceilAndFloor(node, factor);
            factor = floor;
            floor = Integer.MIN_VALUE;
        }
        return factor;
    }
}
