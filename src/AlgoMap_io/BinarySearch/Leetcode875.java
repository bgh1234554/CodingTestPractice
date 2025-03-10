package AlgoMap_io.BinarySearch;

import java.util.Arrays;

/*
Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas.
The guards have gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k.
Each hour, she chooses some pile of bananas and eats k bananas from that pile.
If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.
 */
public class Leetcode875 {
    public static void main(String[] args) {
        System.out.println(minEatingSpeed2(new int[]{30,11,23,4,20},5));
    }
    //내가 (처음에 바로) 쓴 풀이 - 28ms, Math.ceil을 대체 시 12ms
    //maxK를 잘 설정하는 방법을 몰라서 그냥 다 더했는데, 생각해보니 그냥 최대 원소만 리턴하면 되네?
    public static int minEatingSpeed(int[] piles, int h) {
        long sum = 0;
        for(int pile : piles){
            sum+=pile;
        }
        long min = Long.MAX_VALUE;
        long minK = 1; long maxK = sum;
        while(minK < maxK){
            long mid = minK + (maxK - minK)/2;
            int takes=0;
            for(int pile: piles){
                //Math.ceil 때문에 시간이 많이 걸리는 것 같다.
                //Math.ceil은 (pile+mid-1)/mid와 동일하기 때문에, 이를 사용하면 시간이 많이 절약된다.
                int take = (int) Math.ceil((double) pile/mid);
                takes+=take;
            }
            if(takes>h){
                minK = mid+1;
            }
            else{
                min = Math.min(min,mid);
                maxK = mid;
            }
        }
        return (int)min;
    }
    //최적화 후 - 9ms
    //sum을 안구하니까 long 형이 필요가 없다. 게다가 min을 없애고 minK를 사용하니 시간이 더 줄었다.
    //어차피 minK==maxK일때 루프가 종료되기 때문에 굳이 min 변수가 필요가 없어진다.
    public static int minEatingSpeed2(int[] piles, int h) {
        int minK = 1; int maxK = Arrays.stream(piles).max().orElse(1);
        while(minK < maxK){
            int mid = minK + (maxK - minK)/2;
            int takes=0;
            for(int pile: piles){
                takes+=(pile+mid-1)/mid;
            }
            if(takes>h){
                minK = mid+1;
            }
            else{
                maxK = mid;
            }
        }
        return minK;
    }
    //Algomap.io의 풀이 - 8ms
    //내 9ms 풀이를 함수화했기 때문에 1ms가 줄은 것 같다.
    public int minEatingSpeedAlgomapIo(int[] piles, int h) {
        //left가 minK, right가 maxK인데, 따로 min value를 두지 않고,
        //minK가 계속 업데이트되니까, minK인 left를 리턴하는 것 같다.
        //최댓값도 그냥 배열의 최댓값임.
        int left = 1;
        //max()함수는 OptionalInt를 반환하기 때문에, Optional을 벗겨내기 위해 orElse를 사용해줘야 한다.
        int right = Arrays.stream(piles).max().orElse(1); // maximum pile

        while (left < right) {
            int mid = (left + right) / 2;

            if (canFinish(piles, h, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean canFinish(int[] piles, int h, int k) {
        int hours = 0;
        for (int pile : piles) {
            hours += (pile + k - 1) / k; // Equivalent to ceil(pile / k)
        }
        return hours <= h;
    }
}
/*
최소 단위를 구해야 하는데, for문으로 1부터 배열의 최댓값까지 일일이 돌면서 답을 구하기에는
시간 초과가 나기 때문에, 산술적으로 가능한 최소와 가능한 최대를 구한 다음,
이진 탐색으로 주어진 시간 내에 바나나를 다 먹을 수 있는 최소 단위를 구하면 된다.

신박한 이진탐색 문제고, Math.ceil을 대신에 사용할 수 있는 식도 알아두면 좋을 것 같다.
 */