package DoIt.Chapter10;

import java.util.Scanner;

public class BaekJoon1947 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int mod = 1000000000;
        long[] result = new long[1000001];
        result[1]=0; result[2]=1;
        for(int i=3;i<result.length;i++){
            result[i]=(i-1)*(result[i-2]+result[i-1])%mod;
        }
        System.out.println(result[N]);
    }
}
/*
DP로 가기 이전 점화식 연습 문제,
경우의 수를 두개의 배반되는 새로운 경우의 수로 나누고 점화식을 구하고,
Base Case에 대한 값을 구한다.
이후 알아서 계산되게 놔둔 다음에 구한다.
 */
