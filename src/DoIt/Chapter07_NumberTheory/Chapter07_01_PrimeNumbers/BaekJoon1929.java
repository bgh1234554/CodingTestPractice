package DoIt.Chapter07_NumberTheory.Chapter07_01_PrimeNumbers;

import java.util.Scanner;

public class BaekJoon1929 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();
        int[] nums = new int[N+1];
        for(int i=2;i<=N;i++){
            nums[i]=i;
        }
        for(int j=2;j<=Math.sqrt(N);j++){
            if(nums[j]!=0){
//                for(int k=j+j;k<=N;k++){
//                    if(nums[k]!=0&&nums[k]%j==0){
//                        nums[k]=0;
//                    }
                //조건문으로 일일이 물어봐도 되지만, 효율적으로 for문을 짜 빨리 탐색할 수 있다.
//                }
                for(int k=j+j;k<=N;k+=j){
                    nums[k]=0; //j의 배수만 지우니까 k를 2j부터 시작해서 j의 배수를 모두 지우게 만들면 된다.
                }
            }
        }
        for(int i=M;i<=N;i++){
            if(nums[i]!=0){
                System.out.println(nums[i]);
            }
        }
    }
}
