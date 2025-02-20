package DoIt.Chapter07_NumberTheory.Chapter07_03_EuclideanAlgorithm;

import java.util.Scanner;

public class BaekJoon1934 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        for(int i=0;i<T;i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            //최소공배수 = 두 수의 곱 / 최대공약수
            sb.append(a*b/gcd(a,b)).append("\n");
        }
        System.out.println(sb);
    }
    static int gcd(int a, int b){
        if(b==0) return a;
        else return gcd(b,a%b);
        //어차피 a보다 b가 더 커도, 그러면 gcd(b,a)인거라 재귀 한번만 더 하면 원래대로 돌아온다.
    }
}
