package AlgoMap_io.Stack;

import java.util.Stack;
/*
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

    Open brackets must be closed by the same type of brackets.
    Open brackets must be closed in the correct order.
    Every close bracket has a corresponding open bracket of the same type.
 */
public class Leetcode20 {
    public static void main(String[] args) {
        System.out.println(isValid("()"));
    }
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(' || c == '{' || c == '['){
                stack.push(c);
            }
            else if(c == ')' || c == '}' || c == ']'){
                if(stack.isEmpty()){
                    return false;
                }
                else if(stack.peek()=='('&&c==')'){
                    stack.pop();
                }
                else if(stack.peek()=='{'&&c=='}'){
                    stack.pop();
                }
                else if(stack.peek()=='['&&c==']'){
                    stack.pop();
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        return stack.isEmpty();
    }
}
