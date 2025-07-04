package AlgoMap_io.DynamicProgramming;

public class Leetcode1143 {
    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abcde","ace"));
    }
    //나의 풀이 - 실행시간 21초
    public static int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }
}
//역시 문자열 관련 DP는 2차원 배열로 풀면 된다.
//이미 백준에서 한번 해봤기 때문에 익숙해서 금방 풀 수 있었다. 오히려 AI가 주는 도움이 더 햇갈렸다.