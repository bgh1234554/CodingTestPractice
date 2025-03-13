package AlgoMap_io.Trees;

import java.util.LinkedList;
import java.util.Queue;

public class Leetcode100 {
    public static void main(String[] args) {

    }
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null) return true;
        if(p==null || q==null) return false;
        Queue<TreeNode> pq = new LinkedList<>();
        Queue<TreeNode> qq = new LinkedList<>();
        pq.add(p); qq.add(q);
        while(!pq.isEmpty() && !qq.isEmpty()) {
            int size = pq.size();
            for(int i = 0; i < size; i++) {
                TreeNode node1 = pq.poll();
                TreeNode node2 = qq.poll();
                if(node1.val!=node2.val) return false;
                if(node1.left!=null&&node2.left!=null){
                    pq.add(node1.left);
                    qq.add(node2.left);
                }
                else if(node1.left!=null||node2.left!=null){
                    return false;
                }
                if(node1.right!=null&&node2.right!=null){
                    pq.add(node1.right);
                    qq.add(node2.right);
                }
                else if(node1.right!=null||node2.right!=null){
                    return false;
                }
            }
        }
        return pq.isEmpty() && qq.isEmpty();
    }
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        return isSame(p,q);
    }
    public boolean isSame(TreeNode p, TreeNode q) {
        if(p==null && q==null) return true;
        if(p==null || q==null) return false;
        //위쪽의 저 긴 Queue를 재귀함수로 완전 간결하게 만들 수 있다.
        if(p.val!=q.val) return false;
        return isSame(p.left,q.left) && isSame(p.right,q.right);
    }
}
/*
트리는 재귀함수를 잘 활용하는 것이 중요한 것 같다.
핵심 함수는 간결하게 하고, queue 대신 재귀적으로 일을 처리할 수 있기 때문에,
새로운 함수를 만들어 그 안에서 재귀적으로 호출한 다음 결과값을 메인 함수에서
출력하는 형태가 많은 것 같다.
 */
