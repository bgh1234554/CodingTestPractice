package DoIt.Chapter05.Chapter05_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon13023 {
    static ArrayList<Integer>[] arrayList;
    static boolean[] visited;
    static boolean found = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //사람 수
        int M = Integer.parseInt(st.nextToken()); //관계 수
        arrayList = new ArrayList[N];
        visited = new boolean[N];
        for(int i=0;i<N;i++){
            arrayList[i] = new ArrayList<>();
        }
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arrayList[a].add(b);
            arrayList[b].add(a);
        }
        for(int i=0;i<N;i++){
            //visited[i]=true; 필요 없음. 하나씩 탐색해서 출력하는 것이 아니라,
            //4개의 선으로 연결되어있는지, 노드 5개가 연결되어있는지를 찾아야 하니까
            dfsFriend(i,1);
            if(found){
                System.out.println(1);
                break;
            }
        }
        if(!found){
            System.out.println(0);
        }
    }

    private static void dfsFriend(int i, int depth) {
        if(depth==5||found){ //다른데서 찾았으면 탐색할 필요 없으니까.
            found=true;
            return;
        }
        //위의 for문에서 visited[i]=true를 안썼다고 여기서까지 안쓰면,
        //두 노드만을 계속 왔다갔다하면서 depth가 5가 될 때까지 함수 호출이 반복된다.
        //0 1, 0 2, 0 3, 0 4, 0 5는 0을 출력해야 하지만 1을 출력하게 된다.
        visited[i]=true;
        //밑에서 i의 자식 노드들을 탐색할 건데, visited[i]를 true로 만들어놓지 않으면,
        //자식 노드들이 다시 연결된 노드들을 dfs로 탐색할 때, 부모 노드로 돌아가버리면서
        //depth가 1 증가하는 결과가 발생하므로, visited[i]를 true로 만들어놓는다.
//        for(int j=0;j<arrayList[i].size();j++){
//            if(!visited[arrayList[i].get(j)]){
//                dfsFriend(arrayList[i].get(j),depth+1); //재귀적으로 dfs 호출, depth는 1 증가.
//            }
//        }
        //get 안쓰고 arrayList[i]라 하고, arrayList[j]라고 하다가 틀려버렸다.
        //for-each문 안쓰면 햇갈린다.
        for (int neighbor : arrayList[i]) {
            if (!visited[neighbor]) {
                dfsFriend(neighbor, depth + 1); // 재귀적으로 dfs 호출, depth는 1 증가.
            }
        }
        visited[i]=false;
        //i를 부모 노드로 갖는 자식 노드에서 탐색을 모두 완료했으니, 다시 false로 만들어준다.
    }
}
/*
A는 B의 친구, B는 C의 친구, C는 D의 친구, D는 E의 친구
친구 관계가 4번 이어진다 -> 깊이가 5.
DFS로 풀어야한다.
깊이가 5인 친구관계가 있는지 확인하면 되는데, 확인하는 방법을 생각해내는 것이 어렵다.

DFS는 구현 자체는 그리 어렵지 않지만, 그 외의 문제 조건에 따라 dfs의 두번째 인자로 무엇을 줘야 하는지,
visited boolean 배열의 true false 값을 어떻게 만들어야 하는지가 초점인 것 같다.

dfs를 재귀적으로 호출할 때, for each 문을 안쓰면 햇갈릴수가 있고, 다른 인덱스의 값을 알아야 하는 것도 아니니
그냥 for each문을 왠만하면 써야겠다.
 */