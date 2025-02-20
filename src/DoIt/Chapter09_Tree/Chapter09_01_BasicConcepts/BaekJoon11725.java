package DoIt.Chapter09_Tree.Chapter09_01_BasicConcepts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon11725 {
    static boolean[] visited;
    static int[] ans;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];
        ans = new int[N+1];
        graph = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0;i<N-1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph[start].add(end);
            graph[end].add(start);
        }
        dfsTree(1);
        for(int i=2;i<=N;i++){
            System.out.println(ans[i]);
        }
    }

    private static void dfsTree(int i) {
        visited[i]=true;
        for(int x : graph[i]){
            if(!visited[x]){
                ans[x]=i;
                dfsTree(x);
            }
        }
    }
}
