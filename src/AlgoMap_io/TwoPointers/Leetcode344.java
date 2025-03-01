package AlgoMap_io.TwoPointers;
/*
Write a function that reverses a string.
The input string is given as an array of characters s.

You must do this by modifying the input array in-place with O(1) extra memory.
 */
public class Leetcode344 {
    public static void main(String[] args) {

    }
    //투 포인터 없는 단순 for문 - 1ms
    public static void reverseString(char[] s) {
        for (int i = 0; i < s.length / 2; i++) {
            char tmp = s[i];
            s[i]=s[s.length-1-i];
            s[s.length-1-i]=tmp;
        }
    }

    //투 포인터 사용 - 0ms
    public void reverseString2(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            swap(s, left, right);
            left++;
            right--;
        }
    }
    private void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
    /*
    reverseString2는 두 포인터가 교차할 때까지 반복을 수행하므로,
    불필요한 조건 검사를 최소화합니다.
    reverseString은 for 루프의 조건 검사와 인덱스 계산이 추가로 필요합니다.
     */
}
