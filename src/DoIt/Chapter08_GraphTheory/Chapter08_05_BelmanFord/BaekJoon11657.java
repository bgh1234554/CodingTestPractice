package DoIt.Chapter08_GraphTheory.Chapter08_05_BelmanFord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon11657 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long[] distance = new long[N+1];
        for(int i=1;i<=N;i++){
            distance[i]=Long.MAX_VALUE;
        }
        Edge[] edges = new Edge[M];
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            edges[i]=new Edge(start,end,length);
        }
        //벨만-포드 알고리즘 시작
        distance[1]=0;
        for(int i=0;i<N-1;i++){
            for(Edge edge:edges){
                if(distance[edge.start]!=Long.MAX_VALUE&&distance[edge.end]>distance[edge.start]+edge.length){
                    distance[edge.end]=distance[edge.start]+edge.length;
                }
            }
        }
        //음수사이클 확인하기
        boolean negative = false;
        for(Edge edge:edges){
            if(distance[edge.start]!=Long.MAX_VALUE&&distance[edge.end]>distance[edge.start]+edge.length){
                negative=true;
                break;
            }
        }
        if(!negative){
            for(int i=2;i<=N;i++){
                if(distance[i]==Long.MAX_VALUE){
                    System.out.println(-1);
                }
                else{
                    System.out.println(distance[i]);
                }
            }
        }
        else{
            System.out.println(-1);
        }
    }
    static class Edge{
        public int start;
        public int end;
        public int length;
        public Edge(int start, int end, int length) {
            this.start = start;
            this.end = end;
            this.length = length;
        }
    }
}
