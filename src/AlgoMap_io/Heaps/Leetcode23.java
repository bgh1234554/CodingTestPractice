package AlgoMap_io.Heaps;

import AlgoMap_io.LinkedList.ListNode;

import java.util.PriorityQueue;

public class Leetcode23 {
    public static void main(String[] args) {
    }
    //나의 풀이 - 실행시간 6ms
    public ListNode mergeKLists(ListNode[] lists) {
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (ListNode l : lists) {
                while(l != null) {
                    pq.add(l.val); l = l.next;
                }
            }
            ListNode head = new ListNode();
            ListNode ans = head;
            //새로운 Node를 LinkedList의 tail에 붙일 때의 순서를 생각해보자.
            while(!pq.isEmpty()) {
                //다음 노드를 새로 만든 뒤에 커서를 옮겨야 한다.
                ans.next = new ListNode(pq.poll());
                ans = ans.next;
            }
            return head.next;
    }
    //일일이 연결 리스트를 다 뜯어서 넣고, 다시 새로운 연결 리스트를 만들기 때문에
    //실행시간이 오래 걸린다.

    //algomap.io의 풀이 - 실행시간 4ms
    public ListNode mergeKListsAlgomapIo(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));

        //각 리스트의 첫 번째 노드를 PriorityQueue에 추가
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        //꺼낸 노드의 다음 노드를 PriorityQueue에 추가하는 방식으로 진행.
        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            cur.next = node;
            cur = node;
            //리스트의 head의 다음 노드를 PQ에 삽입.
            if (node.next != null) {
                minHeap.offer(node.next);
            }
        }

        return dummy.next;
    }
    /*
    mergeKListsAlgomapIo 함수가 더 빠른 이유는 PriorityQueue에 항상 k개의 노드만 유지하기 때문입니다.
    따라서 PriorityQueue의 크기가 작아져서 삽입 및 삭제 연산이 더 빠르게 수행됩니다.
     */
}
