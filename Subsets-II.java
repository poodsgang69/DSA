class Solution {
    private void rec(int index, int[] nums, List<Integer> curr_ans, List<List<Integer>> final_ans, boolean isPicked) {
        if(index >= nums.length) return;
        
        if(index == 0 || nums[index] != nums[index-1] || isPicked == true) {
            // pick it
            curr_ans.add(nums[index]);
            final_ans.add(new ArrayList<>(curr_ans));
            rec(index+1, nums, curr_ans, final_ans, true);
            curr_ans.remove(curr_ans.size() - 1);
        }
        rec(index+1, nums, curr_ans, final_ans, false);
    }
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> final_ans = new ArrayList<>();
        final_ans.add(new ArrayList<>());
        List<Integer> curr_ans = new ArrayList<>();
        Arrays.sort(nums);
        rec(0, nums, curr_ans, final_ans, true);
        return final_ans;
    }
}