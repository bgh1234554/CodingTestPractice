package DoIt.Chapter11;

import java.util.Scanner;

public class BaekJoon1463 {
    public static void main(String[] args) {
        int[] result;
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        result = new int[N+1];
        //Base Case는 최소한으로. 너무 많이 넣으니까, 배열의 크기를 N+!으로 하면 오류가 발생한다.
        result[1]=0; //result[2]=1; result[3]=1;
        for(int i=2;i<=N;i++){
            result[i]=result[i-1]+1;
            if(i%2==0){
                result[i]=Math.min(result[i],result[i/2]+1);
            }
            if(i%3==0){
                result[i]=Math.min(result[i],result[i/3]+1);
            }
        }
        System.out.println(result[N]);
    }
}
