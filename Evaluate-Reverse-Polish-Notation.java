import java.util.*;

public class Solution {
    public static int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        HashSet<String> ops = new HashSet<>(Arrays.asList("+", "-", "*", "/"));

        for (String token : tokens) {
            if (!ops.contains(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                int num1 = stack.pop();
                int num2 = stack.pop();
                int result;
                switch (token) {
                    case "+" -> result = num2 + num1;
                    case "-" -> result = num2 - num1;
                    case "*" -> result = num2 * num1;
                    case "/" -> result = num2 / num1;
                    default -> throw new IllegalArgumentException("Invalid operator: " + token);
                }
                stack.push(result);
            }
        }

        return stack.pop();
    }
}

/* simple */
