package DoIt.Chapter03.Chapter03_1;

import java.io.*;
import java.util.*;

public class BaekJoon1546 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//        int n = Integer.parseInt(br.readLine());
//        List<Integer> scores = new ArrayList<Integer>();
//        StringTokenizer st = new StringTokenizer(br.readLine()," ");
//        int sum=0;
//        for(int i=0;i<n;i++){
//            String str = st.nextToken();
//            scores.add(Integer.valueOf(str));
//            sum+=Integer.parseInt(str);
//        }
//        int max = Collections.max(scores);
//        sb.append(sum*100.0/max/n);
//        System.out.println(sb);
//    }
    //max함수 쓰기 위해서 만든 Collection을 안쓰면 더 빠르다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int[] scores = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int sum=0;
        int score=0; int max=0;
        for(int i=0;i<n;i++){
            score=Integer.parseInt(st.nextToken());
            sum+=score;
            if(max<score) max=score; //? 연산자 통한 조건문 사용 가능.
        }
        sb.append(sum*100.0/max/n);
        System.out.println(sb);
    }
}
