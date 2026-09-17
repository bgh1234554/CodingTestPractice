package Programmers.lv2;

import java.util.Arrays;

/*
문제 설명

n개의 음이 아닌 정수들이 있습니다. 이 정수들을 순서를 바꾸지 않고 적절히 더하거나 빼서 타겟 넘버를 만들려고 합니다.
예를 들어 [1, 1, 1, 1, 1]로 숫자 3을 만들려면 다음 다섯 방법을 쓸 수 있습니다.

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

사용할 수 있는 숫자가 담긴 배열 numbers, 타겟 넘버 target이 매개변수로 주어질 때 숫자를 적절히 더하고 빼서
타겟 넘버를 만드는 방법의 수를 return 하도록 solution 함수를 작성해주세요.
 */
public class 타겟넘버 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1,1,1,1,1},3)); // 5
        System.out.println(solution(new int[]{4, 1, 2, 1},4)); // 2
    }

    public static int solution(int[] numbers, int target) {
        return dfs(numbers, target);
    }

    private static int dfs(int[] numbers, int target) {
        if (numbers.length == 0) {
            return target == 0 ? 1 : 0;
        }
        int[] rest = Arrays.copyOfRange(numbers, 1, numbers.length);
        //지금 제일 앞에 있는 숫자를 더할지 뺄지 정하기
        return dfs(rest, target - numbers[0]) + dfs(rest, target + numbers[0]);
    }

    //얻어 걸린거. 중복 계산이 들어가서.
    public static int solutionLuck(int[] numbers, int target) {
        int plusAns = dfsWrong(numbers,target,true);
        int minusAns = dfsWrong(numbers,target,false);
        return (plusAns + minusAns)/2;
    }
    /*
    왜 2배가 되냐 — base case가 isPositive를 무시함

    if(numbers == null || numbers.length == 0) {
        if(target==0) return 1;
        else return 0;
    }

    배열이 다 소진됐을 때(재귀의 맨 밑바닥), isPositive가 true든 false든 상관없이 target==0인지만 확인.
    재귀 구조상 배열의 마지막 원소를 처리하는 순간,
    그 다음 호출은 항상 dfs(빈배열, 새target, true)와 dfs(빈배열, 새target, false)를 둘 다 호출.
    이 둘은 isPositive 값만 다르고 나머지(빈 배열, 같은 target)는 똑같으니까,
    완전히 똑같은 조건을 두 번 검사해서 1을 두 번 세는 셈

    즉 "유효한 부호 조합 하나"가 완성될 때마다,
    그 마지막 순간에 true/false 두 경로로 갈라지면서 같은 정답을 두 번 카운트
    이게 전체 결과가 항상 정확히 2배가 되는 진짜 이유
    (top-level에서 통째로 중복되는 게 아니라, 재귀 맨 밑바닥에서 매번 조금씩 중복되는 게 누적).
     */

    private static int dfsWrong(int[] numbers, int target, boolean isPositive) {
        if(numbers == null || numbers.length == 0) {
            if(target==0) return 1;
            else return 0;
        }
        int ans = 0;
        //copyOfRange(원본배열, from(포함), to(제외)) 기억하기

        //여기서 Arrays.copyOfRange가 length가 1이라면 그때 검사를 수행해 추가 재귀를 막았어야 했다.
        if(isPositive){
            ans += dfsWrong(Arrays.copyOfRange(numbers,1,numbers.length),target-numbers[0],true);
            ans += dfsWrong(Arrays.copyOfRange(numbers,1,numbers.length),target-numbers[0],false);
        }
        else{
            ans += dfsWrong(Arrays.copyOfRange(numbers,1,numbers.length),target+numbers[0],true);
            ans += dfsWrong(Arrays.copyOfRange(numbers,1,numbers.length),target+numbers[0],false);
        }
        return ans;
    }
}
