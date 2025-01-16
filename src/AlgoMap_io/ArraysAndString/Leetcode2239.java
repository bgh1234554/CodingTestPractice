package AlgoNap_io.ArraysAndString;

/*
Given an integer array nums of size n, return the number with the value closest to 0 in nums.
If there are multiple answers, return the number with the largest value.
 */

public class Leetcode2239 {
    public static void main(String[] args) {
        Leetcode2239 main = new Leetcode2239();
        int[] arr = {-4,-2,1,4,8};
        int sol = main.findClosestNumber(arr);
        System.out.println(sol);
    }
    public int findClosestNumber(int[] arr) {
        int sol = arr[0];
        for(int i=0;i<arr.length;i++){
            if(Math.abs(sol)>Math.abs(arr[i])){
                sol=arr[i];
            }
            else if(Math.abs(sol)==Math.abs(arr[i])&&sol<arr[i]){
                sol=arr[i];
            }

        }
        return sol;
    }
}
