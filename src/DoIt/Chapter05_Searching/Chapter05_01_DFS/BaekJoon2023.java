package DoIt.Chapter05_Searching.Chapter05_01_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon2023 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dfsPrime(2,1);
        dfsPrime(3,1);
        dfsPrime(5,1);
        dfsPrime(7,1);
    }

    private static void dfsPrime(int i, int digit) {
        if(digit==N){ //N자리수를 만들었다면ㄴ
            if(isPrime(i)){
                System.out.println(i);
            }
            return; //소수 아니면 끝.
        }
        else{
            if(isPrime(i)){
                dfsPrime(i*10+1,digit+1);
                dfsPrime(i*10+3,digit+1);
                //5로 끝나면 무조건 5의 배수라 탐색할 필요가 없음.
                dfsPrime(i*10+7,digit+1);
                dfsPrime(i*10+9,digit+1);
            }
        }
    }

    public static boolean isPrime(int num){
        if(num==1){
            return false;
        }
        for(int i=2;i<=Math.sqrt(num);i++){
            if(num%i==0){
                return false;
            }
        }
        return true;
    }
}
/*
맨 앞자리가 소수인지, 그 뒷자리랑 합해도 소수인지, 그 뒷자리랑 합해도 소수인지.... 반복이므로
재귀적으로 함수를 호출해야 하기 때문에 dfs로 만들 수 있다.

 */