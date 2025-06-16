class Solution {
public:
    // int hIndexNaive(vector<int>& citations) {
    //     int max_ele = *max_element(citations.begin(), citations.end());
    //     int best_ans = 0;
    //     for( int i = 1 ; i <= max_ele ; i++ ) {
    //         vector<int> ans_arr = {};
    //         for (int ele:citations) {
    //             if (ele >= i) {
    //                 ans_arr.push_back(ele);
    //             }
    //         }
    //         if (ans_arr.size() >= i) {
    //             best_ans = i;
    //         }
    //     }

    //     return best_ans;
    // }
    int hIndex(vector<int>& citations) {
        // binary search 
        int max_ele = *max_element(citations.begin(), citations.end());
        int best_ans = 0;
        int high = max_ele, low = 1;
        int mid;

        while(low <= high) {
            // mid = low + ((high - low) / 2);
            mid = ((unsigned int)low + (unsigned int)high) >> 1;
            vector<int> ans_arr = {};
            for (int ele:citations) {
                if (ele >= mid) {
                    ans_arr.push_back(ele);
                }
            }
            if (ans_arr.size() >= mid) {
                best_ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return best_ans;
    }
};

/*
Link: https://leetcode.com/problems/h-index/description/?envType=study-plan-v2&envId=top-interview-150
the first solution is a naive approach where we select the numbers from 1-max(citations) where we select each number & try to find out which of the  citations are >= the selected numbers and we maintain a max_ele/best_ans to track the max of the selected number.
We can improve this by using the Binary Search Approach, Why? Because we are basically selecting numbers from 1->max(citations) so instead of going linearly, we can use binary search to reduce the time taken to search for the perfect number / h value.
*/
