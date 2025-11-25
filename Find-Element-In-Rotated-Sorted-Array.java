class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while(left <= right) {
            int mid = (right + left) / 2;

            if(nums[mid] == target) return mid;

            else if(nums[mid] >= nums[left]) { // mid value is in the same partition as the left value and 
            // that could mean there is a next higher value to the right of mid
            // need to chcek the target with the left and the mid to figure out where to search (left of mid or right of mid)
                if (target > nums[mid] || target < nums[left]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if(target < nums[mid] || target > nums[right]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        return -1;

    }
}
