package AlgoMap_io.LinkedList;
/*
Given the head of a singly linked list,
return the middle node of the linked list.

If there are two middle nodes, return the second middle node.
 */
public class Leetcode876 {
    public static void main(String[] args) {

    }
    public static ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
