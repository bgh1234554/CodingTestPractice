package AlgoMap_io.LinkedList;
/*
Given the head of a linked list head, in which each node contains an integer value.

Between every pair of adjacent nodes,
insert a new node with a value equal to the greatest common divisor of them.

Return the linked list after insertion.

The greatest common divisor of two numbers is the largest positive integer that evenly divides both numbers.
 */
public class Leetcode2807 {
    public static void main(String[] args) {

    }
    public ListNode insertGreatestCommonDivisors(ListNode head){
        ListNode cur = head;
        while(cur.next!=null){
            int gcd = gcd(cur.val,cur.next.val);
            ListNode newNode = new ListNode(gcd);
            newNode.next = cur.next;
            cur.next = newNode;
            cur = cur.next.next;
        }
        return head;
    }
    //최대 공약수 구하는 법, 계속 까먹으니까 다시 알아두자.
    public static int gcd(int a, int b){
        if(b==0) return a;
        return gcd(b,a%b);
    }
}
