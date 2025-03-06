package AlgoMap_io.LinkedList;
/*
Given the head of a singly linked list,
reverse the list, and return the reversed list.
 */
public class Leetcode206 {
    public static void main(String[] args) {

    }
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) { return head;}
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}
/*
굉장히 중요한 예제인데 실제로 생각해보려니까 잘 안된다.
포인터를 어떻게 옮기는지 루프 안에서 어떤 일이 일어나는지
이해가 안된다면 직접 그림으로 그려보자.
 */