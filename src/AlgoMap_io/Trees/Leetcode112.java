package AlgoMap_io.Trees;
/*
Given the root of a binary tree and an integer targetSum,
return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.

A leaf is a node with no children.
 */
public class Leetcode112 {
    public static void main(String[] args) {
        //root=[1,2], target=1
    }
    public boolean hasPathSum(TreeNode root, int target) {
        if (root == null) return false;
        if(root.left == null && root.right == null) return root.val == target;
        else if (root.left == null) return hasPathSum(root.right, target - root.val);
        else if (root.right == null) return hasPathSum(root.left, target - root.val);
        return hasPathSum(root.left,target-root.val)||hasPathSum(root.right,target-root.val);
    }
    //다른 DFS 재귀함수처럼 따로 만드려고 했는데, 생각해보니 재귀함수로 호출하면 매번 root 노드에 대한 처리가 필요하니까,
    //두번째 함수가 필요가 없다는 것을 깨닫게 되었다.
//    public boolean findSum(TreeNode root, int target) {
//        if (root == null) return target == 0;
//        return findSum(root.left,target-root.val)||findSum(root.right,target-root.val);
//    }
    //Algomap.io의 두개 함수로 나눈 버전
    public boolean hasPathSumAlgomapIo(TreeNode root, int targetSum) {
        return hasSum(root, 0, targetSum);
    }

    private boolean hasSum(TreeNode node, int currentSum, int targetSum) {
        //없는 자식 노드에 대한 처리
        if (node == null) return false;
        //새로운 값 추가
        currentSum += node.val;
        //리프 노드인 경우
        if (node.left == null && node.right == null) {
            return currentSum == targetSum;
        }

        return hasSum(node.left, currentSum, targetSum) || hasSum(node.right, currentSum, targetSum);
    }
}
