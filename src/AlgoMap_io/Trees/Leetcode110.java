package AlgoMap_io.Trees;
/*
Given a binary tree, determine if it is height-balanced.

A height-balanced binary tree is a binary tree
in which the depth of the two subtrees of every node never differs by more than one.
 */
public class Leetcode110 {
    public static void main(String[] args) {

    }
    //이전에 사용한 maxDepth를 이용한 풀이 - 실행시간 1ms
    public static boolean isBalanaced(TreeNode root) {
        if (root==null) return true;
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        if(Math.abs(leftDepth-rightDepth)>1) return false;
        //return true;로 끝내면 root 노드에 대해서만 검사하니까 틀린다.
        return isBalanaced(root.left) && isBalanaced(root.right);
    }
    public static int maxDepth(TreeNode root) {
        if (root==null) return 0;
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }
    //Algomap.io의 풀이 - 실행시간 0ms
    public boolean isBalanced2(TreeNode root) {
        return height(root) != -1;
    }

    //-1 => balanced tree가 아닌 경우 쓰는 정수라고 보면 됨.
    //-1인 상황인 경우, depth 리턴할 필요 없이 그냥 -1 리턴.
    private int height(TreeNode node) {
        if (node == null) return 0;

        int leftHeight = height(node.left);
        if (leftHeight == -1) return -1;

        int rightHeight = height(node.right);
        if (rightHeight == -1) return -1;

        //문제가 발생 -> balanced tree가 아님
        //이 때는 -1을 리턴하고 함수 종료.
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return 1 + Math.max(leftHeight, rightHeight);
    }
}
