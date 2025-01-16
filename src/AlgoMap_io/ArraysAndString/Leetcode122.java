package AlgoNap_io.ArraysAndString;
/*
You are given an integer array prices
where prices[i] is the price of a given stock on the ith day.

On each day, you may decide to buy and/or sell the stock.
You can only hold at most one share of the stock at any time.
However, you can buy it then immediately sell it on the same day.

Find and return the maximum profit you can achieve.
 */
public class Leetcode122 {
    public static void main(String[] args) {
        maxProfit(new int[]{7,1,5,3,6,4});
    }
    public static int maxProfit(int[] prices) {
        int total=0;
        for(int i=0;i<prices.length-1;i++){
            //저점 찾기
            while(i<prices.length-1&&prices[i]>prices[i+1]){
                i++;
            }
            int low = prices[i];
            //고점 찾기
            while(i<prices.length-1&&prices[i]<prices[i+1]){
                i++;
            }
            int high = prices[i];
            total+=(high-low);
        }
        return total;
    }
}
/*
이전과 문제는 비슷하지만 푸는 방식은 꽤 다르다.
좀 더 직관적으로 풀려고 하면 풀어진다.
 */