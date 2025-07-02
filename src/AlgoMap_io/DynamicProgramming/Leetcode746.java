package AlgoMap_io.DynamicProgramming;
/*
You are given an integer array cost
where cost[i] is the cost of ith step on a staircase.

Once you pay the cost, you can either climb one or two steps.

You can either start from the step with index 0, or the step with index 1.

Return the minimum cost to reach the top of the floor.
 */
public class Leetcode746 {
    public static void main(String[] args) {
        System.out.println(minCostClimbingStairs(new int[]{10, 15, 20}));
    }
    public static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        //dp[i] = i번째까지 오는데 최소 비용
        dp[0] = 0; dp[1] = 0; //시작 지점이니까
        for (int i = 2; i <= n; i++) {
//            int twoStep = dp[i - 2] + cost[i - 2];
//            int oneStep = dp[i - 1] + cost[i - 1];
//            dp[i] = Math.min(oneStep, twoStep);
            dp[i] = Math.min(dp[i-1]+ cost[i-1], dp[i-2]+cost[i-2]);
        }
        return dp[n];
    }
}
