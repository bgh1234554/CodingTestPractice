package AlgoMap_io.Trees;

import java.util.LinkedList;
import java.util.Queue;

/*
Given the root of a binary tree, return the length of the diameter of the tree.

The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
This path may or may not pass through the root.

The length of a path between two nodes is represented by the number of edges between them.
 */
public class Leetcode543 {
    public static void main(String[] args) {

    }
    //BFS와 maxDepth로 푼 어거지 풀이 - 풀이시간 218ms
    public int diameterOfBinaryTree(TreeNode root) {
        if(root==null) return 0;
        int maxDepth = 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int q = queue.size();
            for(int i=0;i<q;i++) {
                TreeNode node = queue.poll();
                int leftpath = 0;
                int rightpath = 0;
                if(node.left!=null){
                    queue.add(node.left);
                    leftpath = maxDepth(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                    rightpath = maxDepth(node.right);
                }
                maxDepth = Math.max(maxDepth,leftpath+rightpath);
            }
        }
        return maxDepth;
    }
    public static int maxDepth(TreeNode root) {
        if(root==null) return 0;
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }
    //maxDepth 대신에 더 최적화되었고, 재귀적인 height 함수 만들기
    private int largestDiameter = 0;

    public int diameterOfBinaryTreeAlgomapIo(TreeNode root) {
        height(root);
        return largestDiameter;
    }

    private int height(TreeNode node) {
        if (node == null) return 0;
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        int diameter = leftHeight + rightHeight;
        largestDiameter = Math.max(largestDiameter, diameter);

        return 1 + Math.max(leftHeight, rightHeight);
    }
}
