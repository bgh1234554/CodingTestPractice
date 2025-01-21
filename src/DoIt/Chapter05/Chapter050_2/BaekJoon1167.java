package DoIt.Chapter05.Chapter050_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon1167 {
    static ArrayList<Node>[] tree;
    static int[] distance;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        tree = new ArrayList[V+1];
        distance = new int[V+1];
        visited = new boolean[V+1];
        for(int i=1;i<=V;i++){
            tree[i] = new ArrayList<>();
        }
        for(int i=0;i<V;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int vertex = Integer.parseInt(st.nextToken());
            while(true){
                int v = Integer.parseInt(st.nextToken());
                if(v==-1) break;
                int l = Integer.parseInt(st.nextToken());
                tree[vertex].add(new Node(v,l));
            }
        }
        bfsTree(1);
        int max = 1;
        for(int i=1;i<=V;i++){
            if(distance[i]>distance[max]){
                max=i;
            }
        }
        //지름 중 하나의 노드를 찾았으니 초기화하고 다시 bfs를 돌린다.
        distance = new int[V+1];
        visited = new boolean[V+1];
        bfsTree(max);
        max = 1;
        for(int i=1;i<=V;i++){
            if(distance[i]>distance[max]){
                max=i;
            }
        }
        System.out.println(distance[max]);
    }

    private static void bfsTree(int i) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        while(!queue.isEmpty()){
            //부모노드 정보 저장
            int vertex = queue.poll();
            visited[vertex]=true;
            //자식 노드 탐색
            for(Node node:tree[vertex]){
                if(!visited[node.vertex]){
                    queue.add(node.vertex);
                    distance[node.vertex]=distance[vertex]+node.length;
                }
            }
        }
    }

    static class Node{
        int vertex;
        int length;
        public Node(int vertex, int length){
            this.vertex=vertex;
            this.length=length;
        }
    }
}
/*
BFS에 점점 익숙해지려면 queue에 대한 반복학습과 이해가 필요한 것 같다.
트리의 지름을 구하는 문제인데,
트리의 지름은 임의의 한 점에서 가장 먼 노드를 찾고,
그 노드에서 BFS를 다시 수행해 가장 먼 노드를 찾아 그 길이를 구하면 된다.
다만, 그 아이디어를 모르는 상태에서 이 문제를 풀기는 쉽지 않은 것 같다.
그리고, 인접리스트를 구할 때 사용하는 ArrayList의 배열을 초기화할때,
각각 인덱스의 ArrayList를 초기화하기 전에 전체적으로
new ArrayList[크기]로 초기화해주는 것을 잊으면 안된다.
 */