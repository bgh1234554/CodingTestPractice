package AlgoMap_io.BinarySearch;
/*
Given a positive integer num,
return true if num is a perfect square or false otherwise.

A perfect square is an integer that is the square of an integer.
In other words, it is the product of some integer with itself.

You must not use any built-in library function, such as sqrt.
 */
public class Leetcode367 {
    public static void main(String[] args) {
        System.out.println(isPerfectSquare(808201));
    }
    public static boolean isPerfectSquare(int num) {
        int min = 0; int max = num;
        while (min <= max) {
            int mid = min + (max - min) / 2;
            if(mid*mid == num) {
                return true;
            }
            //오버플로우 방지를 위한 이항
            else if(mid < num/mid) {
                min = mid;
                if(max-min==1) return false;
            }
            else {
                max = mid;
                if(max-min==1) return false;
            }
        }
        return false;
    }
}
