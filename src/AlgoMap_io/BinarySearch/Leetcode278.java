package AlgoMap_io.BinarySearch;
/*
You are a product manager and currently leading a team to develop a new product.
Unfortunately, the latest version of your product fails the quality check.
Since each version is developed based on the previous version,
all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n]
and you want to find out the first bad one,
which causes all the following ones to be bad.

You are given an API bool isBadVersion(version)
which returns whether version is bad.

Implement a function to find the first bad version.
You should minimize the number of calls to the API.
 */
//오류 방지용 클래스 - 작동 안함
class VersionControl {
    boolean isBadVersion(int version) {return version >=Math.random()*100;}
}

public class Leetcode278 extends VersionControl{
    public static void main(String[] args) {

    }
    public int firstBadVersion(int n) {
        int min = 1; int max = n;
        int badVersion = 1;
        while (min <= max) {
            int mid = (min+max)/2;
            if(isBadVersion(mid)){
                badVersion = mid;
                max = mid-1;
            }
            else min = mid+1;
        }
        return badVersion;
    }
}
/*
Q - 이진 탐색을 할 때, start +(end-start)/2;를 (start+end)/2보다 많이 쓰는건 overflow 때문에 그런거야?

A - 네, 맞습니다. start + (end - start) / 2를 사용하는 이유는 오버플로우를 방지하기 위해서입니다.
    start와 end가 매우 큰 값일 때, start + end가 정수 범위를 초과할 수 있습니다.
    하지만 start + (end - start) / 2는 이러한 오버플로우 문제를 피할 수 있습니다.
 */
