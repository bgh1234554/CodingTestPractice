package AlgoMap_io.SlidingWindow;
/*
Given a binary array nums and an integer k,
return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 */
public class Leetcode1004 {
    public static void main(String[] args) {
        System.out.println(longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0},2));
        System.out.println(longestOnes(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1},3));
        System.out.println(longestOnes(new int[]{0,0,1,1,1,0,0},0));
        System.out.println(longestOnes(new int[]{0,0,1,1},1));
    }
    public static int longestOnes(int[] nums, int k) {
        //만들 수 있는 1로 이루어진 수열이 없어 0 하나만 남아있어
        //이 메서드의 핵심 아이디어
        //length를 0으로 만들 때는 start가 i보다 한칸 앞에 있게 만들어야 한다.
        int start=0; int maxLength=0; int available = k;
        if(nums[0]==1||(nums[0]==0&&k!=0)) maxLength=1;
        if(nums[0]==0){
            if(available>0){
                available--;
            }
            else if(available==0){
                start++;
            }
        }
        for(int i=1; i<nums.length; i++){
            if(nums[i]==0){
                if(available==0){
                    //시작지점부터 1인놈 제거
                    while(nums[start]==1){
                        start++;
                    }
                    start++;
                }
                else{
                    available--;
                }
            }
            maxLength=Math.max(maxLength,i-start+1);
        }
        return maxLength;
    }
    //Algomap.io의 더 직관적인 버전 - nums[0]에 대한 복잡한 계산이 없어 더 직관적이다.
    public int longestOnesAlgomapIo(int[] nums, int k) {
        int maxLength = 0;
        int zeroCount = 0;
        //available은 k에서 밑으로 내려가니까, 안햇갈리게 변형된 버전
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeroCount++;
            }
            //0의 개수가 k보다 많으면 안되니까, 빼는 작업 진행.
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            //이후 maxLength 업데이트
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
/*
Sliding Window를 늘렸다가 줄였다가 할 수 있다!!
 */