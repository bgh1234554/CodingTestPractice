package AlgoMap_io.ArraysAndString;
/*
Given an array nums with n objects colored red, white, or blue,
sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function.
 */
public class Leetcode75 {
    public void sortColors(int[] nums) {
        int[] count = new int[3];
        for(int num : nums){
            count[num]++;
        }
        int i=0;
        for(int j=0;j<3;j++){
            while(count[j]>0){
                nums[i]=j;
                i++;
                count[j]--;
            }
        }
    }
}
/*
전형적인 계수정렬을 이용하는 문제. 값의 범위가 매우 좁고, 배열의 크기도 짧기 때문에
그냥 계수정렬을 써서 출력하면 매우 간단하다.
왠지 모르겠지만 자동저장에 실패해서 1월 25일에 Leetcode에 풀었는데 지금 풀이를 적는다.
 */
