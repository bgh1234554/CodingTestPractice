package DoIt.Chapter04_Sorting.Chapter04_2_SelectionSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon1427 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int[] arr = new int[str.length()];
        for(int i=0;i<str.length();i++){
            arr[i]=Integer.parseInt(str.substring(i,i+1));
        }
        for(int i=0;i<arr.length;i++){
            int max = i;
            for(int j=i+1;j<arr.length;j++){
                //어차피 첫 부분은 마지막에 swap할때 검사하니까 검사 안해도 됨.
                if(arr[max]<arr[j]){
                    max=j;
                }
            }
            if(arr[i]<arr[max]){
                int tmp=arr[i];
                arr[i]=arr[max];
                arr[max]=tmp;
            }
        }
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]);
        }
    }
}
