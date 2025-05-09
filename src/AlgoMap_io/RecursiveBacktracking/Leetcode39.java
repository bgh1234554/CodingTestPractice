package AlgoMap_io.RecursiveBacktracking;

import java.util.ArrayList;
import java.util.List;
/*
Given an array of distinct integers candidates and a target integer target,
return a list of all unique combinations of candidates where the chosen numbers sum to target.
You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times.
Two combinations are unique if the frequency of at least one of the chosen numbers is different.

The test cases are generated such that the number of unique combinations
that sum up to target is less than 150 combinations for the given input.
 */
public class Leetcode39 {
    public static void main(String[] args) {
        System.out.println(combinationSum(new int[] {2,3,6,7},7));
    }
    static List<List<Integer>> res;
    static List<Integer> sol;
    //내 풀이 - Combination 에서 살짝 변경 - 실행시간 3ms
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new ArrayList<>();
        //최솟값부터 탐색
        for(int i=0;i<candidates.length;i++){
            sol = new ArrayList<>();
            sol.add(candidates[i]);
            backtrack(candidates,target,candidates[i],i);
        }
        return res;
    }
    public static void backtrack(int[] candidates, int target, int sum, int minIndex) {
        if(sum>target){
            return;
        }
        else if(sum==target){
            res.add(new ArrayList<>(sol));
        }
        for(int i=minIndex;i<candidates.length;i++){
            sol.add(candidates[i]);
            backtrack(candidates,target,sum+candidates[i],i);
            sol.remove(sol.size() - 1);
        }
    }
}
