package DoIt.Chapter10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon2775 {
    public static void main(String[] args) throws IOException {
        //k층 n호
        int[][] result = new int[15][15];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0;i<=14;i++){
            result[i][1]=1;
            result[0][i]=i;
        }
        for(int i=1;i<=14;i++){
            //[i][1]의 값은 무조건 1이니까 j는 2부터 돌면 된다.
            for(int j=2;j<=14;j++){
                result[i][j]=result[i][j-1]+result[i-1][j];
            }
        }
        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());
            System.out.println(result[k][n]);
        }
    }
}
