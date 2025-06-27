class Solution {
public:
    bool isValidSudoku(vector<vector<char>>& board) {
        unordered_set<char> s;
    
        // Check rows and columns
        for(int i = 0; i < 9; i++) {
            s.clear();
            // Check row i
            for(int j = 0; j < 9; j++) {
                if(board[i][j] == '.') continue;
                if(s.count(board[i][j])) return false;  // Duplicate found
                s.insert(board[i][j]);
            }
            
            s.clear();
            // Check column i
            for(int j = 0; j < 9; j++) {
                if(board[j][i] == '.') continue;
                if(s.count(board[j][i])) return false;  // Duplicate found
                s.insert(board[j][i]);
            }
        }
        
        // Check 3×3 boxes
        for(int i = 0; i < 9; i += 3) {
            for(int j = 0; j < 9; j += 3) {
                s.clear();
                for(int k = 0; k < 3; k++) {
                    for(int l = 0; l < 3; l++) {
                        if(board[i+k][j+l] == '.') continue;
                        if(s.count(board[i+k][j+l])) return false;  // Duplicate found
                        s.insert(board[i+k][j+l]);
                    }
                }
            }
        }
    return true;
    }
};

