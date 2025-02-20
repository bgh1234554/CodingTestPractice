package DoIt.Chapter08_GraphTheory.Chapter08_01_Expression;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BaekJoon2251 {
    //{물 주는 양동이, 물 받는 양동이}
    static int[][] moves = {{0,1},{0,2},{1,0},{1,2},{2,0},{2,1}};
    static boolean[][] visited=new boolean[201][201];;
    static boolean[] answer=new boolean[201];
    //굳이 sort 안쓰고 여기다 true/false 저장해서 for문으로 앞에서부터 탐색.
    static int[] sizes = new int[3];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sizes[0]=sc.nextInt();
        sizes[1]=sc.nextInt();
        sizes[2]=sc.nextInt();
        bfsBucket();
        for(int i=0;i<answer.length;i++){
            if(answer[i]) System.out.print(i+" ");
        }
    }
    private static void bfsBucket() {
        Queue<Bucket> queue = new LinkedList<>();
        queue.add(new Bucket(0,0));
        visited[0][0]=true;
        answer[sizes[2]]=true;
        while(!queue.isEmpty()){
            Bucket bucket = queue.poll();
            int A = bucket.A;
            int B = bucket.B;
            int C = sizes[2]-A-B;
            for(int i=0;i<6;i++){
                int[] buckets = {A,B,C};
                buckets[moves[i][1]]+=buckets[moves[i][0]];
                buckets[moves[i][0]]=0;
                if(buckets[moves[i][1]]>sizes[moves[i][1]]){
                    buckets[moves[i][0]]=buckets[moves[i][1]]-sizes[moves[i][1]];
                    buckets[moves[i][1]]=sizes[moves[i][1]];
                }
                if(!visited[buckets[0]][buckets[1]]){
                    visited[buckets[0]][buckets[1]]=true;
                    queue.add(new Bucket(buckets[0],buckets[1]));
                    if(buckets[0]==0){
                        answer[buckets[2]]=true;
                    }
                }
            }
        }
    }
    static class Bucket{
        public int A;
        public int B;
        public Bucket(int A, int B){
            this.A=A;
            this.B=B;
        }
    }
}
/*
그래프 데이터를 입력받아서 탐색하는 방식으로 문제를 푸는 것이 아니라,
그래프 원리를 적용해 그래프를 역으로 그리는 문제. 매우 중요하다.
A,B,C가 가질 수 있는 특정 무게 상태 -> 노드
물통의 물을 옮기는 것 -> 에지, 새롭게 만들어진 상태 -> 다른 노드.
한 노드에서 다른 노드는 무조건 6개가 가능함 (그 미로찾기 처럼 경우의수가 정해져 있음)
노드에 물의 상태를 저장해야하므로, 클래스로 A 양동이와 B 양동이의 양을 저장한다.

visited 이중 배열은 처음 사용해봤고, 답을 정렬하라고 했을 때,
boolean 배열과 for문을 이용해서 출력할 수 있는 것도 알게 되었다.
 */