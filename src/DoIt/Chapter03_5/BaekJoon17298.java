package DoIt.Chapter03_5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BaekJoon17298 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] nge = new int[N];
        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            nge[i]=-1;
        }
        Stack<Integer> stack = new Stack<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0;i<N;i++){
            if(stack.isEmpty()){stack.push(i);}
            else{
                while(!stack.isEmpty()&&arr[stack.peek()]<arr[i]){
                    //인덱스를 저장했으니까 값을 비교할때 arr을 씌워야한다.
                    int num=stack.pop();
                    nge[num]=arr[i];
                }
                stack.push(i);
            }
        }
//        while(!stack.isEmpty()){
//            nge[stack.pop()]=-1;
//        } //미리 -1로 초기화 안시키고, 이 방법대로 했을 때, 20ms가 절약되었다.
        for(int i=0;i<N;i++){
            sb.append(nge[i]+" ");
        }
        System.out.println(sb);
    }
}
/*
시간 복잡도를 줄이고 정렬을 할 수 없을 때, FIFO 성질을 만족하는 스택이 도움이 될 수 있다.
왼쪽부터 스택에 차근차근 넣다가, 자신보다 큰 수가 들어오려고 할 때, peek를 검사 해 작은 수들은 모두 pop하고,
오큰수가 지금 들어오는 수라는 것을 저장한다.

문제는 List와 달리 배열은 index값을 알아내는 메서드가 없다. 따라서 스택에 넣는 수를 index로 하고,
입력으로 들어오는 값들은 배열에 저장한다. 그래서 값을 비교할 때 arr[stack.peek()]와 같은 방법을 사용해야 한다.

pop되지 못하고 남아있는 인덱스에 해당하는 수의 오큰수 수열에는 -1을 저장한다.
스택 관련 문제 풀때 !stack.isEmpty()를 조건문에 자주 활용한다는 것을 잊지 말자.
 */