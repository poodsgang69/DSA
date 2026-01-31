class Solution {
    private void dfs(int[] nums, boolean[] used, List<Integer> curr_ans, List<List<Integer>> final_ans, int size) {
        if(curr_ans.size() == size) final_ans.add(new ArrayList<>(curr_ans));
        for(int i = 0 ; i < size ; ++i) {
            if(used[i]) continue;
            else {
                used[i] = true;
                curr_ans.add(nums[i]);

                dfs(nums, used, curr_ans, final_ans, size);

                used[i] = false;
                curr_ans.remove(curr_ans.size() - 1);
            }
        }
    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> final_ans = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        dfs(nums, used, new ArrayList<>(), final_ans, nums.length);
        return final_ans;
        
    }
}
