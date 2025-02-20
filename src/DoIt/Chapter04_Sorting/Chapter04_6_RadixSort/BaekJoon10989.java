package DoIt.Chapter04_Sorting.Chapter04_6_RadixSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon10989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(br.readLine());
        }
        radixSort(arr,5);
        for(int i=0;i<N;i++){
            sb.append(arr[i]).append("\n");
        }
        System.out.println(sb);
    }
    public static void radixSort(int[] arr, int maxDigits){
        int[] data=new int[arr.length]; //배열 값 담을 임시 배열.
        int digit=1; //비교하는 자리수 (1의 자리, 10의 자리)
        for(int i=0;i<maxDigits;i++){
            int[] lastdigit = new int[10]; //자리수 배열
            for(int j=0;j<arr.length;j++){
                lastdigit[(arr[j]/digit)%10]++;
            }
            for(int j=1;j<10;j++){
                lastdigit[j]+=lastdigit[j-1]; //누적합으로 바꾸기
            }
            for(int j=arr.length-1;j>=0;j--){
                //arr의 해당하는 값의 마지막 자리수를 찾는다.
                //마지막 자리에 저장된 값에서 1일 뺀 곳에 데이터를 넣어야 한다.
                //이후 그보다 왼쪽에 데이터를 넣어야 하니까 1을 뺀다.
                data[lastdigit[(arr[j]/digit)%10]-1]=arr[j];
                lastdigit[(arr[j]/digit)%10]--;
            }
            for(int j=0;j<arr.length;j++){
                arr[j]=data[j]; //정렬된 값 복사
            }
            digit*=10;
        }
    }
}
/*
기수 정렬을 이용하여 풀었다.
기수 정렬을 처음봐서 구현된 것을 해석하느라 시간을 썼다.
기수 정렬은 자릿수를 기준으로 정렬하는 방법이고, 시간 복잡도가 O(kN)이라, (k는 자릿수)
많은 수를 정렬할 때 유용하지만, 최댓값의 자리수를 모르거나, 최댓값이 너무 크면 비효율적이고, 공간도 많이 차지한다.
 */