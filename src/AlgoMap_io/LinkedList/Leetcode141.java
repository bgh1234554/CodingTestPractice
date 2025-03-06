package AlgoMap_io.LinkedList;

import java.util.HashSet;

/*
Given head, the head of a linked list,
determine if the linked list has a cycle in it.

There is a cycle in a linked list
if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.

Return true if there is a cycle in the linked list. Otherwise, return false.
 */
public class Leetcode141 {
    public static void main(String[] args) {

    }
    public static boolean hasCycle(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy, fast = dummy;

        //fast가 2배로 빨리가는데, 사이클이면 언젠가는 따라잡기 때문인가?
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    //HashSet을 이용한 방법 - 공간복잡도가 O(n)이라 위 알고리즘이 더 효율적이다.
    public static boolean hasCycleWithHashSet(ListNode head) {
        //왜 value를 넣을 생각을 했지, ListNode가 아니라... val이 같아도 상관없잖아 이러면
        HashSet<ListNode> nodesSeen = new HashSet<>();
        ListNode current = head;
        while (current != null) {
            if (nodesSeen.contains(current)) {
                return true;
            } else {
                nodesSeen.add(current);
            }
            current = current.next;
        }
        return false;
    }
}
/*
이 아이디어는 "토끼와 거북이" 알고리즘으로 알려져 있으며, 연결 리스트에서 사이클을 감지하는 데 매우 효과적이다.
이 알고리즘은 두 개의 포인터를 사용하여 하나는 한 번에 한 노드씩, 다른 하나는 두 번에 한 노드씩 이동한다.
만약 사이클이 있다면 두 포인터는 결국 만나게 된다.

이 아이디어는 다른 문제에도 적용될 수 있다.
예를 들어, 링크드 리스트의 중간 지점을 찾거나,
두 링크드 리스트가 교차하는 지점을 찾는 문제에도 사용할 수 있다.
 */
