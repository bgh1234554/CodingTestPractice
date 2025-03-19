package AlgoMap_io.Heaps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
/*
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 */
public class Leetcode347 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
    }
    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num,map.getOrDefault(num,0)+1);
        }
        //HashMap을 바로 넣는 것이 아니다. key만 넣고 value를 읽어올때 hashmap을 사용한다.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)->map.get(b)-map.get(a));
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            maxHeap.add(entry.getKey());
        }
        int[] ans = new int[k];
        for(int i=0;i<k;i++){
            ans[i] = maxHeap.poll();
        }
        return ans;
    }
}
