package AlgoMap_io.LinkedList;
/*
Given the head of a sorted linked list,
delete all duplicates such that each element appears only once. Return the linked list sorted as well.
 */

public class Leetcode83 {
    public static void main(String[] args) {

    }
    public ListNode deleteDupblicates(ListNode head) {
        //크기가 0 혹은 1인 경우
        if(head==null||head.next==null) return head;
        ListNode cur = head;
        while(cur!=null&&cur.next!=null){
            if(cur.val==cur.next.val){
                cur.next=cur.next.next;
            }
            else{
                cur = cur.next;
            }
        }
        return head;
    }
}
/*
Linked List 시작!
연결되는 포인터 느낌을 계속 가져가도록 하자. 처음부터 탐색 시작하는 Iterator 느낌도.
 */