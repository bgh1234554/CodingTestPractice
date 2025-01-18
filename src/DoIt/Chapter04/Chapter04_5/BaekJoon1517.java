package DoIt.Chapter04.Chapter04_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1517 {
    static long result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }
        mergeSort(arr,0,N-1);
        System.out.println(result);
    }

    private static void mergeSort(int[] arr, int left, int right) {
        int mid = (left+right)/2;
        if(left<right){ //종료 조건
            mergeSort(arr, left, mid);
            mergeSort(arr, mid+1, right);
            merge(arr, left, mid, right);
        }
    }
    //quickSort와 비슷하게 핵심은 merge함수임. 이 함수가 두 배열을 합치는 역할을 한다.
    private static void merge(int[] arr, int left, int mid, int right) {
        //두 배열을 담을 바구니 역할을 하는 임시 배열.
        int[] tmpleft = new int[mid-left+1];
        int[] tmpright = new int[right-mid]; //둘이 크기 더해서 right+1이 나오니까 옳게 나눈 것이 맞음.
        //임시 배열에 값 넣기
        for(int i=0;i<tmpleft.length;i++){
            tmpleft[i]=arr[left+i];
        }
        for(int i=0;i<tmpright.length;i++){
            tmpright[i]=arr[mid+1+i];
        }
        int leftindex = 0, rightindex = 0, store=left;
        while(leftindex<=tmpleft.length-1&&rightindex<=tmpright.length-1){
            if(tmpleft[leftindex]<=tmpright[rightindex]){
                arr[store]=tmpleft[leftindex];
                store++;
                leftindex++;
            }
            else{
                arr[store]=tmpright[rightindex];
                result+=(tmpleft.length - leftindex);
                //왼쪽으로 인덱스가 1 이동할때마다 swap 1회인데, 병할 정렬의 두 배열을 합치는 과정에서
                //오른쪽 배열의 값이 더 작을때마다 swap이 발생하므로 result에 더해준다.
                /*
                왼쪽에 있는 N개의 수보다 작을때,
                swap의 횟수를 구하려면 왼쪽에 있는 전체 원소의 수에서 더 작은 원소의 수를 빼야하는데,
                전체 원소의 수는 tmpleft.length고,
                작은 원소의 수는 leftindex (arr에 들어간 만큼 증가하니까)니까 둘을 뺀 만큼 더하는 것이다.
                 */
                store++;
                rightindex++;
            }
        }
        while(leftindex<tmpleft.length){
            arr[store]=tmpleft[leftindex];
            leftindex++;
            store++;
        }
        while(rightindex<tmpright.length){
            arr[store]=tmpright[rightindex];
            rightindex++;
            store++;
        }
    }
}
