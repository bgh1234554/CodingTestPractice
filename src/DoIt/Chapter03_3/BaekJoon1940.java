package DoIt.Chapter03_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon1940 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //재료 개수
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken()); //합
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int start=N-1, end=0, cnt=0;
        int sum;
        while(end<start){
            sum=arr[start]+arr[end];
            if(sum==M){
                cnt++; start--; end++;//맞힌 후 수정, end++올려도 start값이 그대로라 어차피 ==M일리 없으니 바로 start--
            }
            else if(sum<M){
                end++;
            }
            else {
                start--;
            }
        }
        System.out.println(cnt);
    }
}
/*
6
9
2 7 4 1 5 3
정렬 후 1 2 3 4 5 7
2018번과 다른 점은 2018번처럼 start를 작은 값, end를 큰 값으로 하니까
end가 index 끝까지 가도 M보다 작아서 이전처럼 end++을 하면 예외가 발생한다.

그래서 start을 큰값, end를 작은 값으로 한 뒤
7,1 7,2와 같은 방식으로 개수를 세면 된다.
그러다가 start와 end가 교차하면 (end<start) while문을 종료시킨다.
 */
