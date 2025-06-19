class Solution {
public:
    int jump(vector<int>& nums) {
        int jumps = 0, left = 0, right = 0, size = nums.size();
        int farthest, mini;

        while(right < size-1) {
            farthest = 0;
            for ( int i = left ; i <= right ; ++i ) {
                farthest = max(farthest, i + nums[i]);
            }
            left = right + 1;
            right = farthest;
            jumps++;
        }
        return jumps;
    }
};


/*
NAIVE Solution is solved using Recursion, which is O(N^N) Time and O(N) Space (recursion stack space) 
its pretty simple to solve, just follow the algo in this video: https://www.youtube.com/watch?v=7SBVnw7GSTk
NOTE: 1) Try to solve this question using DP (event thouth this takes O(N^2) Space and Time - Just to learn)
      2) Watch Striver's DP Playlist while travelling to get a grasp of DP and how to convert Recursive problems into DP 
*/