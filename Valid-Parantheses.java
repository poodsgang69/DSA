class Solution {
    public boolean isValid(String s) {
        Stack<Character> paranStack = new Stack<Character>();
        for ( char c : s.toCharArray()) {
            if (c == '(') paranStack.push(')');
            else if (c == '{') paranStack.push('}');
            else if (c == '[') paranStack.push(']');
            else if (paranStack.isEmpty() || paranStack.pop() != c) return false;
        }
        return paranStack.isEmpty(); 
    }
}

/*
Algorithm Notes — Valid Parentheses

1. Use a stack to track expected closing brackets.
2. For each char:
   - If it's an opening bracket, push its matching closing one.
   - If it's a closing bracket:
       • Return false if stack is empty (no match).
       • Pop and compare — if not equal, invalid sequence.
3. After loop, stack must be empty for the string to be valid.
Refer to this link to the video explaining this: https://leetcode.com/problems/valid-parentheses/solutions/9178/short-java-solution-by-phoenix13steve-a11b/comments/751694/ (This comment has the link to the youtube video)
*/
