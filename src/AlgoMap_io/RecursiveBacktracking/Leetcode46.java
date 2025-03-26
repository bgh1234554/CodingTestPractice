package AlgoMap_io.RecursiveBacktracking;

import java.util.ArrayList;
import java.util.List;
/*
Given an array nums of distinct integers, return all the possible . You can return the answer in any order.
 */
public class Leetcode46 {
    public static void main(String[] args) {
        System.out.println(permute(new int[] {1,2,3}));
    }
    static List<List<Integer>> res = new ArrayList<>();
    static List<Integer> sol = new ArrayList<>();
    public static List<List<Integer>> permute(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        backtrack(nums,visited);
        return res;
    }
    public static void backtrack(int[] nums, boolean[] visited) {
        if (sol.size()==nums.length) {
            //result 배열에 sol을 집어넣으려면 새롭게 ArrayList<>를 만들어서 넣어야 한다.
            //전역 변수라서 값을 복사해서 넣는 것이 아니라 참조가 되기 때문에, 디버깅을 해보면
            //계속해서 sol이 업데이트되기 때문에 나중에 sol이 다 비워지고 출력이 되면 결국 빈 배열만 출력된다.
            res.add(new ArrayList<>(sol));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(!visited[i]){
                //해답 배열에 추가하는 경우
                sol.add(nums[i]);
                visited[i] = true;
                backtrack(nums,visited);
                //추가하지 않는 경우
                sol.remove(sol.size()-1);
                visited[i]=false;
            }
        }
    }
}
