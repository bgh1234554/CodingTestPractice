package AlgoMap_io.BinarySearch;
/*
You are given an m x n integer matrix matrix with the following two properties:

    Each row is sorted in non-decreasing order.
    The first integer of each row is greater than
    the last integer of the previous row.

Given an integer target,
return true if target is in matrix or false otherwise.

You must write a solution in O(log(m * n)) time complexity.
 */
public class Leetcode74 {
    public static void main(String[] args) {
        System.out.println(searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}},3));
    }
    public static boolean searchMatrix(int[][] matrix, int target) {
        int row;
        if(target<matrix[0][0]||target>matrix[matrix.length-1][matrix[0].length-1]) return false;
        int top = 0; int bottom = matrix.length-1;
        while(top<=bottom){
            int mid = top + (bottom-top)/2;
            if(matrix[mid][0]==target){
                return true;
            }
            else if(matrix[mid][0]>target){
                bottom = mid-1;
            }
            else if(matrix[mid][0]<target){
                top = mid+1;
            }
        }
        //찾아봐야 하는 row 선택
        //row=top하면 삽입해야 하는 인덱스를 찾는거니까.
        //우리는 삽입할 곳이 아니라 원래 있는 곳을 찾아야 하므로 -1을 해줘야 한다.
        row = top-1;
        int left = 0; int right = matrix[row].length-1;
        while(left<=right){
            int mid = left + (right-left)/2;
            if(matrix[row][mid]==target){
                return true;
            }
            else if(matrix[row][mid]>target){
                right = mid-1;
            }
            else{
                left = mid+1;
            }
        }
        return false;
    }
}
