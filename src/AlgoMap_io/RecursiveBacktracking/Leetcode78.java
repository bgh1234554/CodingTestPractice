package AlgoMap_io.RecursiveBacktracking;

import java.util.ArrayList;
import java.util.List;
/*
Given an integer array nums of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.
 */
public class Leetcode78 {
    public static void main(String[] args) {

    }
    static List<List<Integer>> res;
    static List<Integer> sol;
    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        sol = new ArrayList<>();
        //백트래킹으로 모든 경우의 수 탐색
        backtrack(nums, 0);
        return res;
    }
    //for loop를 사용하는 백트래킹 - 루프를 통해 선택하거나, 선택하지 않는 두 가지 경우를 처리.
    public void backtrack(int[] nums, int start) {
        res.add(new ArrayList<>(sol)); //현재 상태 추가
        //새롭게 추가 하기
        for(int i = start; i < nums.length; i++) {
            //해당 원소를 추가한 경우
            sol.add(nums[i]);
            backtrack(nums, i + 1);
            //해당 원소를 추가하지 않은 경우
            sol.remove(sol.size() - 1);
        }
        //이와 같은 방식으로 두개로 갈라짐.
    }
    /*
    빈 배열로 시작 -> 매번 탐색할 때마다 해당 원소를 선택하거나, 않거나 두 가지 중 하나
    [] -> [], [1] (1 넣거나 말거나) -> [], [2], [1], [1,2] (2 넣거나 말거나) -> ...

    DFS와 비슷한 방식으로 구현 (png 파일 1,2 참조)
    전역 배열 Result = [] (모든 경우의 수를 가지는 리스트)
    Sol = [] (경우의 수를 저장할 임시 배열) => 베이스 케이스에 도달할 시 이전 단계로 돌아가야 한다..
     */
    //Algomap.io의 파이썬 코드에서 구현한 방법 - 각 재귀 호출에서 원소를 선택하거나, 선택하지 않는
    //두 가지 경우를 처리한다. 매번 자기 자신을 두번 재귀적으로 호출한다.
    private static void backtrackcopilot(int[] nums, int start) {
        //모든 재귀 호출이 끝난 다음에 현재 상태를 추가
        if (start == nums.length) {
            res.add(new ArrayList<>(sol));
            return;
        }

        // 선택하지 않는 경우, 인덱스 넘기고 재귀적으로 호출
        backtrackcopilot(nums, start + 1);

        // 선택하는 경우 sol 리스트에 추가
        sol.add(nums[start]);
        //추가한 상태에서 백트래킹 시작
        backtrackcopilot(nums, start + 1);
        //백트래킹 탐색이 다 끝났기 때문에, 함수가 다시 리턴됐으므로, sol 배열에서 가장 최근에
        //업데이트된 원소를 제거해야 한다.
        sol.remove(sol.size() - 1);
    }
}
/*
Recursive Backtracking 이란?
=> "ALL" solutions가 들어간 내용들 ...

1. 결정 후
2. 재귀
3. 베이스 케이스 근접
4. 어느 순간 결정 취소
 */
