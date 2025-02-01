package DoIt.Chapter07.Chapter07_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon21568 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        long gcd = gcd(a,b);
        if(c%gcd!=0) System.out.println(-1);
        else{
            int K = (int) (c/gcd(a,b));
            long[] result = euclid(a,b);
            System.out.println(result[0]*K+" "+result[1]*K);
        }
    }
    static long[] euclid(int a, int b) {
        long[] result = new long[2];
        if(b==0){
            //나눗셈 끝나고 나머지가 0이 됐는데도 나누려고 함수가 들어오면
            //기본 값인 x=1,y=0 반환.
            result[0]=1;
            result[1]=0;
            return result;
        }
        long q = a/b; //몫 구하기
        long[] prev = euclid(b,a%b); //유클리드 호제법 반복 실행.
        result[0]=prev[1];
        result[1]=prev[0]-prev[1]*q;
        return result;
    }

    static long gcd(long a, long b){
        if(b==0)return Math.abs(a);
        else return gcd(b, a%b);
    }
}
/*
유클리드 호제법 구현 예제. 재귀함수로 흔히 구현하며, 처음 보면 내용이 햇갈릴 수 있다.
차근차근 원리를 이해하고 구현해보려고 하면 된다. 왠지 모르겠지만, 백준에는 채점준비중으로 바뀌어 있다.
 */