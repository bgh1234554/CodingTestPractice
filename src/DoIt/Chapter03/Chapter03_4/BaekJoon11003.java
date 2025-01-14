package DoIt.Chapter03.Chapter03_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BaekJoon11003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int num = Integer.parseInt(st.nextToken());
        int size = Integer.parseInt(st.nextToken());
        Deque<Node> dq = new ArrayDeque<Node>();
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<num;i++){
            int val = Integer.parseInt(st.nextToken());
            while(!dq.isEmpty()&&dq.getLast().val>val){
                dq.removeLast(); //지금 들어오는 노드들보다 val이 큰 노드들은 최솟값이 될 수 없으므로 미리 제거한다.
            } //근데 empty면 예외가 발생하니까 !isEmpty()를 넣어준다.

            //isEmpty()관련해서 미리 처리하고, continue를 넣으니까 뽑을 값이 없는데 parseInt를 호출해서
            //NoSuchElementException(StringTokenizer 관련 문제)이 뜬 것 같다.
            //num이 1이거나 size가 1이면 continue시 에러가 발생하니까.
            //그래서 remove기능을 담당하는 while문을 앞으로 땡겨서 isEmpty를 break문으로 처리하지 말고,
            //조건문에 넣어주니 문제가 사라졌다.
            dq.addLast(new Node(i,val));
            if(i-dq.getFirst().index>=size){
                dq.removeFirst();
            } //판단하기 전에 범위에 해당하지 않는 노드들은 dq에서 빼준다.
            sb.append(dq.getFirst().val+" "); //여기서 판단
        }
        System.out.println(sb);
    }
    public static class Node {
        public int index;
        public int val;
        public Node(int index, int val) {
            this.index = index;
            this.val =val;
        }
    }
}
/*
12 3
1 5 2 3 6 2 3 7 3 5 2 6
 */
