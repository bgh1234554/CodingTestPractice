package AlgoMap_io.BinarySearch;
/*
Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
For example, the array nums = [0,1,2,4,5,6,7] might become:

    [4,5,6,7,0,1,2] if it was rotated 4 times.
    [0,1,2,4,5,6,7] if it was rotated 7 times.

Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time
results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums of unique elements, return the minimum element of this array.

You must write an algorithm that runs in O(log n) time.
 */
public class Leetcode153 {
    public static void main(String[] args) {

    }
    public static int findMin(int[] nums){
        int l = 0;
        int r = nums.length - 1;

        while (l < r) {
            //오버플로우 방지
            int m = l + (r - l) / 2;

            if (nums[m] > nums[0]) {
                l = m + 1;
            } else {
                r = m;
            }
        }

        return nums[l];
    }
}
/*
풀이보다는 사실 왜 이런 풀이가 나오게 되었는지 유도과정이 더 중요하다.
어쨌든 한번 정렬이 된 상태에서 꼬아놓은 것이기 때문에 변형된 이진탐색을 사용할 수 있다.
[arr[k], arr[k+1], ..., arr[n-1], arr[0], arr[1], ..., arr[k-1]]에서
arr[0] 기준 왼쪽은 arr[0] 기준 오른쪽보다 무조건 크다.

l = 0, r = nums.length-1이라고 잡고 시작하자.
mid와 arr[r]을 비교했을 때, mid가 더 크면 mid가 arr[0]보다 왼쪽에 있다는 것이니까,
우리는 arr[0]을 찾아야 하니까 l=mid+1으로 업데이트.
반대로 mid가 arr[r]보다 작거나 같으면, arr[0] 그 자체이거나, arr[0]보다 오른쪽에 있다는 것.
따라서 mid = r (r-1이 아니라)로 업데이트...

이후 계속 반복하다보면 l의 인덱스가 arr[0]의 인덱스가 된다.

사실 이 문제에서는 몇번 rotate 되었나는 중요하지 않다.
 */
