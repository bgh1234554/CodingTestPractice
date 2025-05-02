package AlgoMap_io.RecursiveBacktracking;

import java.util.ArrayList;
import java.util.List;
/*
Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells,
where adjacent cells are horizontally or vertically neighboring.

The same letter cell may not be used more than once.
 */
public class Leetcode79 {
    public static void main(String[] args) {
        System.out.println(exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}},"ABCCED"));
        System.out.println(exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}},"SEE"));
        System.out.println(exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}},"ABCB"));
        System.out.println(exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}},"AAB"));
        System.out.println(exist(new char[][]{{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}},"ABCESEEEFS"));
    }
    static boolean[][] visited;
    static boolean found;
    static int[][] movement = {{1,0},{-1,0},{0,1},{0,-1}};
    static int N; static int M;
    public static boolean exist(char[][] board, String word) {
        found = false; //case 3
        M = board.length; N = board[0].length;
        visited = new boolean[M+1][N+1];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(found) break;
                if(board[i][j] == word.charAt(0)){
                    visited = new boolean[M+1][N+1]; //case 4
                    dfsBoard(i,j, board, word, 1);
                }
            }
        }
        return found;
    }

    private static void dfsBoard(int i, int j, char[][] board, String word, int count) {
        if(count>word.length()) return;
        if(count==word.length()|found){
            found = true;
            return;
        }
        visited[i][j] = true;
        for(int k = 0; k < movement.length; k++){
            int dx = i + movement[k][0];
            int dy = j + movement[k][1];
            if (isSafe(dx, dy) && !visited[dx][dy] && board[dx][dy]==word.charAt(count)){
                dfsBoard(dx, dy, board, word, count + 1);
            }
        }
        visited[i][j] = false; //case 5! backtracking
    }
    public static boolean isSafe(int x, int y){
        return x>=0&&x<M&&y>=0&&y<N;
    }

}
/*
5번에서 저 줄을 넣어야 하는 이유 - chatGPT
(0,0) A
(0,1) B
(0,2) C
(0,3) E
(1,3) S
(1,2) E
(2,2) E
(2,3) E
(1,1) F
(1,0) S ✅

1. 첫 번째 경로 탐색 (예: (0,0) → (0,1) → ... → (1,0))

    (1,0)까지 잘 도달했어

    그런데 이 경로로는 실패했어 (중간 어딘가에서 글자 안 맞음)

2. 그러고 나서 다른 경로에서 다시 (1,0)을 쓰려고 하면?

    😨 visited[i][j] = true 로 되어 있기 때문에,
    DFS 탐색이 "어? 이 칸 이미 썼네. 안 감." 하면서 해당 경로를 막아버려!

→ 그러면 진짜 정답이 될 수 있는 경로가 있어도 탐색 자체를 못 하게 되는 거야.

정답 경로는 (1,0)을 마지막에 써야 해.

하지만 그 전에 다른 실패한 경로에서 (1,0)을 썼다가 true로 남겨두면

마지막에 (1,0)을 써야 할 때 "이미 방문했음" 이라면서 막아버려.
 */
