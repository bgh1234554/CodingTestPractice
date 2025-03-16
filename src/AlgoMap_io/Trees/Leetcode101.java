package AlgoMap_io.Trees;

import java.util.LinkedList;
import java.util.Queue;
/*
Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 */
public class Leetcode101 {
    public static void main(String[] args) {

    }
    //BFS로 짠 내 풀이 - 실행시간 1ms
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        if(root.left == null && root.right == null) return true;
        else if(root.left == null || root.right == null) return false;
        else if(root.left.val != root.right.val) return false;
        Queue<TreeNode> leftq = new LinkedList<>();
        Queue<TreeNode> rightq = new LinkedList<>();
        leftq.add(root.left); rightq.add(root.right);
        while(!leftq.isEmpty() && !rightq.isEmpty()) {
            int leftSize = leftq.size();
            for(int i = 0; i < leftSize; i++) {
                TreeNode l = leftq.poll();
                TreeNode r = rightq.poll();
                if(l == null && r == null) continue;
                else if(l == null || r == null) return false;
                if (l.val != r.val) return false;
                leftq.add(l.left); rightq.add(r.right);
                leftq.add(l.right); rightq.add(r.left);
            }
        }
        return leftq.isEmpty() && rightq.isEmpty();
    }
    //DFS로 짠 풀이 - 실행시간 0ms
    public boolean isSymmetricRecursive(TreeNode root) {
        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        if (t1.val != t2.val) return false;

        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }
}
/*
왜 DFS식 재귀적인 풀이는 생각이 바로 안날까
 */