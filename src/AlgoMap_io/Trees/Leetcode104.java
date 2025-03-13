package AlgoMap_io.Trees;

import java.util.LinkedList;
import java.util.Queue;

/*
Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes
along the longest path from the root node down to the farthest leaf node.
 */
public class Leetcode104 {
    public static void main(String[] args) {

    }
    //재귀적으로 생각하면 풀기 쉽다.
    //대놓고 DFS는 아니지만, 사실상의 DFS로 구현한 것.
    public static int maxDepth(TreeNode root) {
        if (root==null) return 0;
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }
    //BFS로 구현한 버전
    public static int maxDepthBFS(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if(root!=null) queue.add(root);
        int depth = 0;
        while(!queue.isEmpty()) {
            //visited 배열이 없으니까 미리 부모 노드를 뽑지 않고,
            //다른 방식으로 구현
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(node.left!=null) queue.add(node.left);
                if(node.right!=null) queue.add(node.right);
            }
            depth++;
        }
        return depth;
    }
}
