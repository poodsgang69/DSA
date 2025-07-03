class Solution {
public:
    bool containsNearbyDuplicateExtra(vector<int>& nums, int k) {
        unordered_map<int, vector<int>> idxMap;
        size_t size = nums.size();
        for ( int i = 0 ; i < size ; ++i ) {
            idxMap[nums[i]].push_back(i);
        }

        for (auto& p : idxMap) {
            size_t idxSize = p.second.size();
            if(idxSize > 1) {
                int j;
                for ( int i = 0 ; i < idxSize - 1 ; ++i ) {
                    j = i + 1;
                    if (p.second[j] - p.second[i] <= k) return true;
                }
            }
        }

        return false;
    }

    bool containsNearbyDuplicate(vector<int>& nums, int k) {
        unordered_map<int, int> idxMap;
        for ( int i = 0 ; i < nums.size() ; ++i ) {
            if (idxMap.find(nums[i]) != idxMap.end()) {
                if ( i - idxMap[nums[i]] <= k ) return true;
            }
            idxMap[nums[i]] = i;
        }

        return false;
    }
};

/*
  1) create a map of all occurances of a particular element (unordered_map<int, vector<int>>)
  2) filter every number whose idxCount is < 1
  3) For those which have more than 1 occurrance, subtract every idx from its previous one till you find the answer (return true)
  4) else return false as there is no pair whose abs(j-i) <= k.

  Second solution is just a direct way of finding the index difference. instead of maintaing an array, as we are only comparing indices with its previous occurred idx ,we can continuously calculate the subtraction and if we find the answer -> True else False.
  All while updating the last visited index till we traverse each index & the entire map.
*/
