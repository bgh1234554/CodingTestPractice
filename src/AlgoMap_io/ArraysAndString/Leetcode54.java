package AlgoMap_io.ArraysAndString;

import java.util.ArrayList;
import java.util.List;

public class Leetcode54 {
    public static void main(String[] args) {
        int[][] matrix = {
                {3},
                {2}
        };
        System.out.println(spiralOrder(matrix));
    }
    public static List<Integer> spiralOrder(int[][] matrix) {
        int row=0; int col=0;
        int n=matrix.length; int m=matrix[0].length;
        List<Integer> ans=new ArrayList<>();
        while(ans.size()<m*n){
            for(int i=col;i<=m-1-col;i++){
                ans.add(matrix[row][i]);
            }
            for(int i=row+1;i<=n-2-row;i++){
                ans.add(matrix[i][m-1-col]);
            }
            for(int i=m-1-col;i>=col;i--){
                ans.add(matrix[n-1-row][i]);
            }
            for(int i=n-2-row;i>=row+1;i--){
                ans.add(matrix[i][col]);
            }
            row++; col++;
        }
        return ans.subList(0,m*n);
    }
}
