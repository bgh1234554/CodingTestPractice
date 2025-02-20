package DoIt.Chapter07_NumberTheory.Chapter07_03_EuclideanAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon1033 {
    static ArrayList<Node>[] graph; //리스트의 배열
    static boolean[] visited;
    static long[] amounts;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        visited = new boolean[N];
        graph = new ArrayList[N]; //배열 초기화
        amounts = new long[N];
        for(int i=0;i<N;i++){
            graph[i] = new ArrayList<>(); //리스트 초기화
        }
        long total = 1;
        for(int i=1;i<N;i++){
            st=new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            //연결 관계와 비율 관계 저장
            graph[a].add(new Node(b,p,q
            ));
            graph[b].add(new Node(a,q,p));
            total*=p*q/gcd(p,q);
        }
        amounts[0]=total;
        dfs(0);
        long curgcd=gcd(amounts[0],amounts[1]);
        for(int i=2;i<N;i++){
            curgcd=gcd(curgcd,amounts[i]);
        }
        for(int i=0;i<N;i++){
            System.out.print(amounts[i]/curgcd+" ");
        }
    }
    static long gcd(long a, long b){
        if(b==0) return a;
        else return gcd(b,a%b);
    }
    //어디랑 연결되어있는지랑, 비율 관계 저장해야하니까 클래스로 만든다.
    //Node랑 Vertex 정보 저장하려고 클래스로 만든 것과 비슷함.
    static class Node{
        public int b;
        public int p;
        public int q;
        public Node(int b, int p, int q) {
            this.b = b;
            this.p = p;
            this.q = q;
        }
    }
    static void dfs(int i){
        visited[i]=true;
        for(Node child:graph[i]){
            if(!visited[child.b]){
                amounts[child.b]=(child.q* amounts[i])/child.p;
                dfs(child.b);
            }
        }
    }
}
/*
문제가 길어서 당황할 수 있다.
그래프 탐색 + 정수론이라는 문제 밑에 적혀진 주제를 다 이용하면 된다.
일단 연결 그래프를 그려본다.
총 양을 정해놓고 그래프에 있는 비율대로 분배하면 된다.
비율 정보와 인접 노드 정보 모두를 저장해야 하기 때문에, 클래스를 만들어야 한다.
총 양을 어떻게 정하냐? 각 비율관계가 나올때마다 최소공배수를 곱해서 어떤 비율관계로 나눌때도 정수가 나오게 한다.
이 양을 임의대로 0에 배정하고, dfs로 그래프를 탐색하면서 새로 방문한 노드의 양을 계산한다.
이후, 필요한 재료의 질량을 모두 더한 값이 최소가 되어야 하기 때문에 모든 값의 최대공약수를 구해, 출력할 때
나눠준다.
 */