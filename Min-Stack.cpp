#include <climits>

class MinStack {
private:
    std::vector<int> stack;
    int startPos;
    int topPos;
    int minEle;
public:
    MinStack() {
        stack = {};
        startPos = 0;
        topPos = 0;
        minEle = INT_MAX;
    }
    
    void push(int val) {
        stack.push_back(val);
        topPos++;
        minEle = std::min(minEle, val);
    }
    
    void pop() {
        int topEle = stack[topPos-1];
        stack.pop_back();
        if (topEle == minEle) {
            minEle = INT_MAX;
            // calculate new Min
            for (auto& ele : stack) {
                minEle = std::min(minEle, ele);
            }
        }
        cout << minEle;
        topPos--;
        cout << endl<<topEle;
        
    }
    
    int top() {
        return stack[topPos-1];
    }
    
    int getMin() {
        return minEle;
    }
};

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack* obj = new MinStack();
 * obj->push(val);
 * obj->pop();
 * int param_3 = obj->top();
 * int param_4 = obj->getMin();
 */

/* 
  Pretty simple min stack implementation. 
an improvemrnt would br to use a Vector<Pair<int, int>> to store elements and the minEle at that particular insertion point
to reduce minEle retrival times (rather than fetching the new min everytime the currMin is deleted from the stack).
*/