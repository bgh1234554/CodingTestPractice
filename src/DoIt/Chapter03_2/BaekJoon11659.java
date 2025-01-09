package DoIt.Chapter03_2;

import java.io.*;
import java.util.*;
public class BaekJoon11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        int[] sum = new int[size+1];
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
            sb.append(sum[to]-sum[from-1]).append('\n');
        }
        System.out.println(sb);
    }
}
