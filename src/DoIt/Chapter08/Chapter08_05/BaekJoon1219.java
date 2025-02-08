package DoIt.Chapter08.Chapter08_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon1219 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //도시 수
        int startCity = Integer.parseInt(st.nextToken());
        int endCity = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); //엣지 수
        Edge[] edges = new Edge[M];
        long[] distance = new long[N];
        Arrays.fill(distance,Long.MIN_VALUE);
        long[] cityCost = new long[N];
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            edges[i]=new Edge(start,end,length);
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            cityCost[i]=Long.parseLong(st.nextToken());
        }
        //벨만-포드 알고리즘의 변형
        distance[startCity]=cityCost[startCity];
        for(int i=0;i<=N+100;i++){
            for(Edge edge:edges){
                //아직 방문 안한 노드는 패스
                if(distance[edge.start]==Long.MIN_VALUE) continue;
                //가서 버는 돈이 더 많으면 업데이트
                else if(distance[edge.end]<distance[edge.start]-edge.length+cityCost[edge.end]){
                    if(i>=N-1){
                        distance[edge.end]=Long.MAX_VALUE;
                    }
                    else{
                        distance[edge.end]=distance[edge.start]-edge.length+cityCost[edge.end];
                    }
                }
                //이미 그전 노드가 양수 사이클에 들어있으면, 지금 방문하는 노드도 양수 사이클에 포함시키기.
                else if(distance[edge.start]==Long.MAX_VALUE){
                    distance[edge.end]=Long.MAX_VALUE;
                }
            }
        }
        if(distance[endCity]==Long.MIN_VALUE) System.out.println("gg");
        else if(distance[endCity]==Long.MAX_VALUE) System.out.println("Gee");
        else System.out.println(distance[endCity]);
    }
    static class Edge{
        public int start;
        public int end;
        public int length;
        public Edge(int start, int end, int length) {
            this.start = start;
            this.end = end;
            this.length = length;
        }
    }
}
/*
변형된 벨만-포드 알고리즘.
최댓값을 찾아야 한다. 그렇다고 단순히 부호만 바꾸는 것만 하는 것은 아니다.
물론, 처음에 초기화를 무한대 대신에 -무한대로 하고, distance배열 업데이트 시
또한, 양수 사이클이 있어도, 출발 노드에서 도착 노드로 갈때 이 양수 사이클을 이용해서 갈 수 없는 경우에
대한 예외 처리가 필요하다.

N의 최댓값이 100이므로, 그냥 충분히 큰 수만큼 돌리면서 업데이트하여 양수 사이클과 연결되어 있는 노드들을
업데이트하는 방식을 사용한다.
 */