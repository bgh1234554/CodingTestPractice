package DoIt.Chapter05.Chapter050_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon1260 {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken()); //시작점
        graph = new ArrayList[N+1];
        visited = new boolean[N+1];
        for(int i=0;i<=N;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        for(int i=1;i<=N;i++){
            Collections.sort(graph[i]);
        }
        dfs(V);
        System.out.println();
        visited = new boolean[N+1]; //visited 배열 초기화
        bfs(V);
        System.out.println();
    }
    public static void dfs(int i){
        visited[i]=true;
        System.out.print(i+" ");
        for(int j:graph[i]){
            if(!visited[j]){
                dfs(j);
            }
        }
    }
    public static void bfs(int i){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        visited[i]=true;
        while(!queue.isEmpty()){
            //queue에서 뺀 노드를 출력한다.
            int parent = queue.poll();
            System.out.print(parent+" ");
            for(int j:graph[parent]){
                if(!visited[j]){
                    queue.add(j);//뺀 노드의 자식 노드들을 하나씩 다 방문한다.
                    visited[j]=true;
                }
            }
        }
    }
}
/*
bfs는 재귀함수로 구현할 수 없기 때문에 Queue로 구현한다.
bfs 구현은 C++ STL을 써본 2020년 이후 5년만이라 그런지 생각이 잘 안나서, 잘 기억해놔야겠다.
DFS는 재귀함수, BFS는 큐. 둘다 visited boolean 배열을 사용한다.
DFS는 true로 만들고 for문으로 자식들한테 재귀적으로 방문.
BFS는 큐에 부모 노드를 넣고, 큐에서 빼서 출력하면서, 정보를 저장해놨다가 자식 노드들을 큐에 넣고,
큐가 빌때까지 반복하면서 visited boolean 배열을 업데이트.
 */