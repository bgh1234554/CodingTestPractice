package DoIt.Chapter08.Chapter08_02;

import java.util.Scanner;

public class BaekJoon1976 {
    //사실상 얘가 핵심이다.
    static int[] parent;
    static int[][] cities;
    static int[] route;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        parent = new int[N+1];
        for(int i=1;i<=N;i++){
            //대표 노드 배열 초기화
            parent[i]=i;
        }
        cities = new int[N+1][N+1];
        int M = sc.nextInt();
        route = new int[M];
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                //도로 연결 상황을 나타내주는 것.
                cities[i][j]=sc.nextInt();
            }
        }
        for(int i=0;i<M;i++){
            route[i]=sc.nextInt();
        }
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(cities[i][j]==1){
                    //도로끼리 연결
                    union(i,j);
                }
            }
        }
        int destination = find(route[0]);
        for(int i=1;i<M;i++){
            if(destination!=find(route[i])){
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
    private static int find(int a) {
        if(a==parent[a]) return a;
        else return parent[a]=find(parent[a]); //대입 후 리턴.
        //대표 노드를 찾아서 거기에 값을 동일시키기. 재귀함수로 구현.
    }

    private static void union(int a, int b) {
        a = find(a); //대표 노드로 연결해야 하니까.
        b = find(b);
        if(a!=b){
            //b를 a에다 연결해야 하니까.
            parent[b]=a;
        }
    }
}
