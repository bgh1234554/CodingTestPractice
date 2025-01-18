package LeetcodePatterns;
/*
Given an array nums containing n distinct numbers in the range [0, n],
return the only number in the range that is missing from the array.
 */
public class Leetcode268 {
    public static void main(String[] args) {
        missingNumber(new int[]{3,0,1});
    }
    public static int missingNumber(int[] nums) {
        int result=0;
        int[] store = new int[nums.length+1];
        for(int i=0;i<nums.length;i++){
            store[nums[i]]++;
        }
        for(int i=0;i<store.length;i++){
            if(store[i]==0){
                result=i;
                break;
            }
        }
        return result;
    }
}
