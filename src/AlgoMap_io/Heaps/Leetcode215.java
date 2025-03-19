package AlgoMap_io.Heaps;

import java.util.PriorityQueue;
/*
Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

Can you solve it without sorting?
 */
public class Leetcode215 {
    public static void main(String[] args) {

    }
    //PriorityQueue (Heap) 사용해서 푸는 방법
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)->b-a);
        for(int num : nums){
            maxHeap.add(num);
        }
        int ans = 0;
        for(int i=0;i<k;i++){
            ans = maxHeap.poll();
        }
        return ans;
    }
    //배열을 사용해서 푸는 방법
    public int findKthLargest2(int[] nums, int k) {
        int[] c = new int[20001];
        for(int num: nums) {
            c[num+10000]++; // as nums range is from -10^4 to 10^4
        }
        for(int i = c.length-1; i >= 0; i--){
            if(c[i] > 0){
                k-=c[i];
                if(k<=0) return i-10000;
            }

        }
        return -1;
    }
}
