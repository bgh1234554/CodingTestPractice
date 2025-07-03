package AlgoMap_io.DynamicProgramming;
/*
You are given an integer array nums.

You are initially positioned at the array's first index,
and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.
 */
public class Leetcode55 {
    //1. DP를 사용한 방법 (매우 느림, 164ms)
    //메모이제이션 활용 - HashMap을 활용해 각 인덱스별로 도달이 가능한지 일일이 확인한다.
    //algomap.io의 버전은 왠지 모르겟지만 시간초과가 발생하고, chatGPT의 버전으로 풀어보았다.
    public boolean canJump(int[] nums) {
        int n = nums.length;
        //visited[]와 memo[]라는 두 boolean[]배열을 사용할 수 있지만,
        //Boolean[]을 사용하면, 기본이 null로 초기화되기 때문에 visited의 효과도 함께 사용할 수 있다.
        Boolean[] memo = new Boolean[n];
        memo[n-1]=true; //마지막 칸이 목적지니까...
        return canReach(0,nums,memo);
    }

    private boolean canReach(int i, int[] nums, Boolean[] memo) {
        if(memo[i]!=null) return memo[i];

        //현재 위치에서 갈 수 있는 최대 인덱스 (IndexOutOfRange방지)
        int maxJump = Math.min(i+nums[i],nums.length-1);

        //도달 가능성인 높은 멀리 있는 칸부터 탐색
        for(int nxt=maxJump;nxt>i;nxt--){
            //도달 가능하다면 메모 배열에 true로 업데이트 후 true 리턴
            if(canReach(nxt,nums,memo)){
                memo[i]=true;
                return true;
            }
        }
        //모든 시도에도 실패했으면 false 리턴
        memo[i]=false;
        return false;
    }

    //2. 그리디 방법
    //오히려 도착점에서 시작점으로 도달하는 것이 가능한지 테스트한다.
    public boolean canJumpGreedy(int[] nums) {
        int n = nums.length;
        int target = n - 1; //목적지를 target으로 설정
        for (int i = n - 2; i >= 0; i--) {
            //배열을 역으로 탐색하면서 도달 가능하다면
            //target을 해당 인덱스로 업데이트
            if (i + nums[i] >= target) {
                target = i;
            }
        }
        //target이 0이라면 시작 인덱스에서 도착점까지 도달이 가능하다는 뜻이 된다.
        return target == 0;
    }
}
