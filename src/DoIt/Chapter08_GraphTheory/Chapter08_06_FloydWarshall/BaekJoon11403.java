package DoIt.Chapter08_GraphTheory.Chapter08_06_FloydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon11403 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] matrix = new int[N][N];
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                matrix[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        for(int k=0;k<N;k++){
            for(int s=0;s<N;s++){
                for(int e=0;e<N;e++){
                    if(matrix[s][k]==1&&matrix[k][e]==1){
                        matrix[s][e]=1;
                    }
                }
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
}
/*
이전 문제보다 더 쉽다.
이번에는 길의 거리가 아니라, 노드와 노드 사이의 에지의 존재 여부만 보여준다.
distance[S][E] = Math.min(distance[S][E],distance[S][K]+distance[K][E]);
를 적용하려면, distance[S][K]와 distance[K][E]가 존재하기만 하면 존재한다고 업데이트해주면 된다.
 */