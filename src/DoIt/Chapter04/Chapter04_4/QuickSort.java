package DoIt.Chapter04.Chapter04_4;

import java.util.Arrays;

public class QuickSort {
    /*
    한번도 QuickSort를 제대로 구현해본 적이 없어서 한번 해봐야겠다.
     */
    public static void main(String[] args) {
        int[] arr = {10,9,8,-1,5,46,6,4,3,2,1};
        quickSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
    public static void quickSort(int[] arr, int left, int right){
        if(left<right){ //그렇지 않을 경우에 재귀적인 호출을 방지하기 위함. 이미 정렬이 다 됐다는 것이므로.
            int pivotindex = selectPivot(arr,left,right);
            //인덱스 기준으로 왼쪽 정렬, 오른쪽 정렬
            quickSort(arr,left,pivotindex-1);
            quickSort(arr,pivotindex+1,right);
        }
    }

    //사실 이 함수가 핵심임. 피벗을 정렬된 위치에 맞춰주고 그 위치를 리턴하기 때문.
    //그리고 왼쪽 오른쪽또 부분적으로 정렬된 상태가
    //되기 때문에 재귀적으로 호출되면서 정렬이 되는 시스템임.
    public static int selectPivot(int[] arr, int left, int right) {
        int pivot = arr[right];
        int start=left, end=right-1;
        //시작과 끝에 포인터를 두고
        while(start<=end){
            //왼쪽부터 pivot보다 큰 인덱스 찾기
            while(start<=right&&arr[start]<pivot) start++;
            //오른쪽부터 pivot보다 작은 인덱스 찾기
            while(end>=left&&arr[end]>pivot) end--;
            //바꾸기
            if(start<=end){ //end가 끝까지 가서 -1일 수도 있기 때문에, loop를 아직 안빠져나갔으니까 조건문이 필요하다.
                swap(arr,start,end);
                //이후 다음 수 검사
                start++;
                end--;
            }
        }
        //피벗 위치 알맞게 변경. right로 보내놨던걸 다시 start인덱스에 옮긴다.
        //마지막에 start==end인 상태에서 루프를 돌 때, end는 두번째 while 루프에서 -1이 되고,
        //start는 처음으로 pivot보다 큰 수에 멈추기 때문에, start과 right를 swap하면 완료된다.
        swap(arr,start,right);
        return start;
    }
    public static void swap(int[] arr,int i,int j){
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }
}
