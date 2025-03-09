package AlgoMap_io.BinarySearch;
/*
Given a sorted array of distinct integers and a target value,
return the index if the target is found.
If not, return the index where it would be if it were inserted in order.

You must write an algorithm with O(log n) runtime complexity.
 */
public class Leetcode35 {
    public static void main(String[] args) {

    }
    public static int searchInsert(int[] nums, int target) {
        int left = 0; int right = nums.length - 1;
        //등호 빼먹지 않게 조심하기
        while (left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid]==target) return mid;
            else if(nums[mid]>target) right = mid-1;
            else left = mid + 1;
        }
        return left;
    }
}
