package DoIt.Chapter08_GraphTheory.Chapter08_01_Expression;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon1707 {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int[] check;
    static boolean bipartite;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        for(int i=0;i<K;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            graph = new ArrayList[V+1];
            check = new int[V+1];
            visited = new boolean[V+1];
            for(int j=1;j<=V;j++){
                graph[j]=new ArrayList<>();
            }
            for(int j=0;j<E;j++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                graph[start].add(end);
                graph[end].add(start);
            }
            bipartite=true;
            for(int j=1;j<=V;j++){
                if(bipartite){
                    dfsBipartite(j);
                }
                else break;
            }
            if(bipartite) System.out.println("YES");
            else System.out.println("NO");
        }
    }
    //이분 그래프 판별하는 법
    //탐색 시 for문을 돌 때, 이미 visited한 배열을 다시 조사 했을 때, parent node와 같은 색깔로 분류가 되어있으면
    //이분 그래프 boolean이 false가 된다.
    private static void dfsBipartite(int j) {
        visited[j]=true;
        for(int node:graph[j]){
            if(!visited[node]){
                check[node]=(check[j]+1)%2; //두개로 나누기 위해 하나씩 색칠한다는 느낌임.
                dfsBipartite(node);
            }
            else if(check[node]==check[j]){
                bipartite=false;
            }
        }
    }
}
