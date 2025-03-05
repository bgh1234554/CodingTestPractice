package AlgoMap_io.Stack;

import java.util.Stack;

class MinStack {
    //일반 스택과, 최솟값 저장용 minStack을 따로 만듬.
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        } else {
            minStack.push(minStack.peek());
        }
    }

    public void pop() {
        stack.pop();
        //minStack과 stack의 사이즈가 같기 때문에,
        //stack의 peek가 i 일때의 최댓값이 minStack의 peek이므로,
        //그냥 같이 pop해주면 된다.
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}