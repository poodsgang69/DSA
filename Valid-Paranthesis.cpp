class Solution {
public:
    bool isValid(string s) {
        unordered_map<char, char> mapping = {
            {')', '('},
            {']', '['},
            {'}', '{'}
        };
        stack<int> stack;

        for(auto& p : s) {
            if (mapping.count(p)) {
                if(stack.size() == 0) return false;
                // element is in the map -> it is a closing bracket
                if(stack.top() == mapping[p]) {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                stack.push(p);
            }
        }

        return !stack.size() && true;
    }
};

/*
    Straightforward solution using a stack.
    We use a map to store the mapping of the closing brackets to the opening brackets.
    We then use a stack to store the opening brackets.
    We then iterate through the string and check if the current character is a closing bracket.
    If it is, we check if the stack is empty or if the top of the stack is the corresponding opening bracket.
    If it is, we pop the top of the stack.
    If it is not, we return false.
    If it is, we continue.
    Finally, we return true if the stack is empty, otherwise false.

    Time Complexity: O(n)
    Space Complexity: O(n)
*/