class Solution {
    public int[] productExceptSelfWithArrays(int[] nums) {
        int counter = 0;
        int firstZeroIdx = -1;
        for(int i = 0 ; i < nums.length ; ++i) {
            if(nums[i] == 0) { 
                if(counter == 0) firstZeroIdx = i;
                counter++; 
            }
        }
        List<Integer> ans = new ArrayList<Integer>();
        if(counter == 1) {
            //one 0 means ans will be all 0's except where the idx is 0
            int prod = 1;
            for (int num : nums) {
                if(num != 0) prod *= num;
            }
            for( int i = 0 ; i < nums.length ; ++i ) {
                if(i == firstZeroIdx) ans.add(prod);
                else ans.add(0);
            }
        } else if (counter > 1) {
            // more than 1 0 means everywhere it will be 0
            for( int i = 0 ; i < nums.length ; ++i ) {
                ans.add(0);
            }
        } else {
            Integer[] forwardList = new Integer[nums.length];
            Integer[] backwardList = new Integer[nums.length];
            forwardList[0] = 1;
            backwardList[nums.length-1] = 1;
            int rollingProduct = 1;

            // fill in forward product
            for(int i = 1 ; i < nums.length ; ++i) {
                rollingProduct *= nums[i-1];
                forwardList[i] = rollingProduct;
            }

            rollingProduct = 1;
            //fill in backward product
            for(int i = nums.length - 2 ; i >= 0 ; --i) {
                rollingProduct *= nums[i+1];
                backwardList[i] = rollingProduct;
            }

            for(int i = 0 ; i < nums.length ; ++i) {
                ans.add(forwardList[i]*backwardList[i]);
            }

        }

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] productExceptSelf(int[] nums) {
        //combining both left prod and right prod array into a single arr result and it passes in 2 goes to get the result.
        int[] result = new int[nums.length];
        result[0] = 1;
        for(int i = 1 ; i < nums.length ; ++i ) {
            result[i] = result[i-1] * nums[i-1];
        }

        int right = 1;
        for( int i = nums.length - 1 ; i >= 0 ; --i ) {
            result[i] *= right;
            right *= nums[i];
        }
        
        return result;
    }
}


/*
Algorithm Notes — productExceptSelfWithArrays & productExceptSelf

1. The goal: For each index, return the product of all other elements in the array
   without using division, handling cases with zeros carefully.

2. Step 1: Count zeros in the input array.
   - If more than one zero → every product will be 0.
   - If exactly one zero → only the position of that zero gets the product of all nonzero elements.
   - If no zeros → compute products normally.

3. Step 2 (no zeros case):
   - Build two helper arrays:
       forwardList[i] = product of all elements to the left of i.
       backwardList[i] = product of all elements to the right of i.
   - Multiply forwardList[i] * backwardList[i] to get the final product for each index.

4. Step 3 (optimized version — productExceptSelf):
   - Use a single result array.
   - First pass: fill result[i] with product of all elements to its left.
   - Second pass (right-to-left): multiply each result[i] by product of elements to its right.
   - This avoids extra arrays and runs in O(n) time with O(1) extra space.

5. Both approaches return an int[] where each element represents
   the product of all other numbers except the one at that index.
*/
