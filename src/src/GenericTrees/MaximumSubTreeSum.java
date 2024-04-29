package GenericTrees;

import GenericTrees.Tree.Node;

public class MaximumSubTreeSum {

    private static int msn = 0;
    private static int ms = Integer.MIN_VALUE;

    public static void main(String[] args) {

        int[] arr = { 10, 20, -50, -1, -60, -1, -1, 30, -70, -1, 80, -110, -1, 120, -1, -1, 90, -1, -1, 40, -100, -1, -1,
                -1 };
        Node root = Tree.makeTreeInput(arr);
        // Tree.display(root);
        maximumSubTreeSum(root);
        System.out.println(msn);
    }

    public static int maximumSubTreeSum(Node node){

        int sum = 0;

        for(Node child : node.children){
            int csum = maximumSubTreeSum(child);

            sum += csum;
        }

        sum += node.data;

        if(sum > ms){
            msn = node.data;
            ms = sum;
        }
        return sum;
    }

}
