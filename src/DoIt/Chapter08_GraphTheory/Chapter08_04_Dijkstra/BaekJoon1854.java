package DoIt.Chapter08_GraphTheory.Chapter08_04_Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon1854 {
    static ArrayList<Node>[] graph;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static PriorityQueue<Integer>[] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        distance = new PriorityQueue[N+1];
        for(int i=1;i<=N;i++){
            graph[i]=new ArrayList<>(); //PQ 사이즈 지정, K번째로 먼 거리가 제일 앞으로 정렬되게 만들기.
            distance[i]= new PriorityQueue<>(K, (a, b) -> b - a);
        }
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(end,length));
        }
        //Dijkstra 시작
        pq.add(new Node(1,0));
        distance[1].add(0);
        while(!pq.isEmpty()){
            Node parent = pq.poll();
            //단순한 최단거리를 구하는 것이 아니라, K번째 최단 거리를 구해야 하기 때문에,
            //distance[parent.vertex] 대신에 parent.val을 대입해야 한다.
            //child.val에 더해야 하는 거리 정보가 계속 업데이트되기 때문이다.
            int currentDistance = parent.val;
            for(Node child:graph[parent.vertex]){
                if(distance[child.vertex].size()<K){
                    distance[child.vertex].add(child.val+currentDistance);
                    pq.add(new Node(child.vertex,child.val+currentDistance));
                }
                else if(distance[child.vertex].peek()>child.val+currentDistance){
                    //기존 MAX 값 삭제
                    distance[child.vertex].poll();
                    distance[child.vertex].add(child.val+currentDistance);
                    pq.add(new Node(child.vertex,child.val+currentDistance));
                }
            }
        }
        for(int i=1;i<=N;i++){
            if(distance[i].size()==K){
                sb.append(distance[i].peek()).append("\n");
            }
            else{
                sb.append("-1").append("\n");
            }
        }
        System.out.println(sb);
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
