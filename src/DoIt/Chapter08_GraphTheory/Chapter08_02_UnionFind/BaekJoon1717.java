package DoIt.Chapter08_GraphTheory.Chapter08_02_UnionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1717 {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        parent = new int[N+1];
        for(int i=0;i<=N;i++){
            //처음에는 자신이 대표 노드니까.
            parent[i]=i;
        }
        int M = Integer.parseInt(st.nextToken());
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int Q = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(Q==0){
                union(a,b);
            }
            else{
                if(isSame(a,b)){
                    System.out.println("YES");
                }
                else{
                    System.out.println("NO");
                }
            }
        }
    }

    private static boolean isSame(int a, int b) {
        a = find(a);
        b = find(b);
        return a==b;
    }

    //사실상 얘가 핵심이다.
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
/*
본격 유니온 파인드 알고리즘 구현 실습
 */