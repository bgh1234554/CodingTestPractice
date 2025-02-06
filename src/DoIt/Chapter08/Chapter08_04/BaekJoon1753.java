package DoIt.Chapter08.Chapter08_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon1753 {
    static ArrayList<Node>[] graph;
    static int[] distance;
    static boolean[] visited;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        graph = new ArrayList[V+1];
        distance = new int[V+1];
        visited = new boolean[V+1];
        for(int i=1;i<=V;i++){
            graph[i]=new ArrayList<>();
            distance[i]=Integer.MAX_VALUE; //거리 무한대로 초기화.
        }
        //출발 노드
        int K = Integer.parseInt(br.readLine());
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v,w));
        }
        //Dijkstra 알고리즘 시작
        //Queue가 아닌 PriorityQueue를 사용하여 구현하면 시간복잡도가 O(N)에서 O(lgN)으로 줄어든다.
        //알아서 최소거리를 가진 노드를 찾아줘서, 매번 Queue를 일일이 탐색할 필요가 없다.
        pq.add(new Node(K,0));
        distance[K]=0;
        while(!pq.isEmpty()){
            Node parent = pq.poll();
            if(!visited[parent.vertex]){
                visited[parent.vertex]=true;
                for(Node child:graph[parent.vertex]){
                    //기존 것이 거리가 더 멀면 작게 바꿔줘야 한다.
                    if(distance[child.vertex]>distance[parent.vertex]+child.value){
                        distance[child.vertex]=distance[parent.vertex]+child.value;
                        //이전 노드까지의 거리의 정보를 전달해줘야 하니까.
                        pq.add(new Node(child.vertex,distance[child.vertex]));
                    }
                }
            }
        }
        for(int i=1;i<=V;i++){
            if(visited[i]){
                System.out.println(distance[i]);
            }
            else{
                System.out.println("INF");
            }
        }
    }
    static class Node implements Comparable<Node>{
        int vertex;
        int value;
        public Node(int vertex, int value) {
            this.vertex = vertex;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value-o.value;
        }
    }
}
/*
본격 Dijkstra 알고리즘 구현 문제
 */