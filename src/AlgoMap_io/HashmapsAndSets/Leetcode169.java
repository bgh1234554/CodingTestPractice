package AlgoMap_io.HashmapsAndSets;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times.
You may assume that the majority element always exists in the array.

Follow-up: Could you solve the problem in linear time and in O(1) space?
 */
public class Leetcode169 {
    public static void main(String[] args) {
        majorityElement(new int[]{3,2,3});
    }

    //Map 사용 안하는, 덜 직관적인 알고리즘. 그러나 공간복잡도가 O(1)이고,
    //실행시간도 2ms밖에 안걸렸다.
    //Boyer-Moore Voting Algorithm이라고 하며, 아마 사전 지식 없이는 구현하기 힘든 알고리즘일 것이다.
    public static int majorityElement(int[] nums){
        int candidate = nums[0];
        int count = 0;
        for (int num : nums) {
            //count가 0이면 후보로 업데이트
            if (count == 0) {
                candidate = num;
            }
            //0이 아닐때는 숫자가 후보와 같으면 count 증가
            //아니면 감소
            count += (candidate == num) ? 1 : -1;
        }
        //마지막까지 탐색후 남아 있는 후보가 최종 선정
        //count를 업데이트하는게, 가장 많이 있는 원소와 나머지 원소를 소거한다는 느낌으로 생각하면
        //그나마 이해가 된다.
        return candidate;
    }

    //Map을 사용해서 푸는 공간복잡도 O(N) 버전
    //165ms -> 매우 느림 처음에는 165라 떴늗네 왜 나중에는 15인거지?
    public static int majorityElementWitHHashMap(int[] nums){
        Map<Integer,Integer> map = new HashMap<>();
        for(int num:nums){
            if(!map.containsKey(num)){
                map.put(num,1);
            }
            else{
                map.put(num,map.get(num)+1);
            }
        }
        int max=0; int ans=0;
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            if(entry.getValue()>max){
                max=entry.getValue();
                ans=entry.getKey();
            }
        }
        return ans;
    }
    //26ms 버전 -> Map 사용 (나중에는 19ms라고 뜬다. 이상하다.)
    public static int majorityElement26ms(int[] nums){
        Map<Integer,Integer> map = new HashMap<>();
        int max=0; int ans=0;
        for(int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
            if(map.get(num)>max){
                max=map.get(num);
                ans=num;
            }
        }
        return ans;
    }
    //4ms 버전 -> 굉장히 똑독한 버전
    public int majorityElementBySort(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
        //최소 반 이상은 있는 원소를 찾으니까, 절반에 해당하는 원소는 항상 최빈값이 차지한다.
    }
    //음수 원소가 들어갈 수 있어서 일반적인 계수 방법으로는 안된다.
//    public static int majorityElementONSpaceFail(int[] nums){
//        Arrays.sort(nums);
//        int N = nums[nums.length-1];
//        int[] counts = new int[N+1];
//        for(int num:nums){
//            counts[num]++;
//        }
//        int max = 0; int ans=0;
//        for(int i=0;i<counts.length;i++){
//            if(max<counts[i]){
//                max = counts[i];
//                ans = i;
//            }
//        }
//        return ans;
//    }
}
/*
The Dictionary<int,int> (hashmap) <number,frequency> was the trivial solution,
however the second solution fulfills the problem's follow-up restriction:
"Could you solve the problem in linear time and in O(1) space?".

Although 8 is difficult,
I don't see how one could be expected to invent the Boyer–Moore majority vote algorithm
in an interview without already having studied it.
 */