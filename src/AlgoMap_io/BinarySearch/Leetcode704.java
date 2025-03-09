package AlgoMap_io.BinarySearch;
/*
Given an array of integers nums which is sorted in ascending order,
and an integer target, write a function to search target in nums.
If target exists, then return its index. Otherwise, return -1.

You must write an algorithm with O(log n) runtime complexity.
 */
public class Leetcode704 {
    public static void main(String[] args) {
        System.out.println(search(new int[]{5},5));
    }
    public static int search(int[] nums, int target) {
        int left = 0; int right = nums.length - 1;
        //등호 빼먹지 않게 조심하기
        while (left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid]==target) return mid;
            else if(nums[mid]>target) right = mid-1;
            else left = mid + 1;
        }
        return -1;
    }
}
