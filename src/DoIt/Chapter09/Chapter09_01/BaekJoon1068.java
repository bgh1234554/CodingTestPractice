package DoIt.Chapter09.Chapter09_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon1068 {
    static boolean[] visited;
    static ArrayList<Integer>[] graph;
    static int root;
    static int delete;
    static int ans=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        visited = new boolean[N];
        graph = new ArrayList[N];
        for(int i=0;i<N;i++){
            graph[i]=new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            int input = Integer.parseInt(st.nextToken());
            if(input==-1){
                root=i;
            }
            else{
                graph[i].add(input);
                graph[input].add(i);
            }
        }
        delete = Integer.parseInt(br.readLine());
        if(root!=delete){
            dfsRoot(root);
        }
        System.out.println(ans);
    }

    private static void dfsRoot(int root) {
        visited[root]=true;
        int count = 0;
        for(int x : graph[root]){
            if(!visited[x]&&x!=delete){
                //if(x==delete) break; break;가 아니라 continue;여야 다음 자식 노드도 탐색한다.
                count++;
                dfsRoot(x);
            }
        }
        if(count==0){
            ans++;
        }
    }
}
