package DoIt.Chapter07.Chapter07_02;

import java.util.Scanner;

public class BaekJoon11689 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();
        long result=N;
        for(int i=2;i<=Math.sqrt(N);i++){
            if(N%i==0){
                result -= result/i;
                //i를 약수로 가지는 N의 다른 인수들 제거
                while(N%i==0){
                    N/=i;
                }
            }
        }
        if(N>1){ //아직 남은 소인수가 N의 값으로 남아있다.
            result -= result/N;
        }
        System.out.println(result);
    }
}
/*
여기서는 인수 하나에만 오일러 피 함수를 구현하면 되기 때문에, 배열이 필요 없다.
while문과 마지막 if문이 이해가 안될 수도 있는데, 직접 소인수분해된 N을 만들어보고 연산을 수행하면 된다.
 */