package AlgoMap_io.ArraysAndString;

import java.util.Arrays;

public class Leetcode48 {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{5,1,9,11}, {2,4,8,10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
        //
    }
    public static void rotate(int[][] matrix) {
        int len = matrix.length;
        for(int i=0;i<len;i++){
            for(int j=i+1;j<len;j++){
                if(i!=j){
                    int tmp=matrix[i][j];
                    matrix[i][j]=matrix[j][i];
                    matrix[j][i]=tmp;
                }
            }
        }
        for(int i=0;i<len;i++){
            for(int j=0;j<(len/2);j++){
                int tmp=matrix[i][j];
                matrix[i][j]=matrix[i][len-1-j];
                matrix[i][len-1-j]=tmp;
            }
        }
    }
}
