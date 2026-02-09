class Solution {
    public int minimumCost(int[] nums) {
        //int cost = nums[0];
        int m1= Integer.MAX_VALUE;
        int m2 = Integer.MAX_VALUE;

        for (int i=1;i<nums.length;++i){
            if(nums[i]<m1){
                m2=m1;
                m1=nums[i];
            } else if(nums[i]<m2) m2 = nums[i];
        }

        return nums[0] + m1+m2;
    }
}

/**
 * INTUITION:
 * To split an array into 3 subarrays, we need 3 "starting elements" 
 * that act as the cost for each section.
 * * 1. The first subarray MUST start at index 0 (cost = nums[0]).
 * 2. We need to find two more starting indices (j and k) for the 
 * remaining two subarrays.
 * 3. Since Version A allows subarrays of size 1, we simply need 
 * the two smallest values available in the rest of the array 
 * (from index 1 onwards). 
 * * This algorithm performs a single "linear scan" to find the two 
 * minimum values (m1 and m2), resulting in O(n) time complexity.
 */
