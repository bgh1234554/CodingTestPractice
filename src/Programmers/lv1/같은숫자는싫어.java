package Programmers.lv1;

import java.util.*;

/*
문제 설명

배열 arr가 주어집니다. 배열 arr의 각 원소는 숫자 0부터 9까지로 이루어져 있습니다.
이때, 배열 arr에서 연속적으로 나타나는 숫자는 하나만 남기고 전부 제거하려고 합니다.
단, 제거된 후 남은 수들을 반환할 때는 배열 arr의 원소들의 순서를 유지해야 합니다.

예를 들면,

    arr = [1, 1, 3, 3, 0, 1, 1] 이면 [1, 3, 0, 1] 을 return 합니다.
    arr = [4, 4, 4, 3, 3] 이면 [4, 3] 을 return 합니다.

배열 arr에서 연속적으로 나타나는 숫자는 제거하고 남은 수들을 return 하는 solution 함수를 완성해 주세요.

제한사항

    배열 arr의 크기 : 1,000,000 이하의 자연수
    배열 arr의 원소의 크기 : 0보다 크거나 같고 9보다 작거나 같은 정수
 */
public class 같은숫자는싫어 {
    public static void main(String[] args) {

    }
    public int[] solution(int []arr) {
        ArrayList<Integer> answer = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for(int num:arr){
            //스택이 비어있거나, 중복된 숫자가 아니면 push
            if(stack.isEmpty() || stack.peek()!=num){
                if(!stack.isEmpty()) {
                    //비어있지 않은 경우, 새 숫자니까, 기존 숫자 pop해서 answer에 넣기
                    int popped = stack.pop();
                    answer.add(popped);
                }
                stack.push(num);
            }
        }
        //stack 내부에 남아있는 숫자 처리
        while(!stack.isEmpty()){
            int popped = stack.pop();
            answer.add(popped);
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    /*
    Java의 Stack 클래스 자체가 사실 legacy 클래스이다.(Vector를 상속하고 내부적으로 synchronized라 성능 오버헤드가 있음)
    진짜로 스택이 필요한 상황(괄호 짝 맞추기, 후위 표기법 계산처럼 여러 개가 쌓였다가 순서대로 빠지는 경우)이라면
    Deque<Integer> stack = new ArrayDeque<>()를 쓰는 게 요즘 관용적인 방식이다.
    push()/pop() 메서드 이름은 똑같이 써서 헷갈릴 일도 없다.

    Deque를 사용하면 아래와 같이 애초에 Answer라는 ArrayList 자체가 필요 없다.
     */
    public int[] solutionWithDeque(int[] arr) {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int num : arr) {
            if (deque.isEmpty() || deque.peekLast() != num) {
                deque.addLast(num);
            }
        }
        return deque.stream().mapToInt(Integer::intValue).toArray();
    }

    /*
    사실 이 문제에선 stack을 단순히 마지막 값을 기억하는 용도로 쓰기 때문에,
    여러개를 쌓아뒀다가 LIFO 형태로 꺼내는 스택의 특징을 이용할 일이 없다.
    따라서, 다음과 같이 스택 없이, 가장 낮은 값만 기억하는 형태로 짤 수도 있다.
     */
    public int[] solutionWithoutStack(int[] arr) {
        List<Integer> answer = new ArrayList<>();
        int last = -1; // arr 값 범위 밖으로 초기 값 세팅

        for (int num : arr) {
            if (num != last) {
                answer.add(num);
                last = num;
            }
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

}
