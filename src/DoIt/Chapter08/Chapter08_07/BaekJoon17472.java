package DoIt.Chapter08.Chapter08_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon17472 {
    //다리 만들때 쓸 dx, dy
    static int[][] movement = {{1,0},{-1,0},{0,1},{0,-1}};
    //union find + 최소신장트리
    static int[] parent;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    //지도
    static int N; static int M; static int numIsland;
    static int[][] map;
    //각각 섬의 모양
    static ArrayList<int[]> island;
    //섬 모양의 리스트
    static ArrayList<ArrayList<int[]>> islands;
    //BFS용 visited
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        //지도 만들기
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //섬 입력 받기
        numIsland=1; //1번 섬, 2번 섬... 이런식으로 구분하기 위해 맵 업데이트할 때 사용할 변수
        islands = new ArrayList<>();
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j]!=0&&!visited[i][j]){
                    bfsIsland(i,j);
                    numIsland++;
                    islands.add(island);
                }
            }
        }
        //섬별로 탐색하면서 만들 수 있는 다리 검색하기.
        for(int i=0;i<islands.size();i++){
            for(int j=0;j<islands.get(i).size();j++){
                //가능한 움직임 모두 조사.
                //기존의 섬
                int x = islands.get(i).get(j)[0];
                int y = islands.get(i).get(j)[1];
                for(int k=0;k<4;k++){
                    //움직인 곳
                    int dx = x+movement[k][0];
                    int dy = y+movement[k][1];
                    //다리 길이
                    int bridge = 0;
                    //그 방향으로 쭉 다리 만들어보기.
                    while(isSafe(dx,dy)){
                        //움직였는데 같은 섬이면 다리를 지을 수 없다.
                        if(map[dx][dy]==map[x][y]) break;
                        //같은 섬은 아닌데, 바다도 아님 -> 다른 섬 도달, PQ에 에지로 추가.
                        else if(map[dx][dy]!=0){
                            //조건이 다리 길이가 2 이상이었음.
                            if(bridge>1){
                                pq.add(new Edge(map[x][y],map[dx][dy],bridge));
                            }
                            break;
                        }
                        //같은 섬도 아니고 다른 섬도 아니니까 바다임.
                        else{
                            bridge++;
                        }
                        //같은 방향으로 계속 전진하게 만들기
                        if(movement[k][0]>0) dx++;
                        else if(movement[k][0]<0) dx--;
                        else if(movement[k][1]>0) dy++;
                        else if(movement[k][1]<0) dy--;
                    }
                }
            }
        }
        //최소 신장 트리 알고리즘 추가
        //유니온-파인드 용 parent 배열 초기화.
        parent = new int[numIsland];
        for(int i=1;i<=numIsland-1;i++){
            parent[i]=i;
        }
        int count=0;
        int cost=0;
        //count<numIsland-2라고 하면 안되는게, 모든 섬이 전부 다 연결되어 있다는 보장이 없기 때문임.
        //그래서 pq가 다 빌 때까지 알고리즘을 수행해야 함.
        while(!pq.isEmpty()){
            Edge parent = pq.poll();
            if(find(parent.start)!=find(parent.end)){
                count++; cost+=parent.val;
                union(parent.start,parent.end);
            }
        }
        if(count==numIsland-2){
            System.out.println(cost);
        }
        else{
            System.out.println(-1);
        }
    }
    private static void bfsIsland(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        island = new ArrayList<>();
        queue.add(new int[]{i,j});
        visited[i][j]=true;
        //섬 모양 업데이트
        island.add(new int[]{i,j});
        //지도 업데이트
        map[i][j]=numIsland;
        while(!queue.isEmpty()){
            int[] parent = queue.poll();
            for(int k = 0; k <4; k++){
                int dx = parent[0]+movement[k][0];
                int dy = parent[1]+movement[k][1];
                if(isSafe(dx,dy)&&!visited[dx][dy]&&map[dx][dy]!=0){
                    map[dx][dy]=numIsland;
                    visited[dx][dy]=true;
                    island.add(new int[]{dx,dy});
                    queue.add(new int[]{dx,dy});
                }
//                while(isSafe(dx,dy)) {
//                    if (visited[dx][dy] && map[dx][dy] != 0) {
//                        map[dx][dy] = numIsland;
//                        visited[dx][dy] = true;
//                        island.add(new int[]{dx, dy});
//                    }
//                    else break;
//                    //같은 방향으로 계속 전진하게 만들기
//                    //이렇게 안하면 같은 섬에 있는데도, 다른 섬으로 카운트된다.
//                    if(movement[k][0]>0) dx++;
//                    else if(movement[k][0]<0) dx--;
//                    else if(movement[k][1]>0) dy++;
//                    else if(movement[k][1]<0) dy--;
//                }
            }
        }
    }
    public static boolean isSafe(int x, int y){
        return x>=0&&x<N&&y>=0&&y<M;
    }

    static class Edge implements Comparable<Edge>{
        int start;
        int end;
        int val;
        public Edge(int start, int end, int val) {
            this.start = start;
            this.end = end;
            this.val = val;
        }
        public int compareTo(Edge o) {
            return this.val-o.val;
        }
    }
    public static void union(int a, int b){
        a=find(a);
        b=find(b);
        //if 조건문 빼먹지 말기
        if(a!=b){
            parent[b]=a;
        }
    }
    public static int find(int a){
        if(a==parent[a]) return a;
        else return parent[a]=find(parent[a]);
    }
}
/*
지도라는 실제 데이터를 받아서, 그래프로 변환하고, 각각 섬 모양에 대한 데이터를 만든 다음,
실제로 가능한 모든 에지의 경우의 수를 구현해서,
에지 리스트를 직접 만든 다음에,
최소 신장 트리를 구현해야 하는 까다로운 문제.
 */