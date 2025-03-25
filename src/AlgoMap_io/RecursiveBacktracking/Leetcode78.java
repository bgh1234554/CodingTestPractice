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
}
/*
Recursive Backtracking 이란?
=> "ALL" solutions가 들어간 내용들 ...

1. 결정 후
2. 재귀
3. 베이스 케이스 근접
4. 어느 순간 결정 취소
 */
