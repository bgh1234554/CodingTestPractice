package DoIt.Chapter08.Chapter08_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon11404 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] distance = new int[N+1][N+1];
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(i==j) distance[i][j]=0;
                else distance[i][j]=200000;
            }
        }
        for(int i=0;i<M;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            //중복해서 들어올 수도 있으니까 최단거리로 업데이트하기.
            if(distance[S][E]>V) distance[S][E]=V;
        }
        for(int k=1;k<=N;k++){
            for(int s=1;s<=N;s++){
                for(int e=1;e<=N;e++){
                    //distance배열의 초기화를 Integer.MAX_VALUE로 하니까 더하는 과정에서
                    //overflow가 발생해 min 연산을 할 때 오류가 발생한다.
                    distance[s][e]=Math.min(distance[s][e],distance[s][k]+distance[k][e]);
                }
            }
        }
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(distance[i][j]==200000){
                    System.out.print("0 ");
                }
                else{
                    System.out.print(distance[i][j]+" ");
                }
            }
            System.out.println();
        }
    }
}
/*
Floyd-Warshall Algorithm
시간복잡도 O(N^3)이지만, 모든 노드간의 최단거리를 탐색하는데 이용된다.
Dynamic Programming에서 얻은 아이디어를 사용한다.
시작 노드를 S, 끝 노드를 E, 경유 노드를 K라고 할 때,
distance[S][E] = Math.min(distance[S][E],distance[S][K]+distance[K][E]);
어찌보면 당연하다.

전체경로의 최단 경로는 부분 경로의 최단 경로의 조합으로 이루어진다는 뜻이기 때문이다.
 */