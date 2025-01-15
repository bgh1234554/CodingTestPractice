package AlgoNap_io.ArraysAndString;

import java.util.ArrayList;
import java.util.List;
/*
You are given a sorted unique integer array nums.

A range [a,b] is the set of all integers from a to b (inclusive).

Return the smallest sorted list of ranges that cover all the numbers in the array exactly.
That is, each element of nums is covered by exactly one of the ranges,
and there is no integer x such that x is in one of the ranges but not in nums.

Each range [a,b] in the list should be output as:

    "a->b" if a != b
    "a" if a == b
 */

public class Leetcode228 {
    public static void main(String[] args) {
        Leetcode228 sol = new Leetcode228();
        int[] nums = {0,1,2,4,5,7};
        System.out.println(sol.summaryRanges(nums));
    }
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if(nums.length==0){
            return result;
        }
        int start=nums[0];
        for(int i=1;i<nums.length;i++){
            if(nums[i-1]!=nums[i]-1){
                if(start==nums[i-1]){
                    result.add(start+"");
                }
                else{
                    result.add(start+"->"+nums[i-1]);
                }
                start=nums[i];
            }
        }
        if(start==nums[nums.length-1]){
            result.add(start+"");
        }
        else{
            result.add(start+"->"+nums[nums.length-1]);
        }
        return result;
    }
}
