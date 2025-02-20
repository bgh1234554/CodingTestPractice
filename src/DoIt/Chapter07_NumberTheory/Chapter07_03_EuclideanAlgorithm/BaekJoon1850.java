package DoIt.Chapter07_NumberTheory.Chapter07_03_EuclideanAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1850 {
    public static void main(String[] args) throws IOException {
        //두 수의 최대공약수가 실제로 모든 자리수가 1인 수의 최대공약수의 길이.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long result = gcd(a,b);
        for(int i=0;i<result;i++){
            sb.append(1);
        }
        System.out.println(sb);
    }
    static long gcd(long a, long b){
        if(b==0) return a;
        else return gcd(b,a%b);
        //어차피 a보다 b가 더 커도, 그러면 gcd(b,a)인거라 재귀 한번만 더 하면 원래대로 돌아온다.
    }
}
