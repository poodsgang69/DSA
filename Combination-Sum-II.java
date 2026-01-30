class Solution {
    private void rec(int[] nums, int index, int remaining, List<Integer> curr_ans, List<List<Integer>> final_ans) {
        if(remaining < 0) return;
        if(remaining == 0) {
            final_ans.add(new ArrayList<>(curr_ans));
        }
        for(int i = index; i < nums.length ; ++i ) {
            if(i > index && nums[i] == nums[i-1]) continue; // skip the element to avoid duplicate combinations
            if(nums[i] > remaining) break;
            curr_ans.add(nums[i]); // choose the element
            rec(nums, i+1, remaining - nums[i], curr_ans, final_ans);
            curr_ans.remove(curr_ans.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> final_ans = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        rec(candidates, 0, target, new ArrayList<>(), final_ans);
        return final_ans;
    }
}