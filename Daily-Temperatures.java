/*

Think about how to solve it using a DP Approach.

*/


class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<int[]> tempsStack = new ArrayDeque<int[]>();
        int[] result = new int[temperatures.length];
        for ( int i = 0 ; i < temperatures.length ; ++i ) {
            int temp = temperatures[i];
            while(!tempsStack.isEmpty() && temp > tempsStack.peek()[0]) {
                // if whatever temperature i am inserting is bigger than what was there at the top of the stack
                // pop that element and add the steps (index of bigger temp - index of the top of stack) into the result array
                int[] currTempPair = tempsStack.pop();
                
                // need to update the result at that element's index so need to hit result index at value of currTempPair[1] which 
                //stores that popped element's index and i is the index of the current (bigger than stack.pop()) temperature. 
                result[currTempPair[1]] = i - currTempPair[1]; // index 1 stores the index
            }

            //insert that temp pair into stack
            tempsStack.push(new int[]{temp, i});
            
        }

        return result;
    }
}



/*
Algorithm Notes — Daily Temperatures (Monotonic Stack)

Goal:
For each day, find how many days you must wait until a warmer temperature.
If none, the result for that day is 0.

Core Idea:
Use a monotonic decreasing stack that stores pairs [temperature, index].
The stack keeps track of days with unresolved "next warmer day" temperatures.

Step-by-step:
1. Iterate through each day's temperature (index i).
2. While the current temperature is higher than the temperature at the top of the stack:
     - Pop the top pair (colder day) from the stack.
     - The difference in indices (i - poppedIndex) gives how many days it took to find a warmer temperature.
     - Update result[poppedIndex] = i - poppedIndex.
   This loop ensures the stack is always in decreasing order of temperature.
3. Push the current day's [temperature, index] onto the stack.
   (Its warmer day will be found later, if any.)

After iteration:
- Any indices left in the stack have no warmer day ahead → their result stays 0.

Intuition:
- Each element is pushed once and popped once → O(n) total time.
- The stack "remembers" days waiting for a warmer future day.
- The index difference gives the waiting period.

Complexity:
- Time: O(n)
- Space: O(n)
*/
