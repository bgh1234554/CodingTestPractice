package DoIt.Chapter04_Sorting.Chapter04_1_BubbleSort;

import java.util.Scanner;

public class BaekJoon2750 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i]=scanner.nextInt();
        }
        for(int i=0;i<N-1;i++){
            boolean swap = false;
            for(int j=0;j<N-1-i;j++){
                if(arr[j]>arr[j+1]){
                    int tmp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=tmp;
                    swap=true;
                }
            }
            if(!swap) break; //최적화된 버블 정렬. 시간 복잡도는 그대로.
        }

        for(int i=0;i<N;i++){
            System.out.println(arr[i]);
        }
    }
}
