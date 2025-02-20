package DoIt.Chapter08_GraphTheory.Chapter08_06_FloydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1389 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] relation = new int[N+1][N+1];
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(i==j) relation[i][j]=0;
                else relation[i][j]=100000;
            }
        }
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            relation[S][E]=1; relation[E][S]=1;
        }
        for(int k=1;k<=N;k++){
            for(int s=1;s<=N;s++){
                for(int e=1;e<=N;e++){
                    relation[s][e]=Math.min(relation[s][e],relation[s][k]+relation[k][e]);
                }
            }
        }
        int min = Integer.MAX_VALUE; int index=0;
        for(int i=1;i<=N;i++){
            int num=0;
            for(int j=1;j<=N;j++){
                num+=relation[i][j];
            }
            if(min>num){
                index=i;
                min=num;
            }
        }
        System.out.println(index);
    }
}
/*
친구 관계 -> 각 도시 간의 도로 관계라고 생각하면 된다. 11403번의 응용버전.
BFS로도 풀 수 있다.
인접 배열 초기화 할때 Integer.MAX_VALUE 로 초기화하지 않기.
 */