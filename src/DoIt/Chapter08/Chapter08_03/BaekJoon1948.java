package DoIt.Chapter08.Chapter08_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon1948 {
    static ArrayList<Node>[] normal;
    static ArrayList<Node>[] reversed;
    static int[] indegree;
    static int[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        normal = new ArrayList[N+1];
        reversed = new ArrayList[N+1];
        indegree = new int[N+1];
        result = new int[N+1];
        for(int i=1;i<=N;i++){
            normal[i]= new ArrayList<>();
            reversed[i]=new ArrayList<>();
        }
        int M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            normal[start].add(new Node(end,dist));
            reversed[end].add(new Node(start,dist));
            indegree[end]++;
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int init = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());
        Queue<Integer> queue = new LinkedList<>();
        queue.add(init);
        //각 노드별 가장 오래 걸리는 시간 업데이트
        while(!queue.isEmpty()){
            int parent = queue.poll();
            for(Node next:normal[parent]){
                indegree[next.vertex]--;
                //가장 오래 걸리는 시간을 찾아야 하니까, 기존 경로와 이전 도시+오는데 경로의 값을 비교해야 한다.
                //밑에서도 다시 쓰이는 수식.
                result[next.vertex]=Math.max(result[next.vertex],next.distance+result[parent]);
                if(indegree[next.vertex]==0){
                    queue.add(next.vertex);
                }
            }
        }
        //가장 오래 걸리는 경로에 포함되는 길이 몇 개인지 출력해야 한다.
        queue = new LinkedList<>();
        queue.add(dest);
        int ans=0;
        boolean[] visited = new boolean[N+1];
        while(!queue.isEmpty()){
            int parent = queue.poll();
            for(Node next:reversed[parent]){
                //현재 최대 경로가, 그 전 도시까지 도달하는 최대 경로+그 도시에서 여기까지 걸리는 시간과 같다면
                //1분도 쉬지 않고 와야하는 길이 된다.
                if(result[next.vertex]+next.distance==result[parent]){
                    ans++;
                    //중복 계산 방지를 위해 DFS, BFS에서 썼던 visited 배열을 사용한다.
                    //같은 도시를 두번 방문하면, 그 이후에 출발점으로 가는 길들이 중복으로 더해지니까,
                    //ans에만 1을 더하고, queue에는 추가하지 않는다.
                    if(!visited[next.vertex]){
                        visited[next.vertex]=true;
                        queue.add(next.vertex);
                    }
                }
            }
        }
        System.out.println(result[dest]);
        System.out.println(ans);
    }
    //거리 정보와 연결된 노드 정보를 기억해야 하니까, class로 저장
    static class Node{
        int vertex;
        int distance;
        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
}
/*
위상 정렬에서 그래프 에지 뒤집기라는 아이디어를 처음 사용하였다.
 */
