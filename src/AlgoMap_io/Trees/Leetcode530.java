package AlgoMap_io.Trees;

import java.util.LinkedList;
import java.util.Queue;

/*
Given the root of a Binary Search Tree (BST),
return the minimum absolute difference between the values of any two different nodes in the tree.
 */
public class Leetcode530 {
    public static void main(String[] args) {

    }
    //BFS로 풀어볼까? -> 폐기
//    public int getMinimumDifference(TreeNode root) {
//        int minDiff = Integer.MAX_VALUE;
//        Queue<TreeNode> q = new LinkedList<>();
//        q.add(root);
//        while(!q.isEmpty()) {
//            int size = q.size();
//            for(int i = 0; i < size; i++) {
//                TreeNode node = q.poll();
//                if(node.left != null) {
//                    minDiff = Math.min(minDiff, Math.abs(node.val - node.left.val));
//                    q.add(node.left);
//                }
//                if(node.right != null) {
//                    minDiff = Math.min(minDiff, Math.abs(node.val - node.right.val));
//                    q.add(node.right);
//                }
//                if(minDiff==1) return 1;
//            }
//        }
//        return minDiff;
//    }
    private TreeNode prev = null;
    private int minDiff = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        inOrderTraversal(root);
        return minDiff;
    }

    private void inOrderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }

        // Traverse the left subtree
        // 처음 탐색하는 거면 여기서 prev가 업데이트 되겠지
        inOrderTraversal(node.left);

        // Process the current node
        if (prev != null) {
            minDiff = Math.min(minDiff, node.val - prev.val);
        }

        // Update the previous node to the current node
        prev = node;

        // Traverse the right subtree
        inOrderTraversal(node.right);
    }
}
/*
[236,104,701,null,227,null,911]
와 같은 트리가 있을 때, 한단계 건너서 차이가 날수도 있으니까 애매하다...

Inorder traversal 하면 정렬이 되니까, 이전 노드와 현재 노드간의 차를 비교한다.
탐색하는 순서가 [104,227,236,701,991]이 되니까...
Math.abs(a[i-1]-a[i])만 계속 반복하면 되기 때문에...

Do It 교재에서는 Inorder Traversal을 사용하지 않았는데, Leetcode에서는 많이 물어본다.
Inorder Traversal
 */