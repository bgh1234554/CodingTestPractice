package AlgoMap_io.TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Given an array nums of n integers,
return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:

    0 <= a, b, c, d < n
    a, b, c, and d are distinct.
    nums[a] + nums[b] + nums[c] + nums[d] == target

You may return the answer in any order.
 */
public class Leetcode18 {
    public static void main(String[] args) {
        System.out.println(fourSum(new int[]{2,2,2,2,2},8));
        System.out.println(fourSum(new int[]{-2,-1,-1,1,1,2,2},0));
        System.out.println(fourSum(new int[]{1000000000,1000000000,1000000000,1000000000},-294967296));
    }
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-3;i++){
            int minFirst = nums[i];
            if(i>0&&nums[i]==nums[i-1]){continue;}
            for(int j=i+1;j<nums.length-2;j++){
                int minSecond = nums[j];
                if(j>i+1&&nums[j]==nums[j-1]){continue;}
                int left = j+1; int right = nums.length-1;
                while(left<right){
                    //오버플로우 방지
//                    long sum = (long)minFirst+(long)minSecond+(long)nums[left]+(long)nums[right];
                    long sum = (long) minFirst+minSecond+nums[left]+nums[right];
                    if(sum==target){
                        List<Integer> result = Arrays.asList(minFirst,minSecond,nums[left],nums[right]);
                        Collections.sort(result);
                        ans.add(result);
                        left++; right--;
                        while(left<right&&nums[left]==nums[left-1]){left++;}
                        while(left<right&&nums[right]==nums[right+1]){right--;}
                    }
                    else if(sum<target){
                        left++;
                    }
                    else{
                        right--;
                    }
                }
            }
        }
        return ans;
    }
}
