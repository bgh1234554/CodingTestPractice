package AlgoMap_io.ArraysAndString;

/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock
and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 */
public class Leetcode121 {
    public static void main(String[] args) {
        Leetcode121 sol = new Leetcode121();
        int[] prices = {7,1,5,3,6,4};
        System.out.println(sol.maxProfit(prices));
    }
    public int maxProfit(int[] prices) {
        int max_profit = 0;
        int buy = prices[0];
        for(int price:prices){
            if(price<buy){
                buy=price; //최저점을 계속 바꾼다.
            }
            int profit = price-buy; //최저점과 그 이후 팔 수 있는 가격을 비교한다.
            max_profit = Math.max(max_profit,profit); //기존의 최고이익보다 큰지 비교한다.
        }
        return max_profit;
    }
}
