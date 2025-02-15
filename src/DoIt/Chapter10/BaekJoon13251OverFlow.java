package DoIt.Chapter10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon13251OverFlow {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        int[] stones = new int[M];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sum=0;
        for(int i=0;i<M;i++){
            stones[i]=Integer.parseInt(st.nextToken());
            sum+=stones[i];
        }
        int K = Integer.parseInt(br.readLine());
        long[][] result = new long[sum+1][sum+1];
        for(int i=1;i<=sum;i++){
            result[i][i]=1;
            result[i][1]=i;
            result[i][0]=1;
        }
        for(int i=2;i<=sum;i++){
            for(int j=1;j<i;j++){
                result[i][j]=result[i-1][j-1]+result[i-1][j];
            }
        }
        double probability=0.0;
        for(int i=0;i<M;i++){
            probability+=((double) result[stones[i]][K] /result[sum][K]);
        }
        System.out.println(probability);
    }
}
//왜 백준에 넣으면 2%만에 틀렸다고 나오는거지? 예시는 다 맞는데...
//result 함수를 보니까 i값이 65일때부터 오버플로우가 발생하기 시작한다.
