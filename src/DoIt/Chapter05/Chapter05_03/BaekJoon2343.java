package DoIt.Chapter05.Chapter05_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon2343 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        int max = 0, sum = 0;
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max,arr[i]);
            sum+=arr[i];
        }
        int start = max, end = sum, mid;
        while(start<=end){
            mid = (start+end)/2;
            if(countDisk(arr,mid)<=M){
                end=mid-1;
                //M개 이하의 음반에 나눠들어가지면 용량이 크다는 거니까, 더 작은 용량에도 들어가질 수 있는지 확인.
            }
            else{
                start=mid+1;
            }
        }
        System.out.println(start);
    }
    public static int countDisk(int[] arr, int mid){
        int count=1, sum=0;
        for(int i=0;i<arr.length;i++){
            if(sum+arr[i]<=mid){
                sum+=arr[i];
            }
            else{
                count++;
                sum=arr[i];
            }
        }
        return count;
    }
}
