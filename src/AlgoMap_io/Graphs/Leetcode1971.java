package AlgoMap_io.Graphs;

import java.util.*;

public class Leetcode1971 {
    public static void main(String[] args) {
        int[][] edges = {{0,1},{1,2},{2,0}};
        System.out.println(validPath(3,edges,0,2));
        edges = new int[][]{{0,1},{0,2},{3,5},{5,4},{4,3}};
        System.out.println(validPath(6,edges,0,5));
    }
    static boolean found;
    static boolean[] visited;
    public static boolean validPath(int n, int[][] edges, int source, int destination) {
        found = false;
        visited = new boolean[n];
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i=0;i<n;i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] edge : edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        dfs(graph,source,destination);
        return found;
    }
    public static void dfs(ArrayList<Integer>[] graph, int source, int destination){
        if(source==destination||found){
            found = true;
            return;
        }
        for(int i : graph[source]){
            if(!visited[i]){
                visited[i] = true;
                dfs(graph,i,destination);
                if(found){
                    return;
                }
            }
        }
    }
    public boolean validPathBFS(int n, int[][] edges, int source, int destination) {
        if (source == destination) return true;

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }

        Set<Integer> seen = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        seen.add(source);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node == destination) return true;
            for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
                if (!seen.contains(neighbor)) {
                    seen.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return false;
    }
}

