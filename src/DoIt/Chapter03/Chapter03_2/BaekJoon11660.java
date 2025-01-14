package DoIt.Chapter03.Chapter03_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int S[][] = new int[N+1][N+1]; int data;
        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                data=Integer.parseInt(st.nextToken());
                S[i][j]=S[i-1][j]+S[i][j-1]-S[i-1][j-1]+data;
                //System.out.print(S[i][j]+" ");
            }
            //.out.println();
        }
        int x1, y1, x2, y2, sum;
        for(int i=0;i<q;i++){
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            //System.out.println(S[x2][y2]+" "+S[x1-1][y2]+" "+S[x2][y1-1]+" "+S[x1-1][y1-1]+" ");
            sum = S[x2][y2]-S[x1-1][y2]-S[x2][y1-1]+S[x1-1][y1-1];
            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }
    /*
    열심히 그림을 그려보면서 2차원 배열에서는 부분합 공식을 어떻게 만들어야하는지 생각해봐야 한다.
    그것만 생각해내면, 생각한 대로 코드로 구현하면 된다.
     */
}
