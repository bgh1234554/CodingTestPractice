package AlgoMap_io.Stack;

import java.util.Stack;

public class Leetcode84 {
    public static void main(String[] args) {
        System.out.println(largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
    }
    public static int largestRectangleArea(int[] heights) {
        Stack<Node> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            //새로운 직사각형 후보군
            int height = heights[i];
            int start = i;
            //기존에 있는 직사각형 후보군이 오른쪽으로 늘어날 수 있는지
            //찾아야 하는데, 더 크면 늘어날 수 없으니 pop한다.
            //하지만, 더 크다는 것은 이번에 들어갈 노드가 왼쪽으로 늘어날 수
            //있다는 것이기 때문에 얼마나 오른쪽으로 늘어날 수 있는지를 저장한다.
            while(!stack.isEmpty() && height<stack.peek().height){
                Node pop = stack.pop();
                //더 이상 연장할 수 없으니, 최종적으로 확정된 직사각형의 넓이를
                //현재 최댓값과 비교한다.
                max = Math.max(max, pop.height*(i-pop.start));
                //합칠수 있다는 거니까, 직사각형의 시작 점 정보 업데이트
                //자동으로 제일 이전의 start 값으로 업데이트 된다. (Stack이니까)
                start = pop.start;
            }
            //직사각형 후보 push
            stack.push(new Node(start,height));
        }
        //맨 끝까지 확장할 수 있는 후보군 중 최댓값 비교
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            max = Math.max(max,pop.height*(heights.length-pop.start));
        }
        return max;
    }
    //직사각형의 왼쪽 아래 점 (시작 인덱스)와 높이를 나타내는 클래스
    static class Node{
        public int start;
        public int height;
        public Node(int start, int height) {
            this.start = start;
            this.height = height;
        }
    }
    //Node 클래스 없이 배열만으로 푼 버전
    public int largestRectangleAreaAlgomapIo(int[] heights) {
        int n = heights.length;
        Stack<int[]> stk = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            int height = heights[i];
            int start = i;
            while (!stk.isEmpty() && height < stk.peek()[0]) {
                int[] popped = stk.pop();
                int h = popped[0];
                int j = popped[1];
                int w = i - j;
                maxArea = Math.max(maxArea, h * w);
                start = j;
            }
            stk.push(new int[]{height, start});
        }

        while (!stk.isEmpty()) {
            int[] popped = stk.pop();
            int h = popped[0];
            int j = popped[1];
            int w = n - j;
            maxArea = Math.max(maxArea, h * w);
        }

        return maxArea;
    }
}
