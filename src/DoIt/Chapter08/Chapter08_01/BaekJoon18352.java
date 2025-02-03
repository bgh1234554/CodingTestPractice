package DoIt.Chapter08.Chapter08_01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BaekJoon18352 {
    static ArrayList<Integer>[] cities;
    //visited와 distance배열을 합쳐서 풀수도 있다. visited가 false인 상태를 -1로 하고,
    //이후에 distance에 했듯이 값을 넣는 방식으로.
    static int[] distance;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();
        int X = sc.nextInt();
        cities = new ArrayList[N+1];
        distance = new int[N+1];
        visited = new boolean[N+1];
        for(int i=1;i<=N;i++){
            cities[i] = new ArrayList<>();
        }
        for(int i=0;i<M;i++){
            int start = sc.nextInt();
            int end = sc.nextInt();
            cities[start].add(end);
        }
        distance[X]=0;
        bfs(X);
        boolean exists=false;
        for(int i=1;i<=N;i++){
            if(distance[i]==K){
                System.out.println(i);
                exists=true;
            }
        }
        if(!exists){
            System.out.println(-1);
        }
    }
    private static void bfs(int x) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(x);
        visited[x]=true;
        while(!queue.isEmpty()){
            int parent = queue.poll();
            for(int city:cities[parent]){
                if(!visited[city]){
                    queue.add(city);
                    visited[city]=true;
                    //거리 계산 방법을 어떻게 만드느냐가 더 중요하다.
                    distance[city]=distance[parent]+1;
                }
            }
        }
    }
}
