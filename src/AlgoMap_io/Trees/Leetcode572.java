package AlgoMap_io.Trees;

import java.util.LinkedList;
import java.util.Queue;
/*
Given the roots of two binary trees root and subRoot,
return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.

A subtree of a binary tree "tree" is a tree that consists of a node in tree and all of this node's descendants.
The tree "tree" could also be considered as a subtree of itself.
 */
public class Leetcode572 {
    public static void main(String[] args) {

    }
    public boolean isSubtree(TreeNode root, TreeNode subRoot){
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if(node.val==subRoot.val) {
                    boolean value = isIdentical(node, subRoot);
                    if(value) {return true;}
                }
                if(node.left!=null) q.add(node.left);
                if(node.right!=null) q.add(node.right);
            }
        }
        return false;
    }
    public boolean isIdentical(TreeNode s, TreeNode t){
        //base case를 짜는데 조금 걸렸다. null일때 부분 미리 처리.
        if(s == null && t == null) return true;
        if(s == null || t == null) return false;
        return s.val==t.val&&isIdentical(s.left, t.left)&&isIdentical(s.right, t.right);
    }
    /*
    BFS와 재귀를 모두 사용하는 문제이다.
    이 문제는 오히려 재귀적인 부분을 짜는 것은 어렵지 않았으나, 어떤 상황에서 그 재귀함수를
    호출해야하는지를 생각하는 것이 어려웠다.

    이후 BFS로 하나씩 노드를 탐색하면서 두 루트 노드의 값이 같으면 탐색을 시작하는 방법으로
    구현하는데 성공하였다. 실행시간도 대부분의 시간인 2ms이다.
     */
}
