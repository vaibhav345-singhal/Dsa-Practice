package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.io.*;
import java.util.*;
import java.lang.*;

class TreeNode{
    int data;
    TreeNode left;
    TreeNode right;
    TreeNode(int data){
        this.data = data;
        left=null;
        right=null;
    }
}

class Solution2 {
    class Pair implements Comparable<Pair> {
        int vlevel=0;
        TreeNode node;
        Pair(int vlevel,TreeNode node){
            this.vlevel = vlevel;
            this.node = node;
        }

        public int compareTo(Pair other){
            if(this.vlevel == other.vlevel){
                return this.node.data - other.node.data;
            }else{
                return this.vlevel - other.vlevel;
            }
        }
    }
    int minIdx = Integer.MAX_VALUE;
    int maxIdx = Integer.MIN_VALUE;
    public void getIdx(TreeNode node, int idx){
        if(node == null) return;

        minIdx = Math.min(minIdx,idx);
        maxIdx = Math.max(maxIdx,idx);

        getIdx(node.left,idx-1);
        getIdx(node.right,idx+1);
    }
    List<List<Integer>> VerticalTraversal(TreeNode root) {

        getIdx(root,0);

        int numberOfVerticalLevels = maxIdx - minIdx +1;

        List<List<Integer>> vo = new ArrayList<>();
        for(int i=0;i<numberOfVerticalLevels;i++){
            vo.add(new ArrayList<>());
        }


        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(-minIdx,root));

        while(pq.size() != 0){
            int size = pq.size();

            // adding childs in new child priority queue to process them
            // because if we will not take this another queue than it will remove child element without processing
            //other parent nodes due to our custom compareTo functionality

            PriorityQueue<Pair> Cpq = new PriorityQueue<>();
            while(size-- > 0){
                Pair rpair = pq.poll();

                vo.get(rpair.vlevel).add(rpair.node.data);

                if(rpair.node.left != null) Cpq.add(new Pair(rpair.vlevel-1,rpair.node.left));

                if(rpair.node.right != null) Cpq.add(new Pair(rpair.vlevel+1,rpair.node.right));
            }
            // making the child priority queue to main queue and vice versa
            pq = Cpq;
        }

        return vo;
    }
}

public class VerticalOrderTraversal {

    static TreeNode buildTree(String str) {
        if(str.length()==0 || str.charAt(0)=='N')
            return null;

        String ip[] = str.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(ip[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while(queue.size()>0 && i < ip.length) {
            TreeNode currNode = queue.peek();
            queue.remove();
            String currVal = ip[i];
            if(!currVal.equals("N")) {
                currNode.left = new TreeNode(Integer.parseInt(currVal));
                queue.add(currNode.left);
            }
            i++;
            if(i >= ip.length)
                break;
            currVal = ip[i];
            if(!currVal.equals("N")) {
                currNode.right = new TreeNode(Integer.parseInt(currVal));
                queue.add(currNode.right);
            }
            i++;
        }
        return root;
    }

    public static void main (String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
        while(t > 0){
            String s = br.readLine();
            TreeNode root = buildTree(s);
            Solution2 ob = new Solution2();
            List<List<Integer>> ans = ob.VerticalTraversal(root);
            for(int i = 0; i < ans.size(); i++) {
                for(int a : ans.get(i)) {
                    System.out.print(a + " ");
                }
                System.out.println();
            }
            // System.out.println();
            t--;
        }
    }
}