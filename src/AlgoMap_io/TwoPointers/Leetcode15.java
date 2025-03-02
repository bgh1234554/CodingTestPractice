package AlgoMap_io.TwoPointers;

import java.util.*;

/*
Given an integer array nums,
return all the triplets [nums[i], nums[j], nums[k]]
such that i != j, i != k, and j != k,
and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.
 */
public class Leetcode15 {
    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
    public static List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0; i<nums.length-2; i++){
            //최솟값을 fix하고, 나머지로 Two sum 알고리즘 돌리기
            int min = nums[i];
            if(nums[i]>0) break;
            if(i>0&&nums[i]==nums[i-1]) continue;
            int left = i+1; int right = nums.length-1;
            //지정된 최솟값에 대해, 나머지 두 수의 조합+최솟값으로 0을 만들 수 있는지 확인하기
            while(left<right){
                if(nums[left]+nums[right]+min==0){
                    //배열을 List로 변환 => Arrays.asList
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //일단 이동
                    left++; right--;
                    //중복된 값일수도 있으니까 while문으로 완전히 넘겨버리기
                    while(left<right&&nums[left]==nums[left-1]) left++;
                    while(left<right&&nums[right]==nums[right+1]) right--;
                }
                else if(nums[left]+nums[right]+min>0){
                    right--;
                }
                else{
                    left++;
                }
            }
        }
        return res;
    }
    /*
    중복 제거 로직을 생각해내지 못했다. 처음부터 테스트케이스 3개를 모두 통과해서 안일하게 생각했던 것 같다.
    처음에는 while문에서 타겟을 찾으면 바로 break를 했는데, 중복 제거 로직을 구현하기 위해
    break하는 것이 아니라 min을 최솟값으로 하는 모든 경우의 수를 구한다.
    중복 제거를 위해 min이 이전과 같은 값이면 continue;
    while문 안에서 조합을 찾았을 때, 새로운 조합을 찾기 위해 left와 right를 이동할 때,
    while문을 한번 더 사용해서 똑같은 방식으로 값이 같은 포인터를 건너뛴다.
     */
    //HashMap, HashSet 사용 -> 느리지만, 알아두면 좋다
    public static List<List<Integer>> threeSumWithHashMap(int[] nums){
        //자바에서는 배열의 값으로 인덱스를 알 수 없으니 HashMap으로 결과값과 인덱스를 매핑
        Map<Integer,Integer> indexMap = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            indexMap.put(nums[i],i);
        }
        //중복된 triplet을 추가하지 않도록 애초에 Set에 저장 후 List로 변환
        Set<List<Integer>> result = new HashSet<>();
        //O(n^2) 시간 복잡도로 두 수의 조합을 찾은 뒤, 남은 한 수가 존재하는지 파악하고
        //존재하는 경우 Map에서 찾아 인덱스 3개를 List에 추가
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                int target = -(nums[i]+nums[j]);
                if(indexMap.containsKey(target)&&indexMap.get(target)!=i&&indexMap.get(target)!=j){
                    List<Integer> triplet = Arrays.asList(nums[i],nums[j],target);
                    //리스트를 정렬할땐 Arrays가 아니라 Collections.sort를 사용
                    Collections.sort(triplet);
                    result.add(triplet);
                }
            }
        }
        //HashSet을 ArrayList로 변환
        return new ArrayList<>(result);
    }
}
