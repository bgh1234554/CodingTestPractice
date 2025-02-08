package DoIt.Chapter08.Chapter08_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon1389_BFS {
    static boolean[] visited;
    static ArrayList<Integer>[] graph;
    static int[][] level;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        graph = new ArrayList[N+1];
        level = new int[N+1][N+1];
        for(int i=1;i<=N;i++){
            graph[i]=new ArrayList<>();
        }
        int M = Integer.parseInt(st.nextToken());
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            graph[S].add(E); graph[E].add(S);
        }
        for(int i=1;i<=N;i++){
            visited = new boolean[N+1];
            bfsFriend(i);
        }
//        for(int i=1;i<=N;i++){
//            for(int j=1;j<=N;j++){
//                System.out.print(level[i][j]+" ");
//            }
//            System.out.println();
//        }
        int min = Integer.MAX_VALUE; int index=0;
        for(int i=1;i<=N;i++){
            int num=0;
            for(int j=1;j<=N;j++){
                num+=level[i][j];
            }
            if(min>num){
                index=i;
                min=num;
            }
        }
        System.out.println(index);
    }
    static void bfsFriend(int i){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        visited[i]=true;
        while(!queue.isEmpty()){
            int parent = queue.poll();
            for(int child:graph[parent]){
                if(!visited[child]){
                    visited[child]=true;
                    queue.add(child);
                    level[i][child]=level[i][parent]+1;
                }
            }
        }
    }
}
/*
친구 관계 -> 각 도시 간의 도로 관계라고 생각하면 된다. 11403번의 응용버전.
BFS로도 풀 수 있다.
인접 배열 초기화 할때 Integer.MAX_VALUE 로 초기화하지 않기.
 */