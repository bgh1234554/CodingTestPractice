package DoIt.Chapter11_DynamicProgramming;

import java.util.Scanner;

public class BaekJoon10844 {
    public static void main(String[] args) {
        int mod = 1000000000;
        long[][] result = new long[101][10];
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for(int i=1;i<=N;i++){
            for(int j=0;j<=9;j++){
                if(i==1&&j!=0){
                    result[i][j]=1;
                }
                else if(j==0){
                    result[i][j]=result[i-1][j+1]%mod;
                }
                else if(j==9){
                    result[i][j]=result[i-1][j-1]%mod;
                }
                else{
                    result[i][j]=(result[i-1][j+1]+result[i-1][j-1])%mod;
                }
            }
        }
        long sum=0;
        for(int j=0;j<=9;j++){
            //마지막에 합 구할때도 mod로 나눠야한다.
            sum=(sum+result[N][j])%mod;
        }
        System.out.println(sum);
    }
}
/*
조금만 생각하면 점화식을 유도할 수 있는, 이차원 DP문제 중에서 제일 쉬운 문제들 중 하나
 */