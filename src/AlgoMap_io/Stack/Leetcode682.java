package AlgoMap_io.Stack;

import java.util.Stack;
/*
You are keeping the scores for a baseball game with strange rules.
At the beginning of the game, you start with an empty record.

You are given a list of strings operations,
where operations[i] is the ith operation you must apply to the record and is one of the following:

    An integer x.
        Record a new score of x.
    '+'.
        Record a new score that is the sum of the previous two scores.
    'D'.
        Record a new score that is the double of the previous score.
    'C'.
        Invalidate the previous score, removing it from the record.

Return the sum of all the scores on the record after applying all the operations.

The test cases are generated such that the answer
and all intermediate calculations fit in a 32-bit integer and that all operations are valid.
 */
public class Leetcode682 {
    public static void main(String[] args) {
        System.out.println(calPoints(new String[]{"5","-2","4","C","D","9","+","+"}));
    }
    public static int calPoints(String[] operations) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (String operation : operations) {
            switch (operation) {
                case "+":
                    int x = stack.pop();
                    int y = stack.pop();
                    stack.push(y);
                    stack.push(x);
                    stack.push(x+y);
                    break;
                case "D":
                    stack.push(stack.peek()*2);
                    break;
                case "C":
                    stack.pop();
                    break;
                default:
                    stack.push(Integer.parseInt(operation));
                    break;
            }
        }
        while(!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }
}
