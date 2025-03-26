package AlgoMap_io.RecursiveBacktracking;

import java.util.ArrayList;
import java.util.List;

public class Leetcode77 {
    public static void main(String[] args) {
        System.out.println(combine(1,1));
    }
    static List<List<Integer>> res;
    static List<Integer> sol;
    //내가 만든 풀이 - 실행시간 20ms
    public static List<List<Integer>> combine(int n, int k) {
        res = new ArrayList<>(); //위에서 초기화시키니까 리트코드에서 오류가 발생한다.
        //가능한 조합의 최솟값부터 차례로 탐색
        for(int i=1;i<=n-k+1;i++){
            boolean[] visited = new boolean[n+1];
            //1~n
            sol = new ArrayList<>();
            sol.add(i);
            visited[i]=true;
            backtrack(n,k,visited,i);
        }
        return res;
    }
    public static void backtrack(int n, int k, boolean[] visited, int minElem){
        if(sol.size()==k){
            res.add(new ArrayList<>(sol));
            return;
        }
        for(int i=minElem;i<=n;i++){
            if(!visited[i]){
                sol.add(i);
                visited[i] = true;
                backtrack(n,k,visited,i);
                sol.remove(sol.size()-1);
                visited[i]=false;
            }
        }
    }

    //Algomap.io의 풀이 - 실행시간 15ms
    public List<List<Integer>> combineAlogmapIo(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> sol = new ArrayList<>();
        backtrackAlgomapIo(n, k, sol, ans);
        return ans;
    }

    private void backtrackAlgomapIo(int x, int k, List<Integer> sol, List<List<Integer>> ans) {
        if (sol.size() == k) {
            ans.add(new ArrayList<>(sol));
            return;
        }

        //x가 k - sol.size()보다 큰 경우, x를 포함하지 않고 다음 재귀 호출을 진행.
        if (x > k - sol.size()) {
            backtrackAlgomapIo(x - 1, k, sol, ans);
        }
        //x를 sol 리스트에 추가하고, x - 1을 다음 원소로 고려하여 재귀 호출을 진행.
        sol.add(x);
        backtrackAlgomapIo(x - 1, k, sol, ans);
        //재귀 호출이 끝나면, sol 리스트에서 x를 제거하여 다음 경우를 고려.
        sol.remove(sol.size() - 1);
    }
}
