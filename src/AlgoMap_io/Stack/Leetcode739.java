package AlgoMap_io.Stack;

import java.util.Arrays;
import java.util.Stack;
/*
Given an array of integers temperatures represents the daily temperatures,
return an array answer such that
answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.

If there is no future day for which this is possible, keep answer[i] == 0 instead.
 */
public class Leetcode739 {
    public static void main(String[] args) {
//        System.out.println(Arrays.toString(dailyTemperatures(new int[]{89,62,70,58,47,47,46,76,100,70})));
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{34,80,80,34,34,80,80,80,80,34})));
    }
    //나 혼자 푼 방법. 인덱스를 참조하기 위해 Node 클래스를 생성하였고, 이 때문에 69ms로 느리다.
    public static int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        Stack<Node> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            Node node = new Node(i,temperatures[i]);
            if(stack.isEmpty()){stack.push(node); continue;}
            if(stack.peek().value<node.value&&node.index!=stack.peek().index){
                while(!stack.isEmpty()&&stack.peek().value<node.value){
                    Node top = stack.pop();
                    res[top.index]=node.index-top.index;
                }
                stack.push(node);
            }
            else{
                stack.push(node);
            }
        }
        return res;
    }
    static class Node{
        public int index;
        public int value;
        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
    //Algomap.io의 버전 - 59ms
    public int[] dailyTemperaturesAlgomapIo(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        //Node 대신 배열에 기온과 인덱스를 차례로 저장한다.
        //나머지 로직은 동일
        Stack<int[]> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int temp = temperatures[i];
            while (!stack.isEmpty() && stack.peek()[0] < temp) {
                int[] prev = stack.pop();
                answer[prev[1]] = i - prev[1];
            }
            stack.push(new int[]{temp, i});
        }
        return answer;
    }
    //Dynamic Programming - 참고용
    public int[] dailyTemperaturesDP(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];

        for (int i = n - 2; i >= 0; i--) {
            int j = i + 1;
            while (j < n && temperatures[j] <= temperatures[i]) {
                if (res[j] == 0) {
                    j = n;
                    break;
                }
                j += res[j];
            }

            if (j < n) {
                res[i] = j - i;
            }
        }
        return res;
    }
}
