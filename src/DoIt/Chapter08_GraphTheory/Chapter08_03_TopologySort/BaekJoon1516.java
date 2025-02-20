package DoIt.Chapter08_GraphTheory.Chapter08_03_TopologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon1516 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] graph = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            graph[i]=new ArrayList<>();
        }
        int[] indegree = new int[N+1];
        int[] buildCost = new int[N+1];
        int[] ans = new int[N+1];
        for(int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            buildCost[i]=Integer.parseInt(st.nextToken()); //얘네가 화살표를 받는 쪽이다.
            //그래프를 그려보면 왜 그런지 알 수 있다.
            while(true){
                int connection = Integer.parseInt(st.nextToken());
                if(connection==-1) break;
                graph[connection].add(i); //connection번 건물을 지어야 i번 건물을 지을 수 있으니까.
                indegree[i]++;
            }
        }
        //위상 정렬 수행
        Queue<Integer> queue = new LinkedList<>();
        for(int i=1;i<=N;i++){
            if(indegree[i]==0){
                queue.add(i);
            }
        }
        while(!queue.isEmpty()){
            int parent = queue.poll();
            for(int child:graph[parent]){
                indegree[child]--;
                //왜 Math.max가 들어가는가?
                //건물을 짓는 방법이 여러가지일 수 있기 때문에??
                //1->4->3, 1->2->3 이런 상황이라면...?
                //1->2->3, 1->4->3의 관계인 경우
                //1을 짓는데는 10이 들고, 2를 짓는데는 5, 4를 짓는데는 3이 들 때,
                //1을 짓고 2를 짓는동안 4를 다 지을 수 있으니까, 4를 짓는 시간을 포함시키지 않기 위해
                ans[child]=Math.max(ans[child],ans[parent]+buildCost[parent]);
                if(indegree[child]==0){
                    queue.add(child);
                }
            }
        }
        for(int i=1;i<=N;i++){
            System.out.println(ans[i]+buildCost[i]);
        }
    }
}
