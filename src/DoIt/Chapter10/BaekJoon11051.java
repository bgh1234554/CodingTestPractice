package DoIt.Chapter10;

import java.util.Scanner;

public class BaekJoon11051 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[][] combination = new int[N+1][N+1];
        for(int i=0;i<=N;i++){
            combination[i][0]=1;
            combination[i][1]=1;
            combination[i][i]=1;
        }
        for(int i=2;i<=N;i++){
            for(int j=1;j<i;j++){
                combination[i][j]=combination[i-1][j]+combination[i-1][j-1];
                combination[i][j] = combination[i][j]%10007;
            }
        }
        System.out.println(combination[N][K]);
    }
}
