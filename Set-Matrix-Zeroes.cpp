class Solution {
public:
    void setZeroes(vector<vector<int>>& matrix) {
        unordered_set<int> zeroIndicesR;
        unordered_set<int> zeroIndicesC;
        int m = matrix.size();
        int n = matrix[0].size();

        for ( int i = 0 ; i < m ; ++i ) {
            for ( int j = 0 ; j < n ; ++j ) {
                if( matrix[i][j] == 0 ) {
                    zeroIndicesR.insert(i);
                    zeroIndicesC.insert(j);
                }
            }
        }

        for ( int i = 0 ; i < m ; ++i ) {
            for ( int j = 0 ; j < n ; ++j ) {
                if ( zeroIndicesR.count(i) == 1 || zeroIndicesC.count(j) == 1 ) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
};

/*
  Figure out how to solve this code in linear space. There is a way to use the first row and column as zeroIndicesR and zeroIndicesC. Figure that out.
*/
