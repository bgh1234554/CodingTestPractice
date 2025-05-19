package AlgoMap_io.BitManipulation;

public class Leetcode191 {
    public static void main(String[] args) {

    }
    public int hammingWeight(int n){
        int ans = 0;
        while (n != 0) {
            ans++;
            n &= (n - 1);
        }
        return ans;
    }
}
/*
매번 n &= (n-1)을 하면 1비트 하나가 제거됨

그래서 이 연산을 반복할 수 있는 횟수 = 1의 개수

n &= (n - 1)은 비트에서 가장 빠르게 1의 개수를 세는 트릭 중 하나.

대부분의 bit count, bitmask 문제에서 응용됨.
 */
