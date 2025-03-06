package AlgoMap_io.LinkedList;
/*
You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists into one sorted list.
The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.
 */
public class Leetcode21 {
    public static void main(String[] args) {

    }
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode cur1 = list1;
        ListNode cur2 = list2;
        ListNode ans = null;
        ListNode anshead = null;
        if(list1==null) return list2;
        if(list2==null) return list1;
        while (cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                if(ans==null){
                    ans = cur1;
                    anshead = ans;
                }
                else{
                    ans.next = cur1;
                    ans = ans.next;
                }
                cur1 = cur1.next;
            }
            else{
                if(ans==null){
                    ans = cur2;
                    anshead = ans;
                }
                else{
                    ans.next = cur2;
                    ans = ans.next;
                }
                cur2 = cur2.next;
            }
        }
        if(cur1!=null){
            ans.next = cur1;
        }
        else{
            ans.next = cur2;
        }
        return anshead;
    }
    public static ListNode mergeTwoListsClean(ListNode list1, ListNode list2) {
        ListNode cur1 = list1;
        ListNode cur2 = list2;
        ListNode ans = new ListNode(0); // 더미 노드 생성
        ListNode anshead = ans;

        while (cur1 != null && cur2 != null) {
            //이제 if ans==null 구문이 필요가 없다.
            if (cur1.val <= cur2.val) {
                ans.next = cur1;
                cur1 = cur1.next;
            } else {
                ans.next = cur2;
                cur2 = cur2.next;
            }
            ans = ans.next;
        }

        // 남은 노드 연결
        if (cur1 != null) {
            ans.next = cur1;
        } else {
            ans.next = cur2;
        }

        return anshead.next; // 더미 노드 다음 노드 반환
    }
}
/*
merge sort에 사용되는 merge 함수의 Linked List 버전.
포인터를 어떻게 업데이트할지만 잘 고민하면 된다.
 */