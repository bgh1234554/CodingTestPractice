package AlgoMap_io.HashmapsAndSets;

import java.util.*;

public class Leetcode128 {
    public static void main(String[] args) {

    }
    //빈도를 체크할 필요가 없기 때문에 Map 대신 Set으로 풀어도 된다.
//    public int longestConsecutiveSequence(int[] nums){
//        int ans=0;
//        Map<Integer,Integer> map = new HashMap<>();
//        for(int num:nums){
//            map.put(num,map.getOrDefault(num,0)+1);
//        }
//
//        return ans;
//    }
    //38ms
    public static int longestConsecutiveSequence(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num:nums){
            set.add(num);
        }
        int ans = 0;
        for(int num:set){
            //num을 시작으로 해야하니까
            if(!set.contains(num-1)){
                int length=1;
                int next=num+1;
                while(set.contains(next)){
                    length++;
                    next++;
                }
                ans = Math.max(ans,length);
            }
        }
        return ans;
    }
    //Set 없이 배열만 이용하는 방법
    public int longestConsecutive16ms(int[] nums) {
        int n=nums.length;
        if(n==0)return 0;
        //정렬 후 차근차근 비교
        Arrays.sort(nums);
        //그전 LCS 마지막 원소
        int lastSmall=Integer.MIN_VALUE;
        //현재 LCS 길이
        int count=0;
        //최대 길이
        int longest=1;
        for(int i=0;i<n;i++){
            //이전 수열에서 연장해야 하는 경우
            if(nums[i]-1==lastSmall){
                count+=1;
                lastSmall=nums[i];
            }
            //새롭게 시작해야 하는 경우
            else if(nums[i]!=lastSmall){
                count=1;
                lastSmall=nums[i];
            }
            //계속 갱신
            longest=Math.max(longest,count);
        }
        return longest;
    }
}
