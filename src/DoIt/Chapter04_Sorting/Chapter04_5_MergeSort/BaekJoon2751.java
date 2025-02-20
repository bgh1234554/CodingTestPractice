package DoIt.Chapter04_Sorting.Chapter04_5_MergeSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon2751 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(br.readLine());
        }
        mergeSort(arr, 0, N-1);
        for(int i=0;i<N;i++){
            sb.append(arr[i]+"\n");
        }
        System.out.println(sb);
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
/*
전형적인 O(nlogn)을 활용하는 정렬 문제. 옛날에는 C++STL을 사용해서 풀었던 기억이 있다.
당연히 이미 구현되어있는 C++ STL이 더 빠르다. 언어의 차이도 있고.
 */