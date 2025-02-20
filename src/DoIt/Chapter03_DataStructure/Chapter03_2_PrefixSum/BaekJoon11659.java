package DoIt.Chapter03_DataStructure.Chapter03_2_PrefixSum;

import java.io.*;
import java.util.*;
public class BaekJoon11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        int[] sum = new int[size+1]; //1번째 index부터 담을거니까.
        int quiz = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<size;i++){
            int dat = Integer.parseInt(st.nextToken());
            sum[i+1]=dat+sum[i];
        }
        for(int i=0;i<quiz;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            //i번째부터 j번째까지 부분합을 구할때, 0번째부터 셀 경우 arr[j]-arr[i-1]을 사용하는데,
            //이때 i가 0일경우 i-1이 -1이 되어 NullPointerException이 발생하니까,
            //애초에 부분합을 담는 배열을 만들 때, 1부터 담게 하고, 0번째에는 0을 넣어 연산을 편하게 할 수 있다.
            sb.append(sum[to]-sum[from-1]).append('\n');
        }
        System.out.println(sb);
    }
}
