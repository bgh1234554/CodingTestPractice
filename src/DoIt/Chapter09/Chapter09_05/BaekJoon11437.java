package DoIt.Chapter09.Chapter09_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon11437 {
    static ArrayList<Integer>[] tree;
    static int[] depth;
    static int[] parent;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            tree[i]=new ArrayList<>();
        }
        depth = new int[N+1];
        parent = new int[N+1];
        visited = new boolean[N+1];
        for(int i=0;i<N-1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            tree[start].add(end); tree[end].add(start);
        }
        parent[1]=0;
        depth[1]=0;
        bfsDepth(1);
        int M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(lca(a,b));
        }
    }
    //뎁스가 작은 노드, 뎁스가 더 큰 노드 순으로 받아야 한다.
    private static int lca(int a, int b) {
        if(depth[a]>depth[b]){
            int tmp = a;
            a=b;
            b=tmp;
        }
        while(depth[b]!=depth[a]){
            b=parent[b];
        }
        while(a!=b){
            a=parent[a];
            b=parent[b];
        }
        return a;
    }

    private static void bfsDepth(int i) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        visited[i]=true;
        while(!queue.isEmpty()){
            int node = queue.poll();
            for(int child:tree[node]){
                if(!visited[child]){
                    depth[child]=depth[node]+1;
                    parent[child]=node;
                    visited[child]=true;
                    queue.add(child);
                }
            }
        }
    }
}
