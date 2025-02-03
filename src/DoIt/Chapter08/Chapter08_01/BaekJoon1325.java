package DoIt.Chapter08.Chapter08_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon1325 {
    static ArrayList<Integer>[] computers;
    static int[] trusts;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        computers = new ArrayList[N+1];
        trusts = new int[N+1];
        visited = new boolean[N+1];
        for(int i=1;i<=N;i++){
            computers[i]=new ArrayList<>();
        }
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            computers[S].add(E);
        }
        for(int i=1;i<=N;i++){
            if(!computers[i].isEmpty()){
                visited=new boolean[N+1];
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                visited[i]=true;
                while(!queue.isEmpty()){
                    int parent = queue.poll();
                    for(int computer:computers[parent]){
                        if(!visited[computer]){
                            queue.add(computer);
                            trusts[computer]++;
                            visited[computer]=true;
                        }
                    }
                }
            }
        }
        int max = 0;
        for(int trust:trusts){
            max = Math.max(trust,max);
        }
        for(int i=1;i<=N;i++){
            if(trusts[i]==max){
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb);
    }

//    private static void bfsTrust(int i) {
//        Queue<Integer> queue = new LinkedList<>();
//        queue.add(i);
//        visited[i]=true;
//        while(!queue.isEmpty()){
//            int parent = queue.poll();
//            for(int computer:computers[parent]){
//                if(!visited[computer]){
//                    queue.add(computer);
//                    trusts[computer]++;
//                    visited[computer]=true;
//                }
//            }
//        }
//    }
}
