package AlgoMap_io.HashmapsAndSets;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
/*
Given an array of integers nums and an integer target,
return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution,
and you may not use the same element twice.

You can return the answer in any order.

 Follow-up: Can you come up with an algorithm that is less than O(n^2) time complexity?
 */
public class Leetcode1 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
        Map<Integer,Integer> num = new HashMap<>();
        num.put(3,1); num.put(3,2);
        System.out.println(num.get(3));
    }
    //4ms 버전
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> num = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            num.put(nums[i],i);
        }
        //정답은 한 조합밖에 없는데, 중복된 숫자가 들어왔고,
        //그 두 숫자의 조합이 답이라면, 어차피 map은 key가 같은 곳에 put 연산을 하면,
        //새로운 값으로 덮어씌워지기 때문에, 0번째 인덱스부터 탐색한다면 문제가 생기지 않는다.
        //nums=[3,3] target=6
        for(int i=0;i<nums.length;i++){
            if(num.containsKey(target-nums[i])&&num.get(target-nums[i])!=i){
                return new int[] {i,num.get(target-nums[i])};
            }
        }
        return null;
    }
    //2ms 버전
    public int[] twoSum2ms(int[] nums, int target) {
        HashMap<Integer,Integer> m=new LinkedHashMap<>();
        int[] res=new int[2];
        int i,num;
        for(i=0;i<nums.length;i++){
            //for문 2개를 사용하지 않고 탐색할 때 미리미리 검사.
            if(m.containsKey(target-nums[i])){
                num=m.get(target-nums[i]);
                res[0]=num;
                res[1]=i;
                return res;
            }
            //없으면 map에 put
            m.put(nums[i],i);
        }
        return res;
    }
}
