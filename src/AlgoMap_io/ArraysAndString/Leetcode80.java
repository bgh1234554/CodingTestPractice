package AlgoMap_io.ArraysAndString;
/*
Given an integer array nums sorted in non-decreasing order,
remove some duplicates in-place such that each unique element appears at most twice.
The relative order of the elements should be kept the same.

Since it is impossible to change the length of the array in some languages,
you must instead have the result be placed in the first part of the array nums. More formally,
if there are k elements after removing the duplicates,
then the first k elements of nums should hold the final result.
It does not matter what you leave beyond the first k elements.

Return k after placing the final result in the first k slots of nums.

Do not allocate extra space for another array.
You must do this by modifying the input array in-place with O(1) extra memory.
 */
public class Leetcode80 {
    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{0,0,1,1,1,1,2,3,3}));
        System.out.println(removeDuplicatesCopilot(new int[]{0,0,1,1,1,1,2,3,3}));
    }
    public static int removeDuplicates(int[] nums) {
        int j=1; //overwrite index
        int count=1; //count the num
        for(int i=1;i<nums.length;i++){ //i for scan
            if(nums[i]==nums[i-1]){
                count++;
            }
            else{
                count=1;
            }
            if(count<=2){
                nums[j]=nums[i];
                j++; //중복이 안됐으니까 그냥 overwrite해도 괜찮다.
            }
        }
        return j;
    }
    /*
    j는 덮어씌울 인덱스, count는 중복도를 확인하는 값.
    for문으로 index 1부터 순차적으로 탐색하는데, 이전 값과 동일하면 count++, 아니면 1로 리셋한다.
    만약 3번 이상 반복되는 index가 나왔을 경우에는 j는 그 자리에 덮어써야하기 때문에 가만히 있고,
    i가 이동해서 count가 1이되는 인덱스를 찾는다. 이후 차근차근 새로운 데이터를 덮어씌운다.
    마지막에는 j-1 인덱스까지 데이터를 덮어씌운 것이기 때문에 자연스럽게 j가 return값이 된다.

    중복을 제거하라길래 swap만 생각하고 있었는데, O(n)의 시간복잡도로도 인덱스 하나씩 땡기는 것이 가능하다는 것을
    생각하지 못한 것 같다. _의 값이 무조건 원래 배열에 있던 값을 보내버린 것이 아니라는 생각을 가져야 할 것 같다.
     */
    public static int removeDuplicatesCopilot(int[] nums) {
        if (nums.length <= 2) return nums.length;

        int j = 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[j - 2]) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }
    /*
    변수 j의 의미는 위의 풀이 방식과 동일하다.
    count 변수가 없는 것이 특징인데, 어차피 2개까지 중복이 허용되기 때문에 과감하게 인덱스 2부터 탐색한다.
    j-2번째의 인덱스의 값을 비교해야할 값으로 지정한다. 이후 num[j]=num[i]가 이해가 안될수도 있는데,
    j-1의 값이 무엇이든 (j의 값이든, i의 값이든) 상관없이 최대 중복도가 2가 보장된다. 그렇기 때문에 그냥 덮어 씌우면,
    알아서 데이터가 앞으로 땡겨진다. 그래서 count 변수가 없다.
     */
}
