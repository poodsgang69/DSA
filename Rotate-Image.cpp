class Solution {
public:
    void rotate(vector<vector<int>>& matrix) {
        int n = matrix.size();
        // First transpose the array
        transpose(matrix, n);
        // Then swap the rows
        swap(matrix, n);
    }

private:
    void transpose(vector<vector<int>>& m, int n) {
        // Traverse UTM
        int temp = 0;
        for( int i = 0 ; i < n - 1 ; ++i ) {
            for( int j = i + 1 ; j < n ; ++j ) {
                temp = m[i][j];
                m[i][j] = m[j][i];
                m[j][i] = temp;
            }
        }
    }

    void swap(vector<vector<int>>& m, int n) {
        int l = 0, r = n-1, temp = 0;
        while(l < r) {
            // keep swapping columns
            for( int j = 0 ; j < n ; ++j ) {
                temp = m[j][l];
                m[j][l] = m[j][r];
                m[j][r] = temp;
            }
            l++;
            r--;
        }
    }
};

/*
    The idea behind this solution is to first transpose the matrix and then swap the rows.
    Transposing a matrix means that the rows become columns and the columns become rows.
    Swapping the rows means that the first row becomes the last row, the second row becomes the second last row, and so on.
    This is done in O(n^2) time and O(1) space.
    The space complexity is O(1) because we are not using any extra space.
    The time complexity is O(n^2) because we are traversing the matrix twice.
    The space complexity is O(1) because we are not using any extra space.
    Solution is writtin in my diary.
*/