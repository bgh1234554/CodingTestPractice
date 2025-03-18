package AlgoMap_io.Trees;
/*
Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.

According to the definition of LCA on Wikipedia:
“The lowest common ancestor is defined between two nodes p and q as the lowest node in T
that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 */
public class Leetcode235 {
    public static void main(String[] args) {

    }
    //Algomap.io의 풀이
    //ArrayList와는 달리 이미 트리가 주어진 거고, LCA가 무조건 있는 거니까,
    //p노드와 q노드의 값을 현재 노드와 비교해가면서 찾아가는 방식으로 구현하였다.
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode lca = root;
        while (root != null) {
            //root보다
            //둘다 더 크면 오른쪽으로 가야하고,
            if (root.val < p.val && root.val < q.val) {
                root = root.right;
            //둘다 더 작으면 왼쪽으로 가야하는거고,
            } else if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else { //사이에 있다는거면, 발견 완료!
                //최소 공통 조상이기 때문에 가장 위쪽에 있는걸 찾는 거니까, 바로 끝내면 된다.
                lca = root;
                break;
            }
        }
        return lca;
    }
}
/*
오랜만에 LCA 하니까 도저히 못풀겠다...

리트코드에서는 자체 자료구조인 TreeNode를 주고,
백준에서는 직접 ArrayList의 배열로 받은 다음, 부모 노드를 기억하는 방식으로 하다보니
많이 달라서 생각하기 힘든 것 같다.
 */