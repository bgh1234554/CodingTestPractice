package DoIt.Chapter06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int goal = Integer.parseInt(st.nextToken());
        int[] money = new int[N];
        for(int i=0;i<N;i++){
            money[i]=Integer.parseInt(br.readLine());
        }
        int sum=0, count=0;
        for(int i=N-1;i>=0;i--){
            while(sum+money[i]<=goal){
                sum+=money[i];
                count++;
            }
        }
        System.out.println(count);
    }
}
