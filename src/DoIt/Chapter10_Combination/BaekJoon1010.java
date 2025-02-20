package DoIt.Chapter10_Combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1010 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[][] combination = new int[31][31];
        for(int i=0;i<31;i++){
            combination[i][0]=1;
            combination[i][1]=1;
            combination[i][i]=1;
        }
        for(int i=2;i<31;i++){
            for(int j=1;j<i;j++){
                combination[i][j]=combination[i-1][j]+combination[i-1][j-1];
            }
        }
        for(int i=0;i<T;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            System.out.println(combination[M][N]);
        }
    }
}
/*
구현보다는 문제를 보고 조합을 떠올릴 수 있냐 없냐가 더 중요하다.
"다리끼리는 서로 겹칠 수 없다"라는 말은 다리 간의 순서가 없다는 말로 해석될 수 있고,
그러면 단순히 동쪽 M개의 사이트에서 N개를 뽑아 순서대로 서쪽 N개의 사이트와 연결해주면 된다는 것이기 때문에
mCn이라는 단순 조합 문제로 바뀌게 된다.
 */