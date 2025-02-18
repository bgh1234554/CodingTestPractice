package DoIt.Chapter11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon14501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N+1];
        int[] P = new int[N+1];
        int[] result = new int[N+2]; //ArrayIndexOutOfBounds 에러 해결하기 위해 배열 크기 1 늘림.
        for(int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i]=Integer.parseInt(st.nextToken());
            P[i]=Integer.parseInt(st.nextToken());
        }
        if(T[N]==1){
            result[N]=P[N];
        }
        for(int i=N-1;i>=1;i--){
            //상담 가능한 경우
            if(i+T[i]-1<=N){
                result[i]=result[i+T[i]]+P[i]; //result 배열 크기를 하나 늘려서 에러 안남.
                //안늘리면 i+T[i]-1<=N 인데 i+T[i]>N인 경우 P[i]를 더해야하는데 에러가 나서 못 더함.
            }
            //상담 불가능한 경우
            //그 다음날부터 불가능한 기간 동안, 일일이 검사할 필요가 없다.
            //어차피 이전 문제는 더 작은 문제라 풀려있을거니까, 바로 전날 거랑만 비교하면 된다.
            result[i]=Math.max(result[i+1],result[i]);
//            for(int j=i+1;j<Math.min(i+T[i],N+1);j++){
//                result[i]=Math.max(result[j],result[i]);
//            }
        }
        System.out.println(result[1]);
    }
}
