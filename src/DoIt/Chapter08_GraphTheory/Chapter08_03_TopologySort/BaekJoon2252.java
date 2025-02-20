package DoIt.Chapter08_GraphTheory.Chapter08_03_TopologySort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BaekJoon2252 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); //Node
        int M = sc.nextInt(); //Edge
        ArrayList<Integer>[] graph = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            graph[i]=new ArrayList<>();
        }
        int[] indegree = new int[N+1];
        for(int i=0;i<M;i++){
            int S = sc.nextInt();
            int E = sc.nextInt();
            graph[S].add(E);
            indegree[E]++;
        }
        //위상 정렬 시작
        Queue<Integer> queue = new LinkedList<>();
        for(int i=1;i<=N;i++){
            //진입 차수가 0이 되는 노드 순차적으로 추가
            if(indegree[i]==0){
                queue.add(i);
            }
        }
        //BFS와 비슷한 방식인 것 같다.
        while(!queue.isEmpty()){
            //큐에 있는 노드를 꺼내면서
            int now = queue.poll();
            System.out.print(now+" ");
            for(int node:graph[now]){
                //노드와 연결된 에지를 전부 하나씩 제거
                indegree[node]--;
                if(indegree[node]==0){
                    //노드의 진입차수가 0이 되면 큐에 추가한다.
                    queue.add(node);
                }
            }
        }
    }
}
