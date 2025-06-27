class Solution {
public:
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        vector<int> ans;
        int m = matrix.size(), n = matrix[0].size();
        int rMin = 0, rMax = m - 1, cMin = 0, cMax = n - 1;
        while(ans.size() < m*n) {
            for ( int i = cMin ; i <= cMax ; i++ ) {
                ans.push_back(matrix[rMin][i]);
            }
            for ( int j = rMin + 1 ; j <= rMax ; j++ ) {
                ans.push_back(matrix[j][cMax]);
            }
            for ( int i = cMax - 1 ; ans.size() < m*n && i >= cMin && rMin <= rMax; i-- ) {
                ans.push_back(matrix[rMax][i]);
            }
            for ( int j = rMax - 1 ; ans.size() < m*n && j >= rMin + 1&&  cMin <= cMax ; j-- ) {
                ans.push_back(matrix[j][cMin]);
            }
            rMin++;
            rMax--;
            cMin++;
            cMax--;
        }
        return ans;
    }
};

/*
  Try to understand why we use ans.size() < m*n and the rMin <= rMax (mostly edge cases)
*/
