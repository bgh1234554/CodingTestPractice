package DoIt.Chapter10;

import java.util.Scanner;

public class BaekJoon1256 {
    public static void main(String[] args) {
        long[][] combination = new long[201][201];
        int limit = 1000000000;
        for(int i=0;i<201;i++){
            for(int j=0;j<=i;j++){
                if(i==j||j==0){
                    combination[i][j]=1;
                }
                else if(i==1&&j==1){
                    combination[i][j]=i;
                }
                else{
                    combination[i][j]=combination[i-1][j-1]+combination[i-1][j];
                    if(combination[i][j]>limit) combination[i][j]=limit+1;
                }
            }
        }
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int N = sc.nextInt();
        int M = sc.nextInt();
        long K = sc.nextLong();
        if(combination[N+M][N]<K) System.out.println(-1);
        else{
            while(N!=0||M!=0){
                if(K<=combination[N+M-1][M]){
                    sb.append('a');
                    N--;
                }
                else{
                    sb.append('z');
                    //하한선 제거
                    K-=combination[N+M-1][M];
                    M--;
                }
            }
            System.out.println(sb);
        }
    }
}
/*
본격적인 DP문제로 가기 이전의 징검다리 문제.
아까 순열의 순서 구하기 문제의 로직 원리가 이해갔다면 이 문제가 훨씬 직관적이고 쉽다고 생각한다.
 */