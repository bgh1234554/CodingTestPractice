package AlgoMap_io.TwoPointers;
/*
You are given an integer array height of length n.
There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.
Return the maximum amount of water a container can store.

Notice that you may not slant the container.
 */
public class Leetcode11 {
    public static void main(String[] args) {

    }
    /*
    어차피 너비 * 높이 인데, 너비는 포인터가 움직일수록 무조건 줄어드니까,
    최대 넓이를 알기 위해서는 높이부분만 비교한다.

    이걸 생각하지 못해서, 넓이가 두개의 변수에 의존하는데, 어떻게 두 개의 변수에 의존하는
    상태에서 투 포인터를 이동시키지 라는 생각을 하였다.
     */
    public static int maxArea(int[] height) {
        int max = 0;
        int left = 0; int right = height.length - 1;
        while (left < right) {
            int width = right - left;
            int h = Math.min(height[left], height[right]);
            int area = h * width;
            max = Math.max(max, area);
            if(height[left]<height[right]){
                left++;
            }
            else{ right--;}
        }
        return max;
    }
}
