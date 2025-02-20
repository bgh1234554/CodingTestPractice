package DoIt.Chapter07_NumberTheory.Chapter07_01_PrimeNumbers;

import java.util.Scanner;

public class BaekJoon1456 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long min = sc.nextLong();
        long max = sc.nextLong();
        //0 개수 햇갈리지 않기.
        int[] nums = new int[10000001];
        //소수 구하기
        for(int i=2;i<10000001;i++){
            nums[i]=i;
        }
        for(int j=2;j<=Math.sqrt(10000001);j++){
            if(nums[j]!=0){
                for(int k=j+j;k<10000001;k+=j){
                    nums[k]=0;
                }
            }
        }
        int cnt=0;
        for(int i=2;i<10000001;i++){
            long num=nums[i]; //거의 소수 후보
            if(num!=0){
//                while(min/(double)num<=nums[i]&&max/(double)num>=nums[i]){
//                    cnt++;
//                    num*=nums[i];
//                }
                //num값에 overflow가 발생할 수 있고, 나눴을 때 정확한 값을 알아야 하기 때문에 num을 double로 형변환.
                //while문과 if문이 따로 있는 이유는 min값이 A[i]보다 클 경우
                //위의 주석문과 같이 while문을 만들면 아예 while문에 들어가지 않는 상황이 발생하기 때문.
                while(nums[i]<=max/(double)num){
                    if(nums[i]>=min/(double)num){
                        cnt++; //min보다 크면 개수를 더한다.
                    }
                    num*=nums[i]; //계속 곱하다가
                }
            }
        }
        System.out.println(cnt);
    }
}
