class Solution {
public:
    vector<vector<int>> insertNaiveSorting(vector<vector<int>>& intervals, vector<int>& newInterval) {
        intervals.push_back(newInterval);
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

    vector<vector<int>> insert(vector<vector<int>>& intervals, vector<int>& newInterval) {
        vector<vector<int>> ans;
        size_t s = intervals.size();
        int i = 0;

        while( i < s && intervals[i][1] < newInterval[0] ) {
            ans.push_back(intervals[i]);
            i++;
        }

        while( i < s && intervals[i][0] <= newInterval[1] ) {
            newInterval[0] = std::min(newInterval[0], intervals[i][0]);
            newInterval[1] = std::max(newInterval[1], intervals[i][1]);
            ++i;
        }

        ans.push_back(newInterval);

        while ( i < s ) {
            ans.push_back(intervals[i]);
            ++i;
        }

        return ans;
    }
};

/*
INSERT INTERVALS ALGORITHM EXPLANATION

This algorithm merges a new interval into a sorted list of non-overlapping intervals.

ALGORITHM OVERVIEW:
The algorithm works in 3 phases:
1. Add all intervals that come BEFORE the new interval (no overlap possible)
2. Merge all intervals that OVERLAP with the new interval
3. Add all intervals that come AFTER the new interval (no overlap possible)

PHASE 1: Add intervals before newInterval
- While current interval's end < newInterval's start
- These intervals cannot overlap with newInterval since intervals are sorted
- Simply add them to result as-is

PHASE 2: Merge overlapping intervals
- While current interval's start <= newInterval's end
- This means there's some overlap between current interval and newInterval
- Update newInterval's bounds to encompass both intervals:
  * newInterval[0] = min(newInterval[0], current[0])  // earliest start
  * newInterval[1] = max(newInterval[1], current[1])  // latest end
- This effectively merges all overlapping intervals into one

PHASE 3: Add remaining intervals
- All remaining intervals come after the merged interval
- Simply add them to result as-is

TIME COMPLEXITY: O(n) where n = number of intervals
SPACE COMPLEXITY: O(n) for the result array

EXAMPLE:
intervals = [[1,3], [6,9]], newInterval = [2,5]
Phase 1: Add [1,3] (before [2,5])
Phase 2: Merge [1,3] with [2,5] -> [1,5]
Phase 3: Add [6,9] (after [1,5])
Result: [[1,5], [6,9]]

KEY INSIGHT:
Since input intervals are sorted, we can process them in order and only need
to check for overlaps during the middle phase where intervals could potentially
overlap with our new interval.
*/
