package AlgoMap_io.LinkedList;

import java.util.HashMap;
import java.util.Map;

/*
A linked list of length n is given such that each node contains an additional random pointer,
which could point to any node in the list, or null.

Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes,
where each new node has its value set to the value of its corresponding original node.
Both the next and random pointer of the new nodes should point to new nodes in the copied list
such that the pointers in the original list and copied list represent the same list state.
None of the pointers in the new list should point to nodes in the original list.

For example, if there are two nodes X and Y in the original list,
where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.

Return the head of the copied linked list.

The linked list is represented in the input/output as a list of n nodes.
Each node is represented as a pair of [val, random_index] where:

    val: an integer representing Node.val
    random_index: the index of the node (range from 0 to n-1) that the random pointer points to,
        or null if it does not point to any node.

Your code will only be given the head of the original linked list.
 */
public class Leetcode138 {
    public static void main(String[] args) {

    }
    //쉽네 하고 생각했는데, 새로운 Node를 만들 때 생성자에 기존 노드를 넣으면
    //포인터처럼 작동하기 때문에 Deep Copy를 할 수 없다.
//    public static Node copyRandomList(Node head){
//        Node cur = head;
//        Node ans = null;
//        Node ansHead = null;
//        //노드 간 random이 어디로 연결되는지 저장
//        Map<Node, Node> map = new HashMap<Node, Node>();
//        while(cur != null){
//            if(ans == null){
//                ans = new Node(cur.val);
//                ansHead = ans;
//            }
//            else{
//                Node node = new Node(cur.val);
//                ans.next = node;
//            }
//            cur = cur.next;
//        }
//        //랜덤을 어떻게 연결하지...?
//        //나중에 while문으로 집어넣으려고 해도 어차피 주소값이 달라서 찾기 힘든데...
//        return ansHead;
//    }
    /*
    Map에 저장하는 대상을 복사된 노드로 변경
     */
    public static Node copyRandomList(Node head) {
        if (head == null) return null;

        Map<Node, Node> oldToNew = new HashMap<>();
        Node curr = head;

        // Deep Copy를 위한 새로운 노드 생성
        while (curr != null) {
            oldToNew.put(curr, new Node(curr.val));
            curr = curr.next;
        }

        // next와 random 포인터 연결하기
        curr = head;
        while (curr != null) {
            Node newNode = oldToNew.get(curr);
            newNode.next = oldToNew.get(curr.next);
            newNode.random = oldToNew.get(curr.random);
            curr = curr.next;
        }
        //HashMap이 새롭게 복사되는 Linked List가 복사되기 쉽게 도와주는 다리 역할을 한다고 보면 될 것 같다.
        return oldToNew.get(head);
    }
    static class Node {
        public int val;
        public Node next;
        public Node random;
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
