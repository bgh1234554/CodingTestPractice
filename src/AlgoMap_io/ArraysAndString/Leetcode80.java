package AlgoMap_io.ArraysAndString;

public class Leetcode80 {
    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{0,0,1,1,1,1,2,3,3}));
    }
    public static int removeDuplicates(int[] nums) {
        int k=1; //바꿔야할 자리
        int dup=1; //중복도
        int check=1; //검사 원본
        for(int j=1;j<nums.length;j++){
            if(nums[j]==nums[j-1]){
                dup++;
            }
            else{
                dup=1;
            }

        }
        return k;
    }
}
