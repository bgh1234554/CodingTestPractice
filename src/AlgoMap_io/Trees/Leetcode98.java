package AlgoMap_io.Trees;
/*
Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

    The left subtree of a node contains only nodes with keys less than the node's key.
    The right subtree of a node contains only nodes with keys greater than the node's key.
    Both the left and right subtrees must also be binary search trees.
 */
public class Leetcode98 {
    public static void main(String[] args) {
        //틀린 케이스
        //[0,-1]
        //[5,4,6,null,null,3,7]
    }
    //틀린 케이스 두번째를 풀 수가 없다. 단순히 부모, 자식 간의 대소만 비교하기 때문이다.
    public boolean isValidBSTWrong(TreeNode root) {
        if(root.left == null && root.right == null) {return true;}
        else if(root.left==null) {return root.right.val>root.val;}
        else if(root.right==null) {return root.left.val<root.val;}
        return root.val>root.left.val&&root.val<root.right.val&& isValidBSTWrong(root.left) && isValidBSTWrong(root.right);
    }
    //Algomap.io에서 푼 올바른 버전
    //새로운 재귀 함수를 통해 최대 최소 값을 인수로 넘길 수 있게 해서,
    //재귀 함수로 호출한다고 하더라도 값이 만족해야하는 범위에 대해 검사할 수 있다.
    public boolean isValidBST(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValid(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }

        if (node.val <= min || node.val >= max) {
            return false;
        }

        return isValid(node.left, min, node.val) && isValid(node.right, node.val, max);
    }
}
