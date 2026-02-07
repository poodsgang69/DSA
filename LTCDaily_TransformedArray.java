class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int[] ans = new int[nums.length];
        int n = nums.length;
        int landed_index = -1;
        for (int i = 0 ; i < nums.length ; ++i) {
            if(nums[i] > 0) {
                landed_index = (i + nums[i]) % n;
                // ans[i] = nums[landed_index];
            } else if (nums[i] < 0) {
                landed_index = ((i - Math.abs(nums[i])) % n + n) % n;
                // ans[i] = nums[landed_index];
            } else {
                landed_index = i;
                // ans[i] = nums[i]; 
            }
            ans[i] = nums[landed_index];
        }
        return ans;
    }
}

/*

Thing to remember here is the concept of rotations and LandedIndices
i: Starting Index
k: Num of Steps
n: Size of Array

Forward: (i+k) % n
Backward: [((i-k)%n) + n ] % n (to make it type safe as some subtractions will result in negative indices so add to n and again modulo n to return positive index)

Time Complexity: O(N)
Space Complixity: O(N) [Cant do in place as we might land in an already deleted index as part of a rotation and we might need that number then]

*/
