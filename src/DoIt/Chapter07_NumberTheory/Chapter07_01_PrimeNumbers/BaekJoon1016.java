package DoIt.Chapter07_NumberTheory.Chapter07_01_PrimeNumbers;

import java.util.Scanner;

public class BaekJoon1016 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long min = sc.nextLong();
        long max = sc.nextLong();
        boolean[] nums = new boolean[(int) (max-min+1)];
        for(long i=2;i<=Math.sqrt(max);i++){
            long pow = i*i;
            /*
             for(int k=j+j;k<10000001;k+=j){
                    nums[k]=0;
             }
             에라토스테네스의 체 구현할 때, 2번째 중첩 for문의 시간 복잡도를 줄이기 위해
             k의 시작점을 2j로 하고 j씩 건너뛰어 0으로 만든 것처럼, 이 for문도 똑같이 최적화가 필요하다.
             */
//            for(long j=min;j<=max;j++){
//                if(j%pow==0){
//                    nums[(int) (j-min)]=true;
//                }
//            }
            //min부터 max까지 순차적으로 탐색하면 시간초과가 발생한다. 제곱수의 배수만 탐색할 수 있도록 해주는
            //for문이 필요하다.
            long time = min/pow; //제곱수의 몇배수부터 탐색해야하는지 알려준다.
            //pow가 4일때 min이 4면 1부터고, 5,6,7이면 8부터 탐색해야 2여야 하기 때문에,
            //나누어떨어지지 않으면 1을 더해준다.
            if(min%pow!=0) time++;
            for(long times=time;times*pow<=max;times++){
                nums[(int)((times*pow)-min)]=true;
            }
        }
        int count=0;
        for(boolean num : nums){
            if(!num){
                count++;
            }
        }
        System.out.println(count);
    }
}
/*
에라토스테네스의 체 방식을 구현할 때는, 걸러내는 역할을 하는 두번째 for문을 얼마나 효과적으로 탐색할지
시작 인덱스와 건너뛰는 방식을 정하는 것이 제일 중요하다.
 */