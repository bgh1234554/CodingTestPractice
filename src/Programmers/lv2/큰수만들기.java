package Programmers.lv2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/*
문제 설명

어떤 숫자에서 k개의 수를 제거했을 때 얻을 수 있는 가장 큰 숫자를 구하려 합니다.

예를 들어, 숫자 1924에서 수 두 개를 제거하면 [19, 12, 14, 92, 94, 24] 를 만들 수 있습니다.
이 중 가장 큰 숫자는 94 입니다.

문자열 형식으로 숫자 number와 제거할 수의 개수 k가 solution 함수의 매개변수로 주어집니다.
number에서 k 개의 수를 제거했을 때 만들 수 있는 수 중,
가장 큰 숫자를 문자열 형태로 return 하도록 solution 함수를 완성하세요.

제한 조건
    number는 2자리 이상, 1,000,000자리 이하인 숫자입니다.

입출력 예
number 	k 	return
"1924" 	2 	"94"
"1231234" 	3 	"3234"
"4177252841" 	4 	"775841"
 */
public class 큰수만들기 {
    public static void main(String[] args) {

    }

    //O(N)으로 순회할 수 있게 Stack으로 교체.
    //한번에 여러 숫자를 pop해도 괜찮음. 그래서 그리디임.
    //k=2고 7329에서 2개를 빼야 한다면 9를 push해야할 때 3과 2를 모두 뺄 수 있음.
    public String solution(String number, int k){
        Stack<Character> stack = new Stack<>();
        int count = 0;
        for(int i=0;i<number.length();i++){
            if(stack.isEmpty()) stack.push(number.charAt(i));
            //만약 앞의 자리 숫자가 더 작으면, 전부 지운다.
            else if (stack.peek()<number.charAt(i)){
                while(!stack.isEmpty() && stack.peek()<number.charAt(i) && count<k){
                    stack.pop();
                    count++;
                }
                stack.push(number.charAt(i));
            }
            else stack.push(number.charAt(i));
        }
        //다 순회했는데도 k값을 못채웠으면 맨 뒷자리를 k랑 맞춰질때까지 pop
        if(count<k) {
            for(int i=number.length()-1;i>=0;i--){
                if(count==k) break;
                stack.pop();
                count++;
            }
        }
        //문자열 연결 시 StringBuilder로 문자 만들기
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    //Claude Code의 리팩토링
    // 큰 수 만들기 - 단조 스택 풀이, 시간복잡도 O(N)
    // 아이디어: 앞자리보다 뒷자리가 크면 앞자리를 지우는 게 이득이다 (k개를 다 쓸 때까지).
    public String solutionRefactor(String number, int k) {
        // 앞에서부터 확정해 가는 결과를 담는 스택 (맨 뒤 = 맨 위)
        // 실무에서 사장된 Stack 대신 최신 클래스인 Deque 사용
        Deque<Character> stack = new ArrayDeque<>();
        int removed = 0; // 지금까지 지운 숫자 개수

        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            //if / else if / else는 결국 while로 pop하고 마지막에 push로 합쳐짐.
            //while (!stack.isEmpty() && count < k && stack.peek() < c)로 pop하고, 그 뒤에 push 한 줄만 두면 됨.
            while (!stack.isEmpty() && removed < k && stack.peekLast() < c) {
                stack.pollLast();
                removed++;
            }
            stack.addLast(c);
        }

        // k-count 번만 돌면 되니까 깔끔하게 while문으로 표기 가능
        while (removed < k) {
            stack.pollLast();
            removed++;
        }

        StringBuilder sb = new StringBuilder();
        //Deque에 대해선 앞에서부터 for-each 문법 사용 가능
        for (char ch : stack) {
            sb.append(ch);
        }
        return sb.toString();
    }

    //2차 발상 - 앞자리보다 뒷자리가 크면 앞자리를 빼면 되네, 그러면 한글자씩 검사해볼까?
    //1문제 시간 초과 - 지울 때마다 맨 앞부터 다시 훑고, 문자열을 새로 만듬.
    //테스트 케이스 하나가 n이 10⁶에 가깝고 k도 커서 그 비용을 못 버티는 거야.
    public String solutionRepeatScan(String number, int k){
        int count = 0;
        while(count<k){
            int prevlength = number.length();
            for(int i=0;i<number.length()-1;i++){
                if(number.charAt(i)<number.charAt(i+1)){
                    number = number.substring(0,i)+number.substring(i+1);
                    count++;
                    break;
                }
            }
            if(prevlength==number.length()&&count<k){
                number = number.substring(0,number.length()-1);
                count++;
            }
        }
        return number;
    }

    //1차 발상 - 한번에 한자리씩 빼면 되지 않을까?
    //재귀 에러 - 재귀가 k번 깊어지는데, k가 수십만이면 StackOverflowError가 날 수 있음.
    //number가 최대 1,000,000자리인데 int는 10자리 정도까지만 담긴다.
    //11자리부터 NumberFormatException이 난다.
    //long으로 바꿔도 19자리쯤이 한계라서 해결이 안 되기 때문에, 이 문제는 숫자를 문자열로 다뤄야 해.
    public String solutionRecursive(String number, int k) {
        if(k==0) return number;
        int max = 0;
        for(int i=0;i<number.length();i++){
            String first = number.substring(0,i);
            String second = number.substring(i+1);
            max = Math.max(max,Integer.parseInt(first+second));
        }
        String answer = "" + max;
        return solutionRecursive(answer,k-1);
    }
}
/*
패턴: 새 원소가 들어올 때, 조건을 만족하는 동안 맨 위를 계속 꺼낸다 (while).
같은 패턴인 문제들: 주식 가격, 올바른 괄호, 크게 만들기 계열, 다음 큰 수 찾기 같은 "단조 스택" 유형이야.
시간복잡도가 O(n)인 이유: while 안에 꺼내는 동작이 있어도 각 원소는 들어가는 것 1번, 나오는 것 1번뿐이야.
전체 꺼내는 횟수가 n을 넘을 수 없어서 이중 반복처럼 보여도 O(n²)이 아니야.
 */