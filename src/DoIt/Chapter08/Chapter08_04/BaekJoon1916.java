package DoIt.Chapter08.Chapter08_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon1916 {
    static ArrayList<Node>[] graph;
    static boolean[] visited;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int[] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        visited = new boolean[N+1];
        distance = new int[N+1];
        for(int i=1;i<=N;i++){
            graph[i]=new ArrayList<>();
            distance[i]=Integer.MAX_VALUE;
        }
        for(int i=0;i<M;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(end,dist));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int first = Integer.parseInt(st.nextToken());
        int last = Integer.parseInt(st.nextToken());
        //Dijkstra 부분
        pq.add(new Node(first,0));
        distance[first]=0;
        while(!pq.isEmpty()){
            Node parent = pq.poll();
            if(!visited[parent.vertex]){
                visited[parent.vertex]=true;
                for(Node child:graph[parent.vertex]){
                    if(distance[child.vertex]>distance[parent.vertex]+child.val){
                        distance[child.vertex]=distance[parent.vertex]+child.val;
                        pq.add(new Node(child.vertex,distance[child.vertex])); //거리 정보 전달
                    }
                }
            }
        }
        System.out.println(distance[last]);
    }
    static class Node implements Comparable<Node>{
        int vertex;
        int val;
        public Node(int vertex, int val) {
            this.vertex = vertex;
            this.val = val;
        }
        public int compareTo(Node o) {
            return this.val-o.val;
        }
    }
}
