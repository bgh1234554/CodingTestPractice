package Programmers.lv2;

import java.util.*;
public class 전력망을둘로나누기 {
    public static void main(String[] args) {
        System.out.println(solution(9, new int[][]{{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}}));
    }

    public static int solution(int n, int[][] wires) {
        boolean[] visited;
        ArrayList<Integer>[] list = new ArrayList[n+1];
        for(int i=1; i<=n; i++) {
            list[i] = new ArrayList<>();
        }
        //인접 리스트 그래프 만들기
        for(int i=0; i<wires.length; i++) {
            list[wires[i][0]].add(wires[i][1]);
            list[wires[i][1]].add(wires[i][0]);
        }
        int ans = n;
        for(int[] wire : wires) {
            visited = new boolean[n+1];
            //ArrayList<Integer>에서 특정 값으로 제거할 시, Integer.ValueOf() 사용하기
            list[wire[0]].remove(Integer.valueOf(wire[1]));
            list[wire[1]].remove(Integer.valueOf(wire[1]));

            int count = bfs(list, visited);
            //count개와 n-count개의 차이를 구해야 하니까
            ans = Math.min(ans, Math.abs(n - 2*count));

            list[wire[0]].add(wire[1]);
            list[wire[1]].add(wire[0]);
        }
        return ans;
    }
    public static int bfs(ArrayList<Integer>[] list, boolean[] visited){
        int count = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        while(!q.isEmpty()){
            int cur = q.poll();
            count++;
            for(int child : list[cur]){
                if(!visited[child]){
                    visited[child] = true;
                    q.add(child);
                }
            }
        }
        return count;
    }
}
/*
1. ArrayList<Integer>에서 값으로 원소 제거 시 반드시 Integer.valueOf() 사용
   list.remove(5)               → int로 인식되어 "인덱스 5"를 제거해버림 (버그)
   list.remove(Integer.valueOf(5)) → 객체로 인식되어 "값 5"를 제거함 (정답)

2. BFS 시작 노드도 반드시 visited 마킹과 큐 추가를 한 세트로 처리
   visited[start] = true;
   q.add(start);
*/