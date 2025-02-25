package LeetcodePatterns.Arrays;
/*
Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.

You must implement a solution with a linear runtime complexity and use only constant extra space.
 */
public class Leetcode136 {
    public static void main(String[] args) {
        singleNumber(new int[]{2,2,1});
    }
    public static int singleNumber(int[] nums) {
        int result=0;
        for(int i=0; i<nums.length; i++) {
            result = result^nums[i];
        }
        return result;
        /*
            xor of two same number is 0
            xor is commutative
            에이 이걸 어떻게 생각해서 푸나....
        */
    }
}
