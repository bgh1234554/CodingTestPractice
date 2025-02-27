package AlgoMap_io.HashmapsAndSets;

import java.util.*;

/*
Determine if a 9 x 9 Sudoku board is valid.
Only the filled cells need to be validated according to the following rules:

1. Each row must contain the digits 1-9 without repetition.
2. Each column must contain the digits 1-9 without repetition.
3. Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.

Note:
    A Sudoku board (partially filled) could be valid but is not necessarily solvable.
    Only the filled cells need to be validated according to the mentioned rules.
 */
public class Leetcode36 {
    public static void main(String[] args) {

    }
    //한칸을 중심으로 푸는 알고리즘 -> 같은 3*3 그리드에 같은 숫자가 있는지 검증하기 힘듬
    //매번 새 칸이 들어올 때마다 검사해야함.
    //너무 Map을 이용한 풀이만 봐서 Map 위주로 생각했던 것 같다.
//    public static boolean isValidSudoku(char[][] board) {
//        boolean isValid = true;
//        //숫자별 위치
//        Map<Character,ArrayList<int[]>> map = new HashMap<>();
//        for(int i=0;i<board.length;i++){
//            for(int j=0;j<board[i].length;j++){
//                if(board[i][j]=='.') continue;
//                if(map.get(board[i][j])==null){
//                    map.put(board[i][j],new ArrayList<>());
//                    map.get(board[i][j]).add(new int[]{i,j});
//                }
//                else{
//                    for(int[] position:map.get(board[i][j])){
//                        if(position[0]==i||position[1]==j){
//                            isValid=false; break;
//                        }
//                        else if(isInSameGrid()){
//                            isValid=false; break;
//                        }
//                        else{
//                            map.get(board[i][j]).add(new int[]{i,j});
//                        }
//                    }
//                }
//            }
//        }
//        return isValid;
//    }
    //HashMap으로 풀이하는 algomap.io의 방법
    //이제까지 Map으로 풀었지만, 오랜만에 Set을 사용한다.
    public boolean isValidSudoku(char[][] board) {
        // Validate Rows
        for (int i = 0; i < 9; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                char item = board[i][j];
                if (item != '.' && !set.add(item)) {
                    return false;
                }
            }
        }

        // Validate Columns
        for (int i = 0; i < 9; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                char item = board[j][i];
                if (item != '.' && !set.add(item)) {
                    return false;
                }
            }
        }

        // Validate 3x3 Sub-grids
        // 3x3 그리드의 시작점을 starts 배열에 저장한다.
        int[][] starts = {{0, 0}, {0, 3}, {0, 6},
                {3, 0}, {3, 3}, {3, 6},
                {6, 0}, {6, 3}, {6, 6}};

        for (int[] start : starts) {
            Set<Character> set = new HashSet<>();
            //3*3칸안에 있는 숫자를 Set에 집어넣는데, add에 실패했다는 것은
            //중복이 존재한다는 것이므로 false를 return한다.
            for (int row = start[0]; row < start[0] + 3; row++) {
                for (int col = start[1]; col < start[1] + 3; col++) {
                    char item = board[row][col];
                    if (item != '.' && !set.add(item)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //Map 없이 풀이하는 방법
    public static boolean isValidSudoku1s(char[][] board){
        for (int i = 0; i < 9; i++) {
            if (!isValid(board, i, 0, 1, 9)) {
                return false;
            }
            if (!isValid(board, 0, i, 9, 1)) {
                return false;
            }
        }
        //위의 starts 배열을 for문으로 바꾼 형태
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                if (!isValid(board, i, j, 3, 3)) {
                    return false;
                }
            }
        }
        return true;
    }
    //주어진 행렬의 주어진 (row,col) 시작 인덱스에서 오른쪽으로 rows, 아래쪽으로 cols만큼의 그리드를 만들어
    //중복이 있는지 검사하는 함수
    private static boolean isValid(char[][] board, int row, int col, int rows, int cols) {
        boolean[] seen = new boolean[10];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char c = board[row + i][col + j];
                if (c == '.') {
                    continue;
                }
                if (seen[c - '0']) {
                    return false;
                } else {
                    seen[c - '0'] = true;
                }
            }
        }
        return true;
    }
}
