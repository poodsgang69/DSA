class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        // ios_base::sync_with_stdio(0);
        int left, right, size = nums.size();
        vector<vector<int>> answer;
        std::sort(nums.begin(), nums.end());

        for ( int i = 0 ; i < size - 1; i++ ) {
            if (i>0 && nums[i] == nums[i-1]) continue;
            int target = (-1)*nums[i];
            twoSum(nums, answer, i, target);
        }

        return answer;
    }
private:
    void twoSum(vector<int>& nums, vector<vector<int>>& answer, int i, int target) {
        int begin = i + 1;
        int end = nums.size()-1;
        while(begin < end) {
            int currSum = nums[begin] + nums[end];
            if (currSum == target) {
                answer.push_back(vector<int>{nums[begin], nums[end], nums[i]});
                //check the front and back duplicate elements
                int leftEle = nums[begin], rightEle = nums[end];
                while(begin < end && nums[begin]==leftEle) begin++;
                while(begin < end && nums[end]==rightEle) end--;
            } else if (currSum > target) {
                end--;
            } else {
                begin++;
            }
        }
    }
};

/*
  Write the comments for this. Its present in the diary but write or generate the output using cursor. 
*/
