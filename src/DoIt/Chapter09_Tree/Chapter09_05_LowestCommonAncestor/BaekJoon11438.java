package DoIt.Chapter09_Tree.Chapter09_05_LowestCommonAncestor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon11438 {
    static ArrayList<Integer>[] tree;
    static boolean[] visited;
    static int[] depth;
    static int[][] parents;
    static int K; //가능한 최대 부모 (2^K단위 기준)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            tree[i]=new ArrayList<>();
        }
        depth = new int[N+1];
        visited = new boolean[N+1];
        //가능한 최대 부모 (2^K단위 기준)
        K = (int) Math.floor(Math.log(N)/Math.log(2));
        parents = new int[K+1][N+1]; //0부터 K까지니까 K+1로 크기 선언해야함.
        //완전 이진트리라는 보장이 없으므로,
        //2^K번째 부모까지 구할 수 있어야함. (노드가 15개면 8번째 부모까지는 구할 수 있게)
        for(int i=0;i<N-1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            tree[start].add(end); tree[end].add(start);
        }
        depth[1]=0;
        bfsDepth(1);
        //k=0인건 이미 구했으니까, k가 1일때부터 초기화하기
        for(int k=1;k<=K;k++){
            for(int n=1;n<=N;n++){
                parents[k][n]=parents[k-1][parents[k-1][n]];
            }
        }
        int M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(lcaUpgrade(a,b));
        }
    }

    private static int lcaUpgrade(int a, int b) {
        if(depth[a]>depth[b]){
            int tmp = a;
            a=b;
            b=tmp;
        }
        //모든 뎁스의 차이를 2진수로 표현할 수 있으니까.
        //2^K 단계씩 depth 올리기
        for(int k=K;k>=0;k--){
            if(Math.pow(2,k)<=depth[b]-depth[a]){
                //a보다 더 위로 넘어가면 안되니까.
                if(depth[a]<=depth[parents[k][b]]){
                    b=parents[k][b];
                }
            }
        }
        //뎁스 맞춘 뒤 2^K 단계씩 부모 올리기
        //최초로 두 부모가 달라지는 K를 찾아야 하니까, 큰 값에서부터 역으로 탐색.
        for(int k=K;k>=0;k--){
            if(parents[k][a]!=parents[k][b]){
                a=parents[k][a];
                b=parents[k][b];
            }
        }
        //이후 다르면 그 노드의 바로 위 부모노드가 공통 노드
        //a가 b의 조상 노드들 중 하나라면, 첫번째 for문에서 depth를 맞추는 동안 이미 a==b이므로,
        //2번째 for문에서 아무 일이 일어나지 않기 때문에, 마지막에 if조건문을 걸어줘야 최소 공통 조상을
        //올바르게 찾을 수 있게 된다. 그 외의 경우에는 두번째 for문을 돌면 무조건 a와 b가 달라지게 된다.
        if(a!=b){
            a = parents[0][a];
        }
        return a;
    }

    private static void bfsDepth(int i) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        visited[i]=true;
        while(!queue.isEmpty()){
            int node = queue.poll();
            for(int child:tree[node]){
                if(!visited[child]){
                    depth[child]=depth[node]+1;
                    parents[0][child]=node;
                    visited[child]=true;
                    queue.add(child);
                }
            }
        }
    }
}
