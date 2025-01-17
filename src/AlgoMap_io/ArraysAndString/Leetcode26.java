package AlgoMap_io.ArraysAndString;
/*
Given an integer array nums sorted in non-decreasing order,
remove the duplicates in-place such that each unique element appears only once.

The relative order of the elements should be kept the same. Then return the number of unique elements in nums.

Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:

Change the array nums such that the first k elements of nums contain the unique elements
in the order they were present in nums initially.

The remaining elements of nums are not important as well as the size of nums.
Return k.
 */
public class Leetcode26 {
    public static void main(String[] args) {
        Leetcode26 sol = new Leetcode26();
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(sol.removeDuplicates(nums));
    }
    public int removeDuplicates(int[] nums) {
        int i=0,j=i+1,k=1;
        while(j<nums.length){
            if(nums[j]!=nums[i]){
                nums[k]=nums[j];
                i=j;
                k++;
            }
            j++;
        }
        return k;
    }
    //i 없앤 버전
    public int removeDuplicates2(int[] nums) {
        int k=1;
        for(int j=1;j<nums.length;j++){
            if(nums[j]!=nums[j-1]){
                nums[k]=nums[j];
                k++;
            }
        }
        return k;
    }
}
