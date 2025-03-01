package AlgoMap_io.TwoPointers;
/*
Given an integer array nums sorted in non-decreasing order,
return an array of the squares of each number sorted in non-decreasing order.
 */
public class Leetcode977 {
    public static void main(String[] args) {

    }
    public static int[] sortedSquares(int[] nums) {
        int left = 0; int right = nums.length-1;
        int[] ans = new int[nums.length];
        int count=nums.length-1;
        //count가 0부터 시작해서 ++하면 내림차순으로 나오니까
        //거꾸로 저장하면 오름차순이 된다.
        while(count>=0){
            if(Math.abs(nums[left])>Math.abs(nums[right])){
                ans[count]=nums[left]*nums[left];
                left++;
            }
            else{
                ans[count]=nums[right]*nums[right];
                right--;
            }
            count--;
        }
        return ans;
    }
}
