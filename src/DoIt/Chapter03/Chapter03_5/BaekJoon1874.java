package DoIt.Chapter03.Chapter03_5;

import java.util.*;

public class BaekJoon1874 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int N = scanner.nextInt();
        int num=1;
        Stack<Integer> stack = new Stack<>();
        int i;
        for(i=0;i<N;i++){
            int x = scanner.nextInt();
            if(stack.isEmpty()){
                while(num<x){
                    stack.push(num); num++;
                    sb.append("+\n");
                }
                if(num==x){
                    stack.push(num); num++;
                    sb.append("+\n");
                    stack.pop();
                    sb.append("-\n");
                }
                else{
                    System.out.println("NO");
                    break;
                }
            }
            else if(stack.peek()==x){
                stack.pop();
                sb.append("-\n");
            }
            else if(stack.peek()<x){
                while(num<x){
                    stack.push(num); num++;
                    sb.append("+\n");
                }
                if(num==x){
                    stack.push(num); num++;
                    sb.append("+\n");
                    stack.pop();
                    sb.append("-\n");
                }
                else{
                    System.out.println("NO");
                    break;
                }
            }
            else{
                while(stack.peek()>x){
                    stack.pop();
                    sb.append("-\n");
                }
                if(stack.peek()==x){
                    stack.pop();
                    sb.append("-\n");
                }
                else{
                    System.out.println("NO");
                    break;
                }
            }
        }
        if(i==N){
            System.out.println(sb);
        }
    }
}
/*
책에 있는 풀이와 달리
스택이 비어 있는 경우,
- 일단 입력값보다 작으면 다 push,
그 후 입력값과 num값 비교 후 num값이 입력값보다 크면 null, 같으면 push 후 pop

맨 위가 입력값이 경우,
- 그냥 pop하면 된다.
입력값보다 작은 경우,
- 비어있는 경우와 사실상 동일하다.
큰 경우로 나누어서 풀었다.
- 일단 pop하다가, 값이 나오면 pop을 추가로 하고, 값이 안나오고 더 작은 수로 바로 넘어가면 NO를 출력한다.

맨 마지막에는 for문을 다 돌아야만 sb에 저장된 문자열을 출력하도록 if문을 추가했다.

처음에는 문제를 이해하는 것부터 어려웠다.
num -> 다음에 stack에 넣을 수
x -> 입력값
 */