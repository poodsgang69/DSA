class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        ios_base::sync_with_stdio(0);
        int l = 0, r = 0, size = s.size(), maxSize = 0;
        std::unordered_map<char, int>map;

        for (int r = 0 ; r < size ; r++ ) {
            if(map[s[r]]>0) {
                l = std::max(map[s[r]], l);
            }
            maxSize = std::max(maxSize, r - l + 1);
            map[s[r]] = r+1;
        }
        return maxSize;
    }
};

/*
  Explaination is written in the Diary. Basically we need to store the nextGoing Index at every point in the algo. This handles the duplicates. 
*/
