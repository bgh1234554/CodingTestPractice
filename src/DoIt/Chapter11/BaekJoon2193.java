package DoIt.Chapter11;

import java.util.Scanner;

public class BaekJoon2193 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        //int로 계산하면 overflow때문에 틀린다.
        long[][] result = new long[N+1][2];
        result[1][0]=0;result[1][1]=1;
        for(int i=2;i<=N;i++){
            for(int j=0;j<2;j++){
                //앞의 계산 결과가 무조건 이친수라는 전제 하에 계산하는거라 추가적인 로직이 필요없다.
                result[i][0]=result[i-1][0]+result[i-1][1];
                result[i][1]=result[i-1][0];
            }
        }
        System.out.println(result[N][0]+result[N][1]);
    }
}
/*
처음으로 풀어보는 이차원 배열이 필요한 점화식.
처음에는 자리수만 가지고 풀 수 있나 1차원 배열로 생각해보자 라고 접근하면
할 수 있는 것이 없어 끝자리수 정보를 포함한 2차원 배열로 풀어야 하는구나 라고 접근하게 된다.
 */