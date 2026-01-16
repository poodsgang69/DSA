class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        // Sort the horizontal and vertical bars
        Arrays.sort(hBars);
        Arrays.sort(vBars);
        int maxConsecutiveHStreak, maxConsecutiveVStreak, squareLowerBoundLength;
        maxConsecutiveHStreak = findStreak(hBars);
        maxConsecutiveVStreak= findStreak(vBars);
        squareLowerBoundLength = Math.min(maxConsecutiveHStreak, maxConsecutiveVStreak);
        return squareLowerBoundLength * squareLowerBoundLength;
    }

    private int findStreak(int[] bars) {
        int streak = 1;
        int maxStreak = 1;
        for(int i = 0 ; i < bars.length - 1 ; ++i) {
            if(bars[i+1] - bars[i] == 1) streak++;
            else streak = 1;
            maxStreak = Math.max(maxStreak, streak);
        }
        return maxStreak+1;
    }
}

/*
Perfect Explaination: https://leetcode.com/problems/maximize-area-of-square-hole-in-grid/solutions/7495395/maximize-area-of-square-hole-in-grid-by-0d0ol
Time Complexity: O(nLogn) + O(mLogm) [assuming n is length of hBars and m is length of vBars]
Space Complexity: O(1) [No extra space used except integers to store auxiliary variables]
*/