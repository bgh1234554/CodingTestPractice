package AlgoMap_io.DynamicProgramming;

import java.util.Arrays;

/*
You are given an integer array coins representing coins of different denominations
and an integer amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount.
If that amount of money cannot be made up by any combination of the coins,
return -1.

You may assume that you have an infinite number of each kind of coin.
 */
public class Leetcode322 {
    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1,2,5},11));
        //1차 오류 발생
        System.out.println(coinChange(new int[]{2},3));
    }
    //내가 푼 방법 - 실행시간 17초
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1]; //불가능하니까
        if(amount == 0) return 0;
        for(int coin: coins){
            if(coin<=amount){
                dp[coin]=1;
            }
        }
        for(int i=1;i<=amount;i++){
            if(dp[i]==0){
                dp[i]=Integer.MAX_VALUE; //impossible big number
                for(int coin: coins){
                    if(i-coin>=0&&dp[i-coin]!=Integer.MAX_VALUE){
                        dp[i]=Math.min(dp[i],dp[i-coin]+1);
                    }
                }
            }
        }
        return dp[amount]==Integer.MAX_VALUE?-1:dp[amount];
    }

    //coin 부분과 경우의 수 계산을 하나로 합친 경우 - 실행시간 8초
    public int coinChange8s(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // impossible big number
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
