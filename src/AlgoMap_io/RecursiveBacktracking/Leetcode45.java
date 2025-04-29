package AlgoMap_io.RecursiveBacktracking;
/*
You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].

Each element nums[i] represents the maximum length of a forward jump from index i.
In other words, if you are at nums[i], you can jump to any nums[i + j] where:

    0 <= j <= nums[i] and
    i + j < n

Return the minimum number of jumps to reach nums[n - 1].
The test cases are generated such that you can reach nums[n - 1].
 */
public class Leetcode45 {
    public static void main(String[] args) {
        System.out.println(jump(new int[]{2,3,1,1,4}));
    }
    //백트래킹 버전 - 시간초과 발생! (그냥 연습용)
    public static int jumpBacktrack(int[] nums){
        //min을 jumpSearch 함수 안에서도 변경할 수 있도록
        //포인터가 자바엔 없으니까 배열로 전달한다.
        int[] min = {nums.length};
        //i=0 (위에서 생각해봤을 떄)
        for(int j = 1; j <=nums[0]; j++){
            jumpSearch(nums, Math.min(j, nums.length - 1),min,1);
        }
        return min[0];
    }
    public static void jumpSearch(int[] nums, int index, int[] min, int count){
        if(index==nums.length-1){
            min[0] = Math.min(min[0],count);
            return;
        }
        //더 이상 갈 수 없다.
        if(nums[index]==0){
            return;
        }
        //여기선 i=index;
        for(int j = 1; j <=nums[index]; j++){
            jumpSearch(nums, Math.min(index + j, nums.length - 1),min,count+1);
        }
    }

    //Optimal Version - BFS by Neetcode
    //하나씩 다 탐색해보는 것이 아니라, 최대 거리만큼만 무조건 간다.
    //왜 BFS???
    //점프 범위(레벨) 안에서 가능한 모든 곳을 먼저 다 본 다음, 레벨을 넘어가니까
    //1차원 이동이라 currentEnd, farthest 변수로 큐 대신 레벨 관리가 가능하다
    public static int jump(int[] nums) {
        int jumps = 0; //BFS의 레벨과 같다고 생각하면 된다.
        int currentEnd = 0; //현재 점프에서 도달할 수 있는 가장 마지막 인덱스
        int farthest = 0; //현재 탐색한 곳 중 가장 멀리 갈 수 있는 인덱스

        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]); // 현재 점에서 가장 멀리 갈 수 있는 곳
            if (i == currentEnd) { // 한 번의 점프 범위를 모두 탐색했으면
                jumps++;           // 점프 수 추가 - 점프를 준비하는 시점에 올라간다.
                currentEnd = farthest; // 다음 점프 범위를 갱신
            }
            //어느 경로로 가는지는 상관없으니까 jumps 수만 세면 됨.
        }

        return jumps;
    }
}
