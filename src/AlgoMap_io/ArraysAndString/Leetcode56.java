package AlgoMap_io.ArraysAndString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
and return an array of the non-overlapping intervals that cover all the intervals in the input
 */
public class Leetcode56 {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})));
    }
    public static int[][] merge(int[][] intervals){
        if(intervals.length==0) return new int[0][0];
        //왜인지 모르겠지만, 정렬이 안되어있는 배열도 주어지니까, 정렬을 해버리자.
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        //List안에 클래스만 담을 수 있는 줄 알았는데 배열도 가능하다...
        List<int[]> merged = new ArrayList<int[]>();
        for(int[] interval : intervals){
            //겹치는 구간이 발생하려면 다음 구간의 시작 구간이 이전 구간의 최댓값 안에 있어야 하니까.
            //그렇지 않을 경우에 새 구간을 만든다.
            if(merged.isEmpty()||merged.get(merged.size()-1)[1]<interval[0]){
                merged.add(interval);
            }
            else{
                merged.get(merged.size()-1)[1]=Math.max(merged.get(merged.size()-1)[1],interval[1]);
            }
        }
        //List<int[]>를 int[][]로 바꾸는 방법. 이걸 알게 된 것이 더 중요하다.
        return merged.toArray(new int[merged.size()][]);
        //List<Integer>는 int[]로 바로 바꾸려면 stream을 사용해야 한다.
        //int[] array = list.stream().mapToInt(Integer::intValue).toArray();
        //Integer[] array2 = list.toArray(new Integer[크기]);
    }
//    public static void main(String[] args) {
//        System.out.println(Arrays.deepToString(merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})));
//    }
//    public static int[][] merge(int[][] intervals) {
//        boolean[] visited = new boolean[10001];
//        int max=0;
//        for(int i=0;i<intervals.length;i++){
//            for(int j=intervals[i][0];j<=intervals[i][1];j++){
//                if(!visited[j]){
//                    visited[j]=true;
//                    max=j;
//                }
//            }
//        }
//        int[][] ans = new int[intervals.length][2];
//        int count=0;
//        for(int i=0;i<=max;i++){
//            while(!visited[i]){
//                i++;
//            }
//            int start=i;
//            while(visited[i]){
//                i++;
//            }
//            int end=i-1;
//            ans[count]=new int[]{start,end};
//            count++;
//        }
//        return Arrays.copyOfRange(ans,0,count);
//    }
    // 문제를 잘못 이해했다.
}
