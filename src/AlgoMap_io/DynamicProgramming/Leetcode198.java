package AlgoMap_io.DynamicProgramming;
/*
You are a professional robber planning to rob houses along a street.
Each house has a certain amount of money stashed,
the only constraint stopping you from robbing each of them
is that adjacent houses have security systems connected
and it will automatically contact the police
if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house,
return the maximum amount of money you can rob tonight without alerting the police.
 */
public class Leetcode198 {
    public static void main(String[] args) {
        System.out.println(rob(new int[]{2,1,1,2}));
    }

    //이중 for문으로 인해
    public static int rob1ms(int[] nums){
        int n = nums.length;
        if(n==1) return nums[0];
        int[] dp = new int[n];
        //dp[i] => i번째 집까지 털었을 때의 최댓값
        dp[0]=nums[0]; dp[1]=Math.max(dp[0],nums[1]);
        for(int i=2;i<n;i++){
            dp[i]=dp[i-1];//이웃한 집을 털면 못터니까

            //i번째 집과 이웃하지 않은 집
            for(int j=i-2;j>=0;j--){
                dp[i]=Math.max(dp[i],dp[j]+nums[i]);
            }
        }
        return dp[n-1];
    }
    public static int rob(int[] nums) {
        if(nums.length <2){
            return nums[0];
        }

        //dp[i] => i번째 집까지 털었을 때의 최댓값
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        //굳이 이중 for문으로 돌릴 필요가 없다.
        //앞의 부분은 계산이 다 끝났을 것이라고 가정하기 때문이다.
        for(int i=2; i<nums.length; i++){
            dp[i] = Math.max(dp[i-1], nums[i]+dp[i-2]);
        }

        return dp[nums.length-1];
    }
}
