class Solution {
public:
    int longestConsecutiveSort(vector<int>& nums) {
        int counter=1, maxLen=1;
        if (nums.size()==0) return 0;
        if (nums.size()==1) return 1;

        std::sort(nums.begin(), nums.end());

        for (int i=1 ; i<nums.size() ; ++i) {
            if (nums[i] != nums[i-1]) {
                if (nums[i-1] == nums[i] - 1) {
                    counter += 1;
                } else {
                    maxLen=std::max(maxLen, counter);
                    counter = 1;
                }   
            }
        }
        return max(maxLen, counter);
    }

    int longestConsecutive(vector<int>& nums) {
        if (nums.size()==0) return 0;
        if (nums.size()==1) return 1;
        unordered_set<int> numSet(nums.begin(), nums.end());
        int maxLen = 1;
        for(auto& num : numSet) {
            if (numSet.find(num-1) == numSet.end()) { //Found the beginning of the consecutive sequence
                int currNum = num;
                int currLen = 1;
                while(numSet.find(currNum+1) != numSet.end()) {
                    currLen++;
                    currNum++;
                }
                maxLen = std::max(maxLen, currLen);
            }
        }
        return maxLen;
    }
};
