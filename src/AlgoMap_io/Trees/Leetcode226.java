package AlgoMap_io.Trees;
/*
Given the root of a binary tree, invert the tree, and return its root.
 */
public class Leetcode226 {
    public static void main(String[] args) {

    }
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}
/*
트리를 좌우로 뒤집어버리기
 */