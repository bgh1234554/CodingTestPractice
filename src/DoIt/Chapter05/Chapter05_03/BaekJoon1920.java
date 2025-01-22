package DoIt.Chapter05.Chapter05_03;

import java.util.Arrays;
import java.util.Scanner;

public class BaekJoon1920 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        int M = sc.nextInt();
        for(int i=0;i<M;i++){
            int num = sc.nextInt();
            int start=0, end=N-1;
            while(start<=end){
                int mid = (start+end)/2;
                if(arr[mid]==num){
                    break;
                }
                else if(arr[mid]<num){
                    start=mid+1;
                }
                else{
                    end=mid-1;
                }
            }
            if(start<=end){
                System.out.println(1);
            }
            else{
                System.out.println(0);
            }
        }
    }
}
