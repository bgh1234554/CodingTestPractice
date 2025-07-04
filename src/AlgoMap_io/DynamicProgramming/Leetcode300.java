package AlgoMap_io.DynamicProgramming;

import java.util.Arrays;

/*
Given an integer array nums, return the length of the longest strictly increasing .

A subsequence is an array that can be derived from another array by deleting some
or no elements without changing the order of the remaining elements.
 */
public class Leetcode300 {
    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }

    //Algomap.io의 방법 차용 - 실행시간 42ms
    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        //i번째 숫자를 포함하는 부분배열중 가장 긴 subsequence
        Arrays.fill(dp, 1); //기본값은 당연히 1. 1이 최솟값이니까.

        for (int i = 1; i < nums.length; i++) {
            //이건 연속적일때 이렇게 하는거고
//            if(nums[i] > nums[i-1]){
//                dp[i] = dp[i-1]+1;
//            }
//            else{
//                dp[i]=1;
//            }
            //비연속적일때는 이렇게 for문을 하나 더 돌려야 한다.
            for (int j = 0; j < i; j++) {
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = 0;
        for(int length : dp){
            max = Math.max(max, length);
        }
        return max;
    }

    //O(nlogn) 시간복잡도로 푼 버전 - 실행시간 2초
    public int lengthOfLISBinarySearch(int[] nums) {
        int[] tails = new int[nums.length]; //현재까지 얻어낸 LIS를 저장
        //tails[i]: 길이 i+1짜리 증가 수열의 최소 끝값
        int size = 0; //현재 가장 큰 증가부분수열의 길이
        for (int x : nums) {
            int i = 0, j = size;
            //현재 원소 x를 넣을 위치 i를 tails에서 이진탐색으로 찾는다.
            while (i != j) {
                int m = (i + j) / 2;
                if (tails[m] < x)
                    i = m + 1;
                else
                    j = m;
            }
            //이후 찾은 위치에 x를 삽입한다. 더 작은 값으로 업데이트 하는 것이 유리하기 때문이다.
            tails[i] = x;
            //만약 i가 맨 끝이면, 수열의 길이가 늘어난 것이기 때문에 size를 늘린다.
            if (i == size) ++size;
        }
        return size;
    }
}
//subsequence와 subarray는 다르다. subarray는 연속적이고, subsequence는 비연속적이다.
//백준 13398번 문제와 비슷한듯 다르다.