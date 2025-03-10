package AlgoMap_io.BinarySearch;
/*
There is an integer array nums sorted in ascending order (with distinct values).

Prior to being passed to your function,
nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length)
such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

Given the array nums after the possible rotation and an integer target,
return the index of target if it is in nums, or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity.
 */
public class Leetcode33 {
    public static void main(String[] args) {

    }
    public int search(int[] nums, int target) {
        int l = 0; int r = nums.length - 1;
        while(l<r){
            int mid = l+(r-l)/2;
            if(nums[mid]>nums[r]){
                l = mid+1;
            }
            else{
                r = mid;
            }
        }
        int minIndex = l;
        if(minIndex==0){
            //rotate 안했으니까, 일반 이진탐색
            l=0;
            r=nums.length-1;
        }
        //minIndex 기준 왼쪽만 탐색
        else if(target>=nums[0]&&target<=nums[minIndex-1]){
            l=0; r = minIndex-1;
        }
        //오른쪽만 탐색
        else{
            l = minIndex;
            r = nums.length-1;
        }
        while(l<=r){
            int mid = l+(r-l)/2;
            if(nums[mid]==target){return mid;}
            else if(nums[mid]>target){
                r = mid-1;
            }
            else{
                l=mid+1;
            }
        }
        return -1;
    }
}
/*
O(logn)이니까 이진 탐색을 여러번 써도 된다. 어렵게 생각하지 말자.

이거 변곡점 인덱스를 알아야 뭘 할거 같은데, 그러려면 이진 탐색을 여러번 해야할 것 같은데,
이게 맞나...? 싶었는제 진짜 맞네...

뒷쪽에 진짜 이진탐색을 할 때 인덱스 구하는 로직을 기억하자.
 */