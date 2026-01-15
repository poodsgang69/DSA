import java.util.Stack;

class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        
        int cols = matrix[0].length;
        int rows = matrix.length;
        int maxArea = 0;
        int[] currHist = new int[cols];
        
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                // If we see a '1', the "building" gets taller.
                // If we see a '0', the "building" is destroyed (reset to 0).
                if (matrix[i][j] == '1') currHist[j] += 1;
                else currHist[j] = 0;
            }
            // Treat the current cumulative heights as a histogram.
            maxArea = Math.max(maxArea, largestRectangleArea(currHist));
        }
        return maxArea;
    }

    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stk = new Stack<Integer>();
        int nextSmallestElement, prevSmallestElement, maxArea = 0, element;
        
        for (int i = 0; i < heights.length; ++i) {
            /* MONOTONIC STACK LOGIC: 
               We maintain a stack of indices where heights are increasing.
               When we find a height SHORTER than the one at stk.peek(), 
               it means we've found the "Right Boundary" (NSE) for the height at the top of the stack.
            */
            while (!stk.isEmpty() && heights[i] < heights[stk.peek()]) {
                element = stk.peek(); 
                stk.pop();
                
                // The current index 'i' is the first element to the RIGHT smaller than 'element'
                nextSmallestElement = i;
                
                // After popping, the new stk.peek() is the first element to the LEFT smaller than 'element'
                prevSmallestElement = stk.isEmpty() ? -1 : stk.peek();
                
                // Width = (RightIndex - LeftIndex - 1)
                maxArea = Math.max(maxArea, heights[element] * (nextSmallestElement - prevSmallestElement - 1));
            }
            stk.push(i);
        }

        /* CLEANUP:
           For elements remaining in the stack, there was no 'i' smaller than them to the right.
           Therefore, their "Right Boundary" is the end of the array.
        */
        while (!stk.isEmpty()) {
            element = stk.peek();
            stk.pop();
            nextSmallestElement = heights.length; 
            prevSmallestElement = stk.isEmpty() ? -1 : stk.peek();
            maxArea = Math.max(maxArea, heights[element] * (nextSmallestElement - prevSmallestElement - 1));
        }

        return maxArea;
    }
}

/*
================================================================================
ALGORITHM THEORY: MAXIMAL RECTANGLE IN BINARY MATRIX
================================================================================

1. CORE CONCEPT: ROW-BY-ROW HISTOGRAMS
   The problem is converted from 2D to 1D. We imagine the matrix as a floor. 
   As we move down each row, we calculate the "height" of consecutive 1s above 
   each cell. This transforms every row into a Histogram problem.
   
   

2. MONOTONIC STACK (Largest Rectangle in Histogram)
   To find the area in O(N) time, we need the "Nearest Smaller Element" (NSE) 
   on both the left and the right for every bar in the histogram.
   
   - Height: The value at heights[element].
   - Right Boundary (NSE): The current index 'i' that forced the pop.
   - Left Boundary (PSE): The index sitting below the current element in the stack.
   - Width Calculation: (Right - Left - 1). 
     Example: If NSE is index 5 and PSE is index 1, the bar at index 2, 3, and 4 
     can all form a rectangle of that height. (5 - 1 - 1 = 3).

3. TIME & SPACE COMPLEXITY
   - Time: O(Rows * Cols). We visit every cell once to update heights, 
     and the Stack logic processes every column in O(1) amortized time per row.
   - Space: O(Cols). We store a 1D 'heights' array and a stack that grows 
     at most to the number of columns.

4. KEY EDGE CASES
   - Empty Matrix: Handled by the initial null check.
   - All 0s: Heights will never increment, maxArea remains 0.
   - All 1s: Heights will increment every row; maxArea will be Rows * Cols.
   - Resetting to 0: Crucial! If matrix[i][j] is '0', currHist[j] MUST reset 
     to 0 because a rectangle cannot "jump" over a zero.
================================================================================
*/