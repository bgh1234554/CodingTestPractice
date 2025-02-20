package DoIt.Chapter06_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BaekJoon1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        ArrayList<Conference> arrayList = new ArrayList<>();
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arrayList.add(new Conference(start,end));
        }
        Collections.sort(arrayList);
        int cur=0; int cnt=1;
        for(int i=1;i<N;i++){
            if(arrayList.get(i).start >= arrayList.get(cur).end){
                cur=i;
                cnt++;
            }
        }
        System.out.println(cnt);
    }
    static class Conference implements Comparable<Conference>{
        public int start;
        public int end;

        public Conference(int start, int end) {
            this.start = start;
            this.end=end;
        }

        @Override
        public int compareTo(Conference con) {
            if(this.end==con.end){
                return this.start-con.start;
            }
            return this.end-con.end;
        }
    }
}
//MIT Introduction to Algorithms에 있는 예제라 1트에 맞췄다!
