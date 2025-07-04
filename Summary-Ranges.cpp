class Solution {
public:
    vector<string> summaryRanges(vector<int>& nums) {
        int i = 0 , j = 0;
        vector<string> ans;
        if (nums.size() == 0) return vector<string>();

        while(j < nums.size()) {
            if ( i == j ) {
                j++;
                continue;
            }
            if (nums[j] == nums[j-1] + 1) {
                j++;
            } else {
                string currRange;
                if ( j-1 == i ) {
                    currRange = format("{}",nums[i]);
                } else {
                    currRange = format("{}->{}",nums[i], nums[j-1]);
                }
                ans.push_back(currRange);
                i = j;
            }
        }

        string currRange;
        if ( i == nums.size() - 1 ) {cout << nums[i] << endl;
            currRange = format("{}",nums[i]);
        } else {
            currRange = format("{}->{}",nums[i], nums[j-1]);
        }
        ans.push_back(currRange);
        return ans;
    }
};

/*
  Pretty Strightforward solution where we scan the sorted array and find out ranges by using 2 pointers. only 2 element types exist -> single value and range. Code is easy to read and understand. 
  Extra steps written at the end to handle end of nums / edge cases.
*/
