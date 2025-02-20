package DoIt.Chapter11_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon9252 {
    static long[][] DP;
    static StringBuilder sb = new StringBuilder();
    static String A;
    static String B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine();
        B = br.readLine();
        DP = new long[A.length()+1][B.length()+1]; //[1][1]부터 저장해야 구현하기 편해진다.
        for(int i=1;i<=A.length();i++){
            for(int j=1;j<=B.length();j++){
                //저장은 [1][1]부터 해도 인덱스는 0부터 비교해야하니까 -1
                if(A.charAt(i-1)==B.charAt(j-1)){
                    DP[i][j]=DP[i-1][j-1]+1;
                }
                else{
                    DP[i][j]=Math.max(DP[i-1][j],DP[i][j-1]);
                }
            }
        }
        //공통 문자열 값 출력
        System.out.println(DP[A.length()][B.length()]);
        printLCS(A.length(),B.length());
        sb.reverse(); //거꾸로 탐색해서 거꾸로 나오니까 뒤집어주기.
        System.out.println(sb);
    }

    private static void printLCS(int lengthA, int lengthB) {
        //둘이 같으면 대각선 위로 이동,
        //다르면 둘 중 위 옆 비교했을 때 더 큰쪽으로 이동.
        //0번째 인덱스에 도달했다는건 탐색이 끝났단 거니까 종료. (우리는 1번째 인덱스부터 저장했으니까)
        if(lengthA==0||lengthB==0) return;
        //저장할떄와 마찬가지로 문자열끼리의 비교는 1 빼서 비교
        if(A.charAt(lengthA-1)==B.charAt(lengthB-1)){
            sb.append(A.charAt(lengthA-1));
            printLCS(lengthA-1,lengthB-1);
        }
        else{
            if(DP[lengthA-1][lengthB]>DP[lengthA][lengthB-1]){
                printLCS(lengthA-1,lengthB);
            }
            else{
                printLCS(lengthA,lengthB-1);
            }
        }
    }
}
/*
문자열과 관련된 DP 문제는 각 문자열을 축으로 하는 2차원 배열을 선언하는 경우가 많다.
LCS는 문자열을 다루는 대표적인 DP문제이기 때문에, 유형을 잘 숙지해놔야 한다.
 */