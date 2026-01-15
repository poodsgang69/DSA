class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stk = new Stack<Integer>();
        int nextSmallestElement, prevSmallestElement, maxArea = 0, element;
        for ( int i = 0 ; i < heights.length ; ++i ) {
            while(!stk.isEmpty() && heights[i] < heights[stk.peek()]) {
                // need to pop and calculate the maxArea in this part
                element = stk.peek();
                stk.pop();
                nextSmallestElement = i;
                prevSmallestElement = stk.isEmpty() ? -1 : stk.peek();
                maxArea = Math.max(
                    maxArea, 
                    heights[element] * (nextSmallestElement - prevSmallestElement - 1)
                );
            }
            // add the current element's index in consideration 
            // to the monotonically increasing stack 
            stk.push(i);
        }

        // If the stack still contains elements
        while(!stk.isEmpty()) {
            element = stk.peek();
            stk.pop();
            // NSE over here will be the end of the array (OOB) : heights.length + 1
            nextSmallestElement = heights.length;
            prevSmallestElement = stk.isEmpty() ? -1 : stk.peek();
            maxArea = Math.max(
                maxArea, 
                heights[element] * (nextSmallestElement - prevSmallestElement - 1)
            );
        }

        return maxArea;
    }
}

// See Striver's Video to understand
// Time Complexity: O(N) [Every element is traversed only once]
// Space Complexity: O(N) [Need Stack that can store at max (worst case) all the elements in the array] 