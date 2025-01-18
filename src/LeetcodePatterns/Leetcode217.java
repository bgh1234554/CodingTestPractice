package LeetcodePatterns;

import java.util.HashSet;
import java.util.Set;
/*
Given an integer array nums, return true if any value appears at least twice in the array,
and return false if every element is distinct.
 */
public class Leetcode217 {
    public static void main(String[] args) {
        containsDuplicate(new int[]{1,2,3,1});
    }
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }
        return nums.length!=set.size();
    }
    public static boolean containsDuplicateOptimal(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            if(!set.add(num)){
                return true;
            }
        }
        return false;
    }
}
