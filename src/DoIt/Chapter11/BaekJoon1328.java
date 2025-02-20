package DoIt.Chapter11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1328 {
    public static void main(String[] args) throws IOException {
        long[][][] DP = new long[101][101][101];
        DP[1][1][1]=1;
        long mod = 1000000007;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        for(int i=2;i<=N;i++){
            for(int j=1;j<=L;j++){
                for(int k=1;k<=R;k++){
                    DP[i][j][k]=(DP[i-1][j][k]*(i-2)+DP[i-1][j-1][k]+DP[i-1][j][k-1])%mod;
                }
            }
        }
        System.out.println(DP[N][L][R]);
    }
}
/*
3차원 DP 배열은 처음인데다가, 일반식도 매우 복잡하다. 괜히 플래티넘이 아니다.
DP 배열의 규칙을 구할 때, DP[N-1]까지는 다 구해져 있다 가정하고 DP[N]의 식을 세우는 단계가 아직까진 익숙하지 않다.
수학적 귀납법대로 사고하는 것이 너무 어렵다.

가장 작은 건물을 마지막에 배치하자는 아이디어는 처음에 생각이 났는데, 그 다음 식을 구할 때 어떻게 써먹어야 하는지
몰라서 잊고 있다가 나중에 해답지를 보고 방법을 깨달았다.
 */