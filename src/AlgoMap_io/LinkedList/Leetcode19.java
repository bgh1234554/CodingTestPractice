package AlgoMap_io.LinkedList;
/*
Given the head of a linked list,
remove the nth node from the end of the list and return its head.
 */
public class Leetcode19 {
    public static void main(String[] args) {

    }
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if(head.next == null) return null;
        //딜레이를 주기 위한 카운터
        int count = 1;
        //노드를 제거해야 하니까 이전 노드에 대한 정보도 필요하다.
        ListNode cur = null;
        ListNode prev = null;
        //정상적으로 탐색하는 포인터
        ListNode normal = head;
        while (normal!=null) {
            if(count<n){
                count++;
                normal = normal.next;
            }
            else if(count==n){
                count++;
                normal = normal.next;
                cur = head;
            }
            else{
                normal=normal.next;
                prev = cur;
                cur = cur.next;
            }
        }
        //prev가 null이면 cur=head라는 뜻이므로, prev.next를 할 수 없다.
        //굳이 제거할 필요 없이 다음 노드를 넘겨주면 된다.
        if(prev==null) return head.next;
        prev.next=cur.next;
        cur.next=null;
        return head;
    }

    //count 변수를 for문으로 대체한다. normal 포인터 없이 for문으로 대체
    public ListNode removeNthFromEndAlgomapIo(ListNode head, int n) {
        //null 대신 dummy 노드를 출발점으로 사용
        ListNode dummy = new ListNode(0);
        //그 다음 head를 연결
        dummy.next = head;
        //behind가 prev 포인터, ahead가 normal 포인터
        ListNode behind = dummy, ahead = dummy;

        //제거해야할 노드의 이전 노드를 움직일 거라, n번이 아닌 n+1번 먼저 이동한다.
        for (int i = 0; i <= n; i++) {
            ahead = ahead.next;
        }

        //정상적으로 이동
        while (ahead != null) {
            behind = behind.next;
            ahead = ahead.next;
        }

        //스킵해야 하는 노드 스킵
        behind.next = behind.next.next;

        return dummy.next;
    }
}
