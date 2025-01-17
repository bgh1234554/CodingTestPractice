package AlgoMap_io.ArraysAndString;
/*
You are given two integer arrays nums1 and nums2, sorted in non-decreasing order,
and two integers m and n, representing the number of elements in nums1 and nums2 respectively.

Merge nums1 and nums2 into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function, but instead be stored inside the array nums1.
To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged,
and the last n elements are set to 0 and should be ignored. nums2 has a length of n.

Can you do it with time complexity of O(m+n)?
 */
public class Leetcode88 {
    public static void main(String[] args) {
        merge(new int[]{1,2,3,0,0,0},3,new int[]{2,5,6},3);
    }
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int x = m-1, y=n-1, z;
        for(z=m+n-1;z>=0;z--){
            if(x<0){
                nums1[z]=nums2[y];
                y--;
            }
            else if(y<0) break;
            else if(nums1[x]<=nums2[y]){
                nums1[z]=nums2[y];
                y--;
            }
            else{
                nums1[z]=nums1[x];
                x--;
            }
        }
    }
}
/*
투 포인터 예제를 복습하기 좋은 것 같다. 정렬되어 있고 두 원소를 비교해야 한다면, 투 포인터를 사용하는 것이 좋다.

투 포인터를 이용할 때 제일 뒷 인덱스부터 비교하는 예제가 많은 것 같다. 이를 활용하면 좋을 것 같다.

nums1에 데이터를 넣어야 하므로 nums1의 제일 끝에 데이터를 넣을 인덱스를 z,
nums2의 제일 끝부분을 y, nums1의 제일 끝부분을 x라고 하면,

여기서 포인터를 하나씩 옮겨가면서 큰 수를 nums1[z]에 넣는다.

만약, x<0이 되면, (nums1={5,0,0,0,0} nums2={2,3,4,5} 와 같은 경우) num2의 남은 부분을 차례차례 넣으면 된다.
y<0이 되면, 새롭게 nums1 배열에 끼워넣을 원소가 더 이상 없다는 뜻이므로, 정렬이 완료된 것이라 break를 하면 된다.

병합정렬에서 쓰는 merge 함수와 비슷해서 whlie문을 기반으로 만드려다가 자꾸 IndexOutOfBounds가 나와서 실패하였다.
투 포인터 예제를 풀 때 거꾸로 생각하는 습관을 들여야 할 것 같다.
 */
