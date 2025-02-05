package DoIt.Chapter08.Chapter08_02;

import java.util.ArrayList;
import java.util.Scanner;

public class BaekJoon1043 {
    static int[] parent;
    static int[] truth;
    static ArrayList<Integer>[] party;
    static int result;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        parent = new int[N+1];
        for(int i=1;i<=N;i++){
            parent[i]=i;
        }
        int M = sc.nextInt(); //파티 수
        int T = sc.nextInt(); //진실을 아는 사람 수
        truth = new int[T];
        for(int i=0;i<T;i++){
            truth[i]=sc.nextInt();
        }
        party = new ArrayList[M];
        for(int i=0;i<M;i++){
            party[i]=new ArrayList<>();
            int size = sc.nextInt();
            for(int j=0;j<size;j++){
                party[i].add(sc.nextInt());
            }
        }
        for(int i=0;i<M;i++){
            int first = party[i].get(0);
            for(int j=1;j<party[i].size();j++){
                union(first,party[i].get(j));
            }
        }
        for(int i=0;i<M;i++){
            boolean isPossible = true;
            int person = party[i].get(0);
            for(int j=0;j<T;j++){
                //진실을 아는 사람도 union 되어있을 수 있으니까 find 해야함.
                if(find(person)==find(truth[j])){
                    isPossible = false;
                    break;
                }
            }
            if(isPossible) result++;
        }
        System.out.println(result);
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
