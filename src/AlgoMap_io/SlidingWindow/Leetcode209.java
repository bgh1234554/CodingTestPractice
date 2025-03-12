package AlgoMap_io.SlidingWindow;
/*
Given an array of positive integers nums and a positive integer target,
return the minimal length of a whose sum is "greater than or equal" to target.

If there is no such subarray, return 0 instead.

Follow up: If you have figured out the O(n) solution,
try coding another solution of which the time complexity is O(n log(n)).
 */
public class Leetcode209 {
    public static void main(String[] args) {
        System.out.println(minSubArrayLen(11,new int[]{1,2,3,4,5}));
    }
    public static int minSubArrayLen(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE;
        int left = 0; int sum = 0;
        for (int right = 0; right < nums.length; right++) {
            sum+=nums[right];
            if(sum==target){
                minLength = Math.min(minLength,right-left+1);
            }
            else if(sum>target){
                while(sum>target){
                    minLength = Math.min(minLength,right-left+1);
                    sum-=nums[left++];
                }
                if(sum==target){
                    minLength = Math.min(minLength,right-left+1);
                }
            }
        }
        return minLength == Integer.MAX_VALUE?0:minLength;
    }
}
/*
이것도 슬라이딩 윈도우의 크기를 유동적으로 조절하는 문제.
target 이상이니까, 같을 떄는 그냥 minLength 업데이트하고,
더 클 때는 인덱스가 작은 부분을 없애서 더 작은 길이로 target보다 큰지
while 루프로 지우면서 확인.
 */