package DoIt.Chapter05.Chapter050_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon11724 {
    static ArrayList<Integer>[] arrayList;
    static boolean[] visited;
    static int count=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //노드 수
        int M = Integer.parseInt(st.nextToken()); //에지 수 (여기선 질의 수와 동일)
        arrayList = new ArrayList[N+1]; //1부터 저장할거라서.
        visited = new boolean[N+1];
        for(int i=1;i<=N;i++){
            arrayList[i] = new ArrayList<>(); //인접 리스트 구현할 ArrayList 초기화
        }
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            arrayList[u].add(v); //u와 v가 인접해 있으니까 둘 다 추가
            arrayList[v].add(u);
        }
        for(int i=1;i<=N;i++){
            if(!visited[i]){
                dfs(i);
                count++;
            }
        }
        System.out.println(count);
    }
    //i의 인접리스트에 들어가 있는 노드에 대해 dfs를 재실행. 방문기록을 남기기 위해 boolean배열 인덱스 값 true로 바꿈.
    public static void dfs(int i){
        visited[i]=true;
        for(int j:arrayList[i]){//arrayList[i]안에 들어있는 값 - i와 인접한 노드들.
            if(!visited[j]){//방문했으면 넘어가고, 방문하지 않았으면 dfs를 재귀적으로 호출한다.
                dfs(j);
            }
        }
    }
}
//단순한 DFS문제. 인접리스트를 구현할 때 ArrayList를 사용했다.
