package AlgoMap_io.Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
/*
You are given a network of n nodes, labeled from 1 to n.

You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi),
where ui is the source node, vi is the target node,
and wi is the time it takes for a signal to travel from source to target.

We will send a signal from a given node k.
Return the minimum time it takes for all the n nodes to receive the signal.
If it is impossible for all the n nodes to receive the signal, return -1.
 */
public class Leetcode743 {
    public static void main(String[] args) {
        System.out.println(networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
        System.out.println(networkDelayTime(new int[][]{{1, 2, 1}}, 2, 1));
        System.out.println(networkDelayTime(new int[][]{{1, 2, 1}}, 2, 2));
    }
    public static int networkDelayTime(int[][] times, int N, int K) {
        boolean[] visited = new boolean[N+1];
        int[] distance = new int[N+1]; //K에서 edge까지의 최단거리
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
        for(int i=1;i<=N;i++){
            distance[i] = Integer.MAX_VALUE;
        }
        distance[K] = 0;
        pq.add(new int[]{K,0});
        while(!pq.isEmpty()){
            int[] parent = pq.poll();
            int node = parent[0];
            visited[node] = true;
            //노드와 이웃한 노드를 검사해 최단거리 갱신되면 pq에 추가
            for(int[] edge : times){
                if(edge[0]==node && !visited[edge[1]]){
                    if(distance[edge[1]]>distance[node]+edge[2]){
                        distance[edge[1]] = distance[node]+edge[2];
                        pq.add(new int[]{edge[1],distance[edge[1]]});
                    }
                }
            }
        }
        int max = 0;
        for(int i=1;i<=N;i++){
            if(distance[i]==Integer.MAX_VALUE){
                return -1;
            }
            max = Math.max(max,distance[i]);
        }
        return max;
    }
    public static int networkDelayTimeWithAdjacencyList(int[][] times, int N, int K) {
        List<int[]>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] time : times) {
            graph[time[0]].add(new int[]{time[1], time[2]}); // {to, weight}
        }
        boolean[] visited = new boolean[N+1];
        int[] distance = new int[N+1]; //K에서 edge까지의 최단거리
        Arrays.fill(distance, Integer.MAX_VALUE); //fill 메서드로 한번에 채우기 가능
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
        distance[K] = 0;
        pq.add(new int[]{K,0});
        while(!pq.isEmpty()){
            int[] parent = pq.poll();
            int node = parent[0];
            visited[node] = true;
            //노드와 이웃한 노드를 검사해 최단거리 갱신되면 pq에 추가
            for(int[] edge : graph[node]){
                if(!visited[edge[0]] && distance[edge[0]]>distance[node]+edge[1]){
                    distance[edge[0]] = distance[node]+edge[1];
                    pq.add(new int[]{edge[0],distance[edge[0]]});
                }
            }
        }
        int max = 0;
        for(int i=1;i<=N;i++){
            if(distance[i]==Integer.MAX_VALUE){
                return -1;
            }
            max = Math.max(max,distance[i]);
        }
        return max;
    }
}
/*
1차 제출 시 - 이미 이중 배열로 그래프가 구현되어 있으니까 굳이 인접리스트로 그래프를 다시 표현할 필요가 없다.
근데 그러니 Dijkstra 탐색 시 times 이중배열을 매번 탐색해 속도가 너무 느리다.

2차 제출 - 인접 그래프를 만들 때 걸리는 시간이 훨씬 나중에 이득을 본다.

실행시간 38ms -> 9ms로 감소
 */