package DoIt.Chapter08.Chapter08_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BaekJoon1414 {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int N = Integer.parseInt(br.readLine());
        parent = new int[N];
        for(int i=0;i<N;i++){
            parent[i]=i;
        }
        int sum=0;
        for(int i=0;i<N;i++){
            String line = br.readLine();
            for(int j=0;j<N;j++){
                int tmp=0;
                if(line.charAt(j)>='a'&&line.charAt(j)<='z'){
                    sum+=line.charAt(j)-'a'+1;
                    tmp=line.charAt(j)-'a'+1;
                }
                else if(line.charAt(j)>='A'&&line.charAt(j)<='Z'){
                    sum+=line.charAt(j)-'A'+27;
                    tmp=line.charAt(j)-'A'+27;
                }
                if(i!=j&&tmp!=0){
                    pq.add(new Edge(i,j,tmp));
                }
            }
        }
        int count=0;
        int cost=0;
        while(!pq.isEmpty()){
            Edge parent = pq.poll();
            if(find(parent.start)!=find(parent.end)){
                union(parent.start,parent.end);
                count++;
                cost+=parent.val;
            }
        }
        if(count==N-1){
            System.out.println(sum-cost);
        }else{
            System.out.println(-1);
        }
    }

    static void union(int a, int b){
        a=find(a);
        b=find(b);
        parent[b]=a;
    }

    static int find(int a){
        if(a==parent[a]) return a;
        else return parent[a]=find(parent[a]);
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
}
/*
아까 다리 문제보다 훨씬 쉽다. 처음에 문자를 어떻게 숫자로 바뀔지만 잘 생각하고,
여기서도 while문을 N-1번 돌리는게 아니라 pq.isEmpty()로 계속 반복한 다음에,
parent 배열이 업데이트된 횟수를 따로 저장해서, 나중에 검사 후 print시킨다.
 */