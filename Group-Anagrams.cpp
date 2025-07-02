class Solution {
public:
    vector<vector<string>> groupAnagramsOriginal(vector<string>& strs) {
        size_t size = strs.size();
        vector<string> originalStrs = strs;
        unordered_map<string, vector<int>> map;
        vector<vector<string>> ans;
        for ( int i = 0 ; i < size ; ++i ) {
            // sort the string
            std::sort(strs[i].begin(), strs[i].end());
        }

        // input the sorted strings in the map
        for ( int i = 0 ; i < size ; ++i ) {
            map[strs[i]].push_back(i);
        }

        // iterate through the map, group all the anagrams and return a final answer
        for ( auto& p : map ) {
            vector<string> levelGroup;
            for ( auto& idx : p.second ) {
                levelGroup.push_back(originalStrs[idx]);
            }
            ans.push_back(levelGroup);
        }

        return ans;
    }

    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        unordered_map<string, vector<string>> map;
        for (auto& s : strs) {
            string key = s;
            sort(key.begin(), key.end());
            map[key].push_back(std::move(s)); // move if you don't need strs anymore, else just push_back(s)
        }
        vector<vector<string>> ans;
        for (auto& p : map) {
            ans.push_back(std::move(p.second));
        }
        return ans;
    }
    
};

/*
    This code requires us to work in 3 parts as we need to group the anagrams together.
    1) Sort each string in the input vector. This way we can figure out which strings are anagrams of each other as no matter which permutation of the string we have, the sorted string will be the same.
    2) Input the sorted strings in a map / in the firsty solution i input the index of the original string in the map. This way, all the like strings will be grouped together / their indices will be grouped together.
    3) Iterate through the map and group the strings together, store the strings in a vector and push it back to the answer vector and return it.

    the second solution is a bit more efficient as we dont need to sort the strings at one go and then separately iterate through the vector and add the strings to the answer vector.
    Instead we sort the string and immediately add it to the map.
    Then we iterate through the map and add the strings to the answer vector and return it.

    Time Complexity: O(n * k log k) where n is the number of strings and k is the length of the longest string.
    Space Complexity: O(n * k) where n is the number of strings and k is the length of the longest string.

*/