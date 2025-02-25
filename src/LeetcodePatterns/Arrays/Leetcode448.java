package LeetcodePatterns.Arrays;

import java.util.ArrayList;
import java.util.List;
/*
Given an array nums of n integers where nums[i] is in the range [1, n],
return an array of all the integers in the range [1, n] that do not appear in nums.
 */
public class Leetcode448 {
    public static void main(String[] args) {
        findDisappearedNumbers(new int[]{4,3,2,7,8,2,3,1});
    }
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int[] store = new int[nums.length+1];
        for(int i=0;i<nums.length;i++){
            store[nums[i]]++;
        }
        for(int i=1;i<store.length;i++){
            if(store[i]==0){
                list.add(i);
            }
        }
        return list;
    }
}
