package AlgoMap_io.TwoPointers;

public class Leetcode167 {
    public static void main(String[] args) {
        System.out.println(twoSum(new int[]{0,2,7,11,15},9));
    }
    public static int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if(sum==target){
                //첫번째부터 세니까
                return new int[]{left+1,right+1};
            }
            else if(sum>target){
                right--;
            }
            else{
                left++;
            }
        }
        return null;
    }
}
