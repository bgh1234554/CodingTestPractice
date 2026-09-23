package Programmers.lv2;
/*
문제 설명

n개의 송전탑이 전선을 통해 하나의 트리 형태로 연결되어 있습니다.
당신은 이 전선들 중 하나를 끊어서 현재의 전력망 네트워크를 2개로 분할하려고 합니다.
이때, 두 전력망이 갖게 되는 송전탑의 개수를 최대한 비슷하게 맞추고자 합니다.

송전탑의 개수 n, 그리고 전선 정보 wires가 매개변수로 주어집니다.
전선들 중 하나를 끊어서 송전탑 개수가 가능한 비슷하도록 두 전력망으로 나누었을 때,
두 전력망이 가지고 있는 송전탑 개수의 차이(절대값)를 return 하도록 solution 함수를 완성해주세요.

제한사항

    n은 2 이상 100 이하인 자연수입니다.
    wires는 길이가 n-1인 정수형 2차원 배열입니다.
    wires의 각 원소는 [v1, v2] 2개의 자연수로 이루어져 있으며,
    이는 전력망의 v1번 송전탑과 v2번 송전탑이 전선으로 연결되어 있다는 것을 의미합니다.
    1 ≤ v1 < v2 ≤ n 입니다.
    전력망 네트워크가 하나의 트리 형태가 아닌 경우는 입력으로 주어지지 않습니다.

 */
import java.util.*;
public class 전력망을둘로나누기 {
    public static void main(String[] args) {
        System.out.println(solution(9, new int[][]{{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}}));
    }

    //n이 100이 최대이기 때문에 일일이 검사하는 brute force로도 풀이가 가능하다.
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