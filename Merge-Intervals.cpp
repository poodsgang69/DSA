class Solution {
public:
    vector<vector<int>> mergeJiji(vector<vector<int>>& intervals) {
        vector<vector<int>> mergedIntervals;
        sort(intervals.begin(), intervals.end());
        for(vector<int> interval: intervals) {
            if(!mergedIntervals.empty()) {
                int i = mergedIntervals.size() - 1;
                if(mergedIntervals[i][1] >= interval[0])
                    mergedIntervals[i][1] = max(mergedIntervals[i][1], interval[1]);
                else
                    mergedIntervals.push_back(interval);
            } else mergedIntervals.push_back(interval);
        }
        return mergedIntervals;
    }

    vector<vector<int>> merge(vector<vector<int>>& intervals) {
        if (intervals.empty()) return {};
        vector<vector<int>> ans;
        std::sort(intervals.begin(), intervals.end());
        ans.push_back(intervals[0]);

        for ( int i = 1 ; i < intervals.size(); ++i ) {
            if (intervals[i][0] <= ans.back()[1]) {
                ans.back()[1] = std::max(ans.back()[1], intervals[i][1]);
            } else {
                ans.push_back(intervals[i]);
            }
        }

        return ans;
    }

};

/*
MERGE INTERVALS ALGORITHM EXPLANATION

This algorithm merges overlapping intervals in a given array of intervals.

KEY CONCEPTS:
1. SORTING: First sort intervals by start time (intervals[i][0])
   - This ensures we process intervals in chronological order
   - Makes it easier to detect overlaps

2. MERGING LOGIC:
   - Keep track of the last merged interval in result array
   - For each new interval, check if it overlaps with the last merged interval
   - Overlap condition: new_interval_start <= last_merged_end
   - If overlap exists: update the end time to max(last_merged_end, new_interval_end)
   - If no overlap: add the new interval to result array

3. TIME COMPLEXITY: O(n log n) due to sorting
   SPACE COMPLEXITY: O(n) for storing merged intervals

*/