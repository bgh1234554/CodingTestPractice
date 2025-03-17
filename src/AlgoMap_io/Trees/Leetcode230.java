package AlgoMap_io.Trees;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/*
Given the root of a "binary search tree", and an integer k,
return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 */
public class Leetcode230 {
    public static void main(String[] args) {

    }
    //바로 생각난 풀이 - PQ와 Q 모두 이용 - 실행시간 4ms
    public int kthSmallest(TreeNode root, int k) {
        PriorityQueue<TreeNode> pq = new PriorityQueue<>((o1, o2) -> o2.val - o1.val);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if(pq.size() < k){
                    pq.add(node);
                }
                else if(pq.peek().val>node.val&&pq.size()>=k){
                    pq.add(node);
                    pq.poll();
                }
                if(node.left != null) q.add(node.left);
                if(node.right != null) q.add(node.right);
            }
        }
        return pq.peek().val;
    }
    private int count;
    private int ans;

    //DFS Inorder traversal을 이용한 방법 - 실행시간 0ms
    //Time: O(n), Space: O(n)
    public int kthSmallestWithDFS(TreeNode root, int k) {
        count = k;
        ans = 0;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        //왼쪽 DFS
        dfs(node.left);
        //값 출력
        if (count == 1) {
            ans = node.val;
        }
        //오른쪽 DFS
        count--;
        //count값이 0이면 ans를 찾았다는 뜻이니까 node.right에 대해 DFS를 실행할 필요가 없다.
        if (count > 0) {
            dfs(node.right);
        }
    }
}
/*
단순한 이진트리가 아니라 "이진탐색 트리"이기 때문에, 단순히 PQ와 Q를 쓰는 방법보다 더 쉬운 방법이 있다.

이진탐색트리이기 때문에, DFS - Inorder traversal을 하면 O(n) 시간에 BST에 들어있는 값에 대해
정렬된 순서로 출력할 수 있다는 것을 알 수 있다.

다만 여기서는 배열이 굳이 필요가 없기 때문에, count의 값을 줄여가면서 count가 1이 됐을때 정답을 출력하는 방식으로
함수를 구성하였다. count가 1이 아니면 ans를 업데이트하지 않고, count에 1을 빼는 방식으로 함수가 진행된다.
 */