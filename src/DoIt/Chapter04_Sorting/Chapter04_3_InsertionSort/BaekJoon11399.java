package DoIt.Chapter04_Sorting.Chapter04_3_InsertionSort;

import java.util.Scanner;

public class BaekJoon11399 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i]=scanner.nextInt();
        }
        int[] sum = new int[N];
        for(int i=1;i<N;i++){
            //삽입할 값 선정
            int insertIndex =i;
            int insertVal = arr[i];
            //삽입할 인덱스를 정렬된 부분 내에서 찾는다
            //제일 큰값부터 역으로 검색한다.
            for(int j=i-1;j>=0;j--){
                if(arr[j]<insertVal){
                    insertIndex = j+1;//작은 수 옆에 추가해야하니까 +1
                    break;
                }
                if(j==0) insertIndex = 0; //처음까지 가버렸으면 처음에 삽입
            }
            //i가 지금 있는 칸이고 insertIndex가 삽입할 곳이니까, 한칸씩 밀어야 한다.
            for(int j=i;j>insertIndex;j--){
                arr[j]=arr[j-1]; //오른쪽으로 한칸씩 옮겨야 하니까.
            }
            arr[insertIndex]=insertVal;
        }
        sum[0]=arr[0];
        for(int i=1;i<N;i++){
            sum[i]=sum[i-1]+arr[i];
        }
        int total=0;
        for(int i=0;i<N;i++){
            total+=sum[i];
        }
        System.out.println(total);
    }
}
