package AlgoMap_io.DynamicProgramming;
/*
There is a robot on an m x n grid.
The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

Given the two integers m and n,
return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated
so that the answer will be less than or equal to 2 * 10**9.
 */
public class Leetcode62 {
    public static void main(String[] args) {
        System.out.println(uniquePaths(3,7));
    }
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        //확통 시간에 했던 테두리에 1 적고 더하는 방식 그거임.
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for(int i=0;i<n;i++){
            dp[0][i] = 1;
        }
        //이중 for문 경계 어떻게 할지가
        for (int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j-1] + dp[i-1][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}
