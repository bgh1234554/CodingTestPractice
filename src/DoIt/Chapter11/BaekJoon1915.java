package DoIt.Chapter11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1915 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long max = 0;
        long[][] result = new long[1001][1001];
        //굳이 배열로 받은 뒤에, DP 배열에 저장하지 말고, DP 배열에 바로 저장해도 된다.
        for(int i=0;i<n;i++){
            String row = br.readLine();
            for(int j=0;j<m;j++){
                result[i][j]=Long.parseLong(String.valueOf(row.charAt(j)));
                if(result[i][j]==1&&i>0&&j>0){ //ArrayIndexOutOfBoundsException 방지
                    result[i][j]=Math.min(result[i-1][j-1],Math.min(result[i][j-1],result[i-1][j]))+1;
                }
                max = Math.max(max,result[i][j]);
            }
        }
        System.out.println(max*max);
    }
}
