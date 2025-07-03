package AlgoMap_io.DynamicProgramming;
/*
Given an integer array nums, find the with the largest sum, and return its sum.
 */
public class Leetcode53 {
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int curSum = 0;
        for(int num: nums){
            curSum += num; //새로운 부분 배열의 합에 추가
            maxSum = Math.max(maxSum, curSum); //이후 최고기록과 비교 후 업데이트
            if(curSum<0) curSum = 0;
            //현재 부분배열의 합이 음수면 의미가 없으므로 새롭게 부분배열 시작
        }
        return maxSum;
    }
}
//백준 13398번 문제와 유사하다
//여기서는 left, right 배열을 사용하지 않고 Kadane's Algorithm을 사용해보겠다.

/*
Kadane's Algorithm is an efficient method to solve the Maximum Subarray problem in linear time.
The core idea is to iterate through the array while maintaining two variables:
current subarray sum and maximum sum found so far.

At each step, we determine whether to extend the existing subarray
or start a new one beginning at the current element.

This decision is made by comparing the current number against the sum of the current number and the ongoing subarray.

This strategy avoids redundant computations and allows you to find the optimal solution in a single pass,
making it ideal for performance-critical applications.


Start by initializing two variables: max_sum to negative infinity and curr_sum to 0. Then iterate through the array:

At each index, update curr_sum to the maximum of the current element alone
or the sum of the current element and the existing curr_sum.
Update max_sum to the maximum of itself and the current curr_sum.

This technique ensures that the best possible subarray sum is always recorded while maintaining constant space.

Time Complexity - O(n) Space Complexity - O(1)
 */