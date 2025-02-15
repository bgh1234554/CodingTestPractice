package DoIt.Chapter10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon13251 {
    public static void main(String[] args) throws IOException {
        int[] stones = new int[51];
        double[] probability = new double[51];
        double ans = 0.0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int sum=0;
        for(int i=0;i<M;i++){
            stones[i]=Integer.parseInt(st.nextToken());
            sum+=stones[i];
        }
        int K = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            if(stones[i]>=K){
                probability[i]=1.0;
                for(int k=0;k<K;k++){
                    probability[i]*= (double) (stones[i] - k) /(sum-k);
                }
            }
            ans+=probability[i];
        }
        System.out.println(ans);
    }
}
