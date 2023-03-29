package BinaryTree;

import java.util.LinkedList;
        import java.util.Queue;
        import java.io.*;
        import java.util.*;

public class AllNodesAtDistanceKFromTargetNode {
    static Node buildTree(String str){
        // System.out.print(str);
        // if(str.length()==0 || str.charAt(0)==''N''){
        //     return null;
        // }
        String ip[] = str.split(" ");
        Node root = new Node(Integer.parseInt(ip[0]));
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while(queue.size()>0 && i < ip.length) {
            Node currNode = queue.peek();
            queue.remove();
            String currVal = ip[i];
            if(!currVal.equals("N")) {
                currNode.left = new Node(Integer.parseInt(currVal));
                queue.add(currNode.left);
            }
            i++;
            if(i >= ip.length)
                break;
            currVal = ip[i];
            if(!currVal.equals("N")) {
                currNode.right = new Node(Integer.parseInt(currVal));
                queue.add(currNode.right);
            }
            i++;
        }
        return root;
    }
    void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    public static void main (String[] args) throws IOException{
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();
        int n = sc.nextInt();
        int K = sc.nextInt();
        //System.out.print(s);
        Node root = buildTree(s);
        Solution4 tree = new Solution4();
        ArrayList<Integer> ans = tree.KDistanceNodes(root,n,K);
        Collections.sort(ans);
        for(int i = 0 ; i < ans.size() ; ++i){
            System.out.print(ans.get(i) + " ");
        }

    }
}



class Solution4{
    public static void getChildParent(Node node, HashMap<Node,Node> getParent){
        if(node == null) return;

        if(node.left != null){
            getParent.put(node.left,node);
        }

        if(node.right != null){
            getParent.put(node.right,node);
        }

        getChildParent(node.left,getParent);
        getChildParent(node.right,getParent);
    }

    public static Node TargetNode(Node node,int target){
        if(node == null) return null;

        if(node.data == target) return node;

        Node lpart = TargetNode(node.left,target);
        Node rpart = TargetNode(node.right,target);

        if(lpart != null && rpart != null) return node;

        if(lpart != null) return lpart;
        else if(rpart != null) return rpart;
        else return null;
    }

    public static ArrayList<Integer> KDistanceNodes(Node root, int target , int k){
        ArrayList<Integer> ans = new ArrayList<>();
        if(root == null) return ans;

        HashMap<Node,Node> getParent = new HashMap<>();

        getChildParent(root,getParent);

        Node src = TargetNode(root,target);

        HashSet<Node> vis = new HashSet<>();

        Queue<Node> q = new LinkedList<>();
        q.add(src);
        vis.add(src);


        int level = 0;

        while(q.size() != 0){
            int size = q.size();
            if(level == k ){
                while(q.size() != 0){
                    ans.add(q.poll().data);
                }
                break;
            }
            while(size-->0){
                Node node = q.poll();
                if(node.left != null && vis.contains(node.left) == false){
                    q.add(node.left);
                    vis.add(node.left);
                }

                if(node.right != null && vis.contains(node.right) == false){
                    q.add(node.right);
                    vis.add(node.right);
                }

                if(getParent.containsKey(node) && vis.contains(getParent.get(node)) == false){
                    q.add(getParent.get(node));
                    vis.add(getParent.get(node));
                }
            }
            level++;
        }
        return ans;
    }

}


