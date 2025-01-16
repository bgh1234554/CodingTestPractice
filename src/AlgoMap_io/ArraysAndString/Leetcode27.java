package AlgoNap_io.ArraysAndString;
/*
Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.

Consider the number of elements in nums which are not equal to val be k,
to get accepted, you need to do the following things:

Change the array nums such that the first k elements of nums contain the elements which are not equal to val.
The remaining elements of nums are not important as well as the size of nums.

Return k.
 */
public class Leetcode27 {
    public static void main(String[] args) {
        System.out.println(removeElement(new int[]{0,1,2,2,3,0,4,2},2));
    }
    public static int removeElement(int[] nums, int val) {
        int i = 0, n = nums.length;
        while (i < n) {
            if (nums[i] == val) {
                nums[i] = nums[n - 1];
                n--;
            } else {
                i++;
            }
        }
        return n;
    }
}
/*
처음으로 푸는 아이디어를 제대로 얻지 못했다.

양 끝에 포인터를 둔다.
i번째 인덱스와 n-1번째 인덱스가 있다고 할 때,
i번째 인덱스가 val과 같으면 없애버려야 하니까, n-1번째 인덱스로 대체한다.
근데, 대체된것도 val일 수 있기 때문에 i는 놔두고 n값만 1 감소시킨다.
다시 val이면 if문으로 들어가 다시 val이 아닌 값으로 대체를 시도한다.

val이 확실히 아닐 때만 i의 인덱스가 커진다. 그렇게 해서 i와 n-1번째 인덱스가 만나면 검사가 종료된다.
O(n)의 시간복잡도를 가진다. 공간복잡도는 O(1).

끝 인덱스가 2이거나, 첫 인덱스가 2일때 n이 1씩 감소하기 때문에, 남은 n의 값은 val이 아닌 값의 수가 자연스럽게 된다.
 */