package DoIt.Chapter11_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon11049 {
    static int[][] DP;
    static Matrix[] matrices;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        DP = new int[N+1][N+1];
        matrices = new Matrix[N+1];
        //DP 배열 초기화
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                DP[i][j] = -1;
            }
        }
        for(int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            matrices[i]=new Matrix(row,col);
        }
        System.out.println(fillDP(1,N));
    }

    private static int fillDP(int start, int end) {
        int result = Integer.MAX_VALUE;
        if(DP[start][end]!=-1) return DP[start][end]; //메모이제이션 활용
        if(start==end) return 0;
        if(end-start==1) return matrices[start].row*matrices[end].row*matrices[end].col;
        for(int i=start;i<end;i++){
            result = Math.min(result,fillDP(start,i)+fillDP(i+1,end)+matrices[start].row*matrices[i+1].row*matrices[end].col);
        }
        DP[start][end]=result;
        return DP[start][end];
    }

    static class Matrix{
        int row;
        int col;
        public Matrix(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
/*
행렬 관련 동적 계획법(DP) 문제는 알고리즘 문제에서 자주 등장합니다.
이러한 문제들은 주로 2차원 또는 3차원 DP 배열을 사용하여 해결합니다.

2차원 DP 배열을 사용하는 경우
유형: 행렬 곱셈 순서, 최단 경로 문제
예시: 백준 11049번 (행렬 곱셈 순서)
점화식: DP[i][j] = min(DP[i][k] + DP[k+1][j] + cost) (i <= k < j)

3차원 DP 배열을 사용하는 경우
유형: 상태가 여러 개인 문제, 특정 조건을 만족하는 경로 찾기
예시: 백준 2342번 (Dance Dance Revolution)
점화식: DP[i][j][k] = min(DP[i-1][j][k] + cost, DP[i][j-1][k] + cost, ...)

행렬 관련 DP 문제는 주로 2차원 배열을 사용하지만, 문제의 복잡도에 따라 3차원 배열을 사용할 수도 있습니다.
다양한 문제를 풀어보면서 경험을 쌓는 것이 중요합니다.
 */