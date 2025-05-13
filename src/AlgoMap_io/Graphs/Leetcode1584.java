package AlgoMap_io.Graphs;

import java.util.PriorityQueue;

/*
You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].

The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|,
where |val| denotes the absolute value of val.

Return the minimum cost to make all points connected.
All points are connected if there is exactly one simple path between any two points.
 */
public class Leetcode1584 {
    public static void main(String[] args) {

    }
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        boolean[] visited = new boolean[n];
        int total = 0;
        //int[] = {cost, pointIndex}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[0]-b[0]);
        pq.add(new int[]{0,0}); //어디서 시작하든지 상관 없으니까 0번째 점에서 시작한다.
        //!pq.isEmpty() -> edgeUsed < n 으로 바꾸면 pq에 필요없는 int[]만 남아 있을 때 종료 가능
        //실행시간 547ms -> 73ms
        int edgeUsed = 0;
        while(edgeUsed<n){
            int[] parent = pq.poll();
            int cost = parent[0];
            int pointIndex = parent[1];
            //각 점을 순차적으로 탐색
            if(!visited[pointIndex]){
                visited[pointIndex] = true;
                total+=cost; //해당 점까지의 거리를 새로 추가. 가장 가까운 점이 뽑힌거니까...
                edgeUsed++;
                for(int i=0;i<n;i++){
                    if(!visited[i]){
                        int dist = Math.abs(points[pointIndex][0]-points[i][0])
                                +Math.abs(points[pointIndex][1]-points[i][1]);
                        pq.add(new int[]{dist,i});
                    }
                }
            }
        }
        return total;
    }
}
/*
Prim's Algorithm (MST) 핵심 아이디어
=> greedy한 전략을 사용한다. 가장 연결비용이 작은걸 선택해서 바로 더해버리니까
 - 현재 연결된 정점 집합에서, 가장 저렴한 비용으로 연결 가능한 새 정점을 하나씩 추가하면서 전체를 연결하는 방식

흐름 요약:
1. 시작 정점을 하나 선택
2. 그 정점과 연결 가능한 모든 간선의 비용 계산 → 우선순위 큐(Heap) 에 저장
3. 가장 작은 비용의 간선을 꺼내서, 아직 MST에 포함되지 않은 정점을 추가
4. 새로 포함된 정점에서 갈 수 있는 다른 정점들을 다시 큐에 넣음
5. 이 과정을 모든 정점이 연결될 때까지 반복
 */

/*
Djikstra와 비슷하면서도 다른 것 같다. 연결 가능한 모든 점을 탐색하기 때문이다.

흐름 (두서없이 쓴 버전)
처음에 0과 관련된 모든 점을 넣어
1,2,3,4,5번 점이 들어갔다고 쳤을 때
0과 가장 가까운 점을 뽑고, 이를 1번이라 하면
1번을 기준으로 2,3,4,5번은 아직 방문안했으니까
또 2,3,4,5가 들어가는데
그러면 0에서 2까지, 1에서 2까지가 두개 들어가는데...
그 중 하나만 뽑히고,
나머지 하나가 뽑힐 때는 이미 visited 됐으니까 무시되어서 MST가 만들어진다는 것.
 */