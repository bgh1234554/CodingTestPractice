package DoIt.Chapter08_GraphTheory.Chapter08_07_MinimumSpanningTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon1197 {
    static int[] parent;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N+1];
        //parent 배열 초기화
        for(int i=1;i<=N;i++){
            parent[i]=i;
        }
        //자동 정렬되게 PQ에 Edge를 추가
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            pq.add(new Edge(start,end,val));
        }
        int count=0;
        int cost=0;
        //최소 신장 트리 알고리즘 시작
        while(count<N-1){
            Edge info = pq.poll();
            //둘이 같은데 연결하면 사이클이 생기니까.
            if(find(info.start)!=find(info.end)){
                union(info.start,info.end);
                count++;
                cost+=info.val;
            }
        }
        System.out.println(cost);
    }
    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a!=b) parent[b]=a;
    }
    static int find(int x){
        if(x==parent[x]) return x;
        else return parent[x]=find(parent[x]);
    }
    static class Edge implements Comparable<Edge>{
        public int start;
        public int end;
        public int val;
        public Edge(int start, int end, int val) {
            this.start = start;
            this.end = end;
            this.val = val;
        }
        public int compareTo(Edge o) {
            return this.val-o.val;
        }
    }
}
