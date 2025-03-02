package AlgoMap_io.TwoPointers;

import java.util.Arrays;

/*
Given an integer array nums of length n and an integer target,
find three integers in nums such that the sum is closest to target.

Return the sum of the three integers.
You may assume that each input would have exactly one solution.
 */
public class Leetcode16 {
    public static void main(String[] args) {
        System.out.println(threeSumClosest(new int[]{-1,2,1,-4},1));
    }
    public static int threeSumClosest(int[] nums, int target) {
        int closest = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            int min = nums[i];
            int left = i+1; int right = nums.length-1;
            while(left<right){
                int sum = min+nums[left]+nums[right];
                if(Math.abs(sum-target)==0){
                    closest = sum;
                    return closest;
                }
                if (Math.abs(closest - target) > Math.abs(sum - target)){
                    closest = sum;
                }
                if(sum<target){
                    left++;
                }
                else{
                    right--;
                }
            }
        }
        return closest;
    }
}
/*
15번 풀면 쉽게 풀 수 있다고 했는데...

closest의 뜻이 뭐지? 같아도 closest 아닌가?

아... 그냥 closest에 sum을 넣어야 하는데 Math.abs를 씌운 두 값의 차이를 게속 집어넣어서 생긴 오류였다...
 */