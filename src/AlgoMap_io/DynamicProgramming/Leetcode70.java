package AlgoMap_io.DynamicProgramming;
/*
You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps.
In how many distinct ways can you climb to the top?
 */
public class Leetcode70 {
    public int climbStairs(int n) {
        int[] stairs = new int[n + 2];
        stairs[1] = 1; stairs[2] = 2;
        for (int i = 3; i <= n; i++) {
            stairs[i]= stairs[i-1] + stairs[i-2];
            //왜냐면 두칸 뒤에서 두칸 접프, 한칸 뒤에서 한칸 점프
            //두칸에서 1+1로 들어가면 한칸에서 한칸 점프랑 겹치니까
        }
        return stairs[n];
    }
}
