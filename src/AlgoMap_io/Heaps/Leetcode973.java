package AlgoMap_io.Heaps;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Leetcode973 {
    public static void main(String[] args) {

    }
    public static int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)-> (int) ((Math.pow(a[0],2)+Math.pow(a[1],2))-(Math.pow(b[0],2)+Math.pow(b[1],2))));
        for(int[] point : points) {
            pq.add(point);
        }
        int[][] res = new int[k][2];
        for(int i=0;i<k;i++) {
            res[i] = pq.poll();
        }
        return res;
    }
}
