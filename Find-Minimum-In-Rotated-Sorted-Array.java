class Solution {
    public int findMin(int[] nums) {
        // something related to the array needing to be split as there will be a left partition
        // and right partition where we need to find which partition contains the start of the 
        // actual sorted array (lowest element is the first element of this figured partition).
        int left = 0, right = nums.length - 1;
        int currMin = 1001;
        while(left <= right) {
            int mid = (right + left) / 2;
            if(nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                currMin = Math.min(currMin, nums[mid]);
                right = mid - 1;
            }
        }

        return currMin;
    }
}
