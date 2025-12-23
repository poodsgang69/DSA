class Solution {
    public int findDuplicate(int[] nums) {
        int len = nums.length;
        boolean[] visited = new boolean[len];
        for(int i = 0 ; i<len ; ++i) {
            if (!visited[nums[i]-1]) visited[nums[i]-1] = true;
            else return nums[i];
        }
        return -1;
    }
}