package AlgoMap_io.RecursiveBacktracking;

import java.util.ArrayList;
import java.util.List;

/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 */
public class Leetcode22 {
    static List<String> res = new ArrayList<>();
    //리트코드에서는 백트래킹 시 같은 솔루션 클래스를 재활용하기 때문에 제대로 초기화되지 않는 경우가 발생한다.
    //이때는 함수 안에 res를 넣어서 backtrack에 res를 인수로 전달하는 방식을 사용하면 도움이 된다.
    public static void main(String[] args) {
        System.out.println(generateParenthesis(2));
    }
    //내가 푼 방법 - 실행시간 2ms
    public static List<String> generateParenthesis(int n) {
        if(n==1){
            res.add("()");
            return res;
        }
        StringBuilder sb = new StringBuilder();
        backtrack("(",n,1,0);
        return res;
    }
    public static void backtrack(String s, int n, int open,int close){
        if(s.length()==2*n&&open==close){
            res.add(s);
            return;
        }
        if(open==n){
            s=s+")";
            backtrack(s,n, open, close+1);
        }
        else if(close==n){
            s=s+"(";
            backtrack(s,n, open+1, close);
        }
        else if(open<n){
            s=s+"(";
            backtrack(s,n, open+1, close);
            s=s.substring(0,s.length()-1);
            if(open>close){
                s=s+")";
                backtrack(s,n, open, close+1);
            }
        }
        else{
            s=s+")";
            backtrack(s,n, open, close+1);
        }
    }

    //Algomap.io의 해답 - 실행시간 1ms
    public List<String> generateParenthesisAlgomapIo(int n) {
        List<String> res = new ArrayList<>();

        dfs(0, 0, "", n, res);

        return res;
    }

    private void dfs(int openP, int closeP, String s, int n, List<String> res) {
        if (openP == closeP && openP + closeP == n * 2) {
            res.add(s);
            return;
        }

        //생각해보니 조건문을 이렇게 단순화 시킬 수 있구나...
        // "("를 추가할 수 있는 조건 + ")"를 추가할 수 있는 조건
        if (openP < n) {
            dfs(openP + 1, closeP, s + "(", n, res);
        }

        if (closeP < openP) {
            dfs(openP, closeP + 1, s + ")", n, res);
        }
    }
}
