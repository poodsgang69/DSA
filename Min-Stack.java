class MinStackPRQ {

    private Stack<Integer> minStk;            // declare at class level
    private PriorityQueue<Integer> minHeap;   // declare at class level
    
    public MinStackPRQ() {
        minStk = new Stack<>();               // initialize inside constructor
        minHeap = new PriorityQueue<>();
    }
    
    public void push(int val) {
        //Pushing into the Stack
        //need to push the element into the Stack and also the minHeap and keep calculating the min
        minStk.push(val);
        minHeap.add(val);
    }
    
    public void pop() {
        int removedElement = minStk.pop();
        minHeap.remove(removedElement);
    }
    
    public int top() {
        return minStk.peek();
    }
    
    public int getMin() {
        return minHeap.peek();
    }
}

class MinStack {

    private Deque<Integer> minStk;           
    private Deque<Integer> minEleStk;   
    
    public MinStack() {
        minStk = new ArrayDeque<>();               
        minEleStk = new ArrayDeque<>();
    }
    
    public void push(int val) {
        //Pushing into the Stack
        //need to push the element into the Stack and also the minHeap and keep calculating the min
        minStk.push(val);
        if(minEleStk.isEmpty() || minEleStk.peek() >= val) minEleStk.push(val);
    }
    
    public void pop() {
        int removedElement = minStk.pop();
        if(removedElement == minEleStk.peek()) minEleStk.pop();
    }
    
    public int top() {
        return minStk.peek();
    }
    
    public int getMin() {
        return minEleStk.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

/*

The first algo is pretty much a simple algo where we maintain a Priority Queue (MinHeap) to retrieve the minimum element of the stack in an average of O(1) time. but the problem arises when we pop, it is the top element
in the Stack but it could be any at arbitrary position in the MinHeap, which takes O(N) time. so that might make our code get accepted, but it is slow because of that. 
To tackle that, we use the below 2 stach approach.

Algorithm Notes — MinStack (Two-Stack Approach)

Goal:
Return min in O(1) while supporting standard stack ops.

Core Idea:
Maintain two stacks:
  - dataStack: stores all pushed values (normal stack behavior).
  - minStack: stores the running minimums in a monotonic (nonincreasing) fashion.

Push(x):
  - Push x to dataStack.
  - If minStack is empty OR x <= minStack.peek(), also push x to minStack.
    Intuition: whenever a new value is <= current min, it could be (or tie) the new minimum.
    We record it so that if it later gets popped, the previous minimum is still on minStack.

Pop():
  - Pop v from dataStack.
  - If v == minStack.peek(), also pop from minStack.
    Intuition: minStack mirrors the history of minima. Removing the current min reveals the next min underneath.

Top():
  - Return dataStack.peek().

getMin():
  - Return minStack.peek() (current minimum in O(1)).

Duplicates & Ties:
  - Use "x <= minStack.peek()" on push so equal minima are pushed again.
  - When popping, only the matching instance removes one copy from minStack.
  - This preserves correct next-min after duplicates are popped.

Complexity:
  - push / pop / top / getMin: O(1) amortized time.
  - Space: O(n) worst-case (strictly decreasing pushes replicate on minStack).
*/
