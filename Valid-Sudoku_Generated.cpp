#include <iostream>
#include <vector>
#include <unordered_set>
#include <string>
using namespace std;

class Solution {
public:
    // Approach 1: Hash Set Method (Most Intuitive)
    bool isValidSudoku(const vector<vector<char>>& board) {
        // Create hash sets for rows, columns, and boxes
        vector<unordered_set<char>> rows(9);
        vector<unordered_set<char>> cols(9);
        vector<unordered_set<char>> boxes(9);
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char cell = board[i][j];
                
                // Skip empty cells
                if (cell == '.') continue;
                
                // Calculate box index: (row/3)*3 + (col/3)
                int boxIndex = (i / 3) * 3 + (j / 3);
                
                // Check if digit already exists in row, column, or box
                if (rows[i].count(cell) || cols[j].count(cell) || boxes[boxIndex].count(cell)) {
                    return false;
                }
                
                // Add digit to all three sets
                rows[i].insert(cell);
                cols[j].insert(cell);
                boxes[boxIndex].insert(cell);
            }
        }
        
        return true;
    }
    
    // Approach 2: Bit Manipulation (Most Space Efficient)
    bool isValidSudokuBitManipulation(const vector<vector<char>>& board) {
        vector<int> rows(9, 0);
        vector<int> cols(9, 0);
        vector<int> boxes(9, 0);
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char cell = board[i][j];
                
                if (cell == '.') continue;
                
                int digit = cell - '0';
                int mask = 1 << digit;
                int boxIndex = (i / 3) * 3 + (j / 3);
                
                // Check if digit already exists using bitwise AND
                if ((rows[i] & mask) || (cols[j] & mask) || (boxes[boxIndex] & mask)) {
                    return false;
                }
                
                // Set the bit for this digit using bitwise OR
                rows[i] |= mask;
                cols[j] |= mask;
                boxes[boxIndex] |= mask;
            }
        }
        
        return true;
    }
    
    // Approach 3: Array-based Method (Cache Friendly)
    bool isValidSudokuArray(const vector<vector<char>>& board) {
        // Use boolean arrays for better cache performance
        vector<vector<bool>> rows(9, vector<bool>(10, false));
        vector<vector<bool>> cols(9, vector<bool>(10, false));
        vector<vector<bool>> boxes(9, vector<bool>(10, false));
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char cell = board[i][j];
                
                if (cell == '.') continue;
                
                int digit = cell - '0';
                int boxIndex = (i / 3) * 3 + (j / 3);
                
                // Check for duplicates
                if (rows[i][digit] || cols[j][digit] || boxes[boxIndex][digit]) {
                    return false;
                }
                
                // Mark as seen
                rows[i][digit] = true;
                cols[j][digit] = true;
                boxes[boxIndex][digit] = true;
            }
        }
        
        return true;
    }
    
    // Approach 4: Single Hash Set with String Keys
    bool isValidSudokuStringKeys(const vector<vector<char>>& board) {
        unordered_set<string> seen;
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char cell = board[i][j];
                
                if (cell == '.') continue;
                
                // Create unique keys for row, column, and box
                string rowKey = "row" + to_string(i) + cell;
                string colKey = "col" + to_string(j) + cell;
                string boxKey = "box" + to_string((i/3)*3 + j/3) + cell;
                
                // Check if any key already exists
                if (seen.count(rowKey) || seen.count(colKey) || seen.count(boxKey)) {
                    return false;
                }
                
                // Add all keys
                seen.insert(rowKey);
                seen.insert(colKey);
                seen.insert(boxKey);
            }
        }
        
        return true;
    }
    
    // Approach 5: Brute Force Method (For Understanding)
    bool isValidSudokuBruteForce(const vector<vector<char>>& board) {
        // Check each row
        for (int i = 0; i < 9; i++) {
            unordered_set<char> rowSet;
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.' && !rowSet.insert(board[i][j]).second) {
                    return false;
                }
            }
        }
        
        // Check each column
        for (int j = 0; j < 9; j++) {
            unordered_set<char> colSet;
            for (int i = 0; i < 9; i++) {
                if (board[i][j] != '.' && !colSet.insert(board[i][j]).second) {
                    return false;
                }
            }
        }
        
        // Check each 3x3 box
        for (int box = 0; box < 9; box++) {
            unordered_set<char> boxSet;
            int startRow = (box / 3) * 3;
            int startCol = (box % 3) * 3;
            
            for (int i = startRow; i < startRow + 3; i++) {
                for (int j = startCol; j < startCol + 3; j++) {
                    if (board[i][j] != '.' && !boxSet.insert(board[i][j]).second) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
};

// Helper function to print the board
void printBoard(const vector<vector<char>>& board) {
    cout << "Sudoku Board:" << endl;
    for (int i = 0; i < 9; i++) {
        if (i % 3 == 0 && i != 0) {
            cout << "------+-------+------" << endl;
        }
        for (int j = 0; j < 9; j++) {
            if (j % 3 == 0 && j != 0) {
                cout << "| ";
            }
            cout << board[i][j] << " ";
        }
        cout << endl;
    }
    cout << endl;
}

// Test function to validate all approaches
void testAllApproaches(Solution& sol, const vector<vector<char>>& board, const string& testName) {
    cout << "=== " << testName << " ===" << endl;
    printBoard(board);
    
    bool result1 = sol.isValidSudoku(board);
    bool result2 = sol.isValidSudokuBitManipulation(board);
    bool result3 = sol.isValidSudokuArray(board);
    bool result4 = sol.isValidSudokuStringKeys(board);
    bool result5 = sol.isValidSudokuBruteForce(board);
    
    cout << "Hash Set Method: " << (result1 ? "VALID" : "INVALID") << endl;
    cout << "Bit Manipulation: " << (result2 ? "VALID" : "INVALID") << endl;
    cout << "Array Method: " << (result3 ? "VALID" : "INVALID") << endl;
    cout << "String Keys: " << (result4 ? "VALID" : "INVALID") << endl;
    cout << "Brute Force: " << (result5 ? "VALID" : "INVALID") << endl;
    
    // Verify all methods give same result
    if (result1 == result2 && result2 == result3 && result3 == result4 && result4 == result5) {
        cout << "✓ All methods agree!" << endl;
    } else {
        cout << "✗ Methods disagree!" << endl;
    }
    cout << endl;
}

int main() {
    Solution sol;
    
    // Test Case 1: Valid Sudoku Board
    vector<vector<char>> validBoard = {
        {'5','3','.','.','7','.','.','.','.'},
        {'6','.','.','1','9','5','.','.','.'},
        {'.','9','8','.','.','.','.','6','.'},
        {'8','.','.','.','6','.','.','.','3'},
        {'4','.','.','8','.','3','.','.','1'},
        {'7','.','.','.','2','.','.','.','6'},
        {'.','6','.','.','.','.','2','8','.'},
        {'.','.','.','4','1','9','.','.','5'},
        {'.','.','.','.','8','.','.','7','9'}
    };
    
    // Test Case 2: Invalid Board (Duplicate in row)
    vector<vector<char>> invalidRowBoard = {
        {'8','3','.','.','7','.','.','.','.'},
        {'6','.','.','1','9','5','.','.','.'},
        {'8','9','8','.','.','.','.','6','.'},  // Duplicate 8 in row
        {'8','.','.','.','6','.','.','.','3'},
        {'4','.','.','8','.','3','.','.','1'},
        {'7','.','.','.','2','.','.','.','6'},
        {'.','6','.','.','.','.','2','8','.'},
        {'.','.','.','4','1','9','.','.','5'},
        {'.','.','.','.','8','.','.','7','9'}
    };
    
    // Test Case 3: Invalid Board (Duplicate in column)
    vector<vector<char>> invalidColBoard = {
        {'5','3','.','.','7','.','.','.','.'},
        {'6','.','.','1','9','5','.','.','.'},
        {'.','9','8','.','.','.','.','6','.'},
        {'8','.','.','.','6','.','.','.','3'},
        {'4','.','.','8','.','3','.','.','1'},
        {'7','.','.','.','2','.','.','.','6'},
        {'.','6','.','.','.','.','2','8','.'},
        {'.','.','.','4','1','9','.','.','5'},
        {'5','.','.','.','8','.','.','7','9'}  // Duplicate 5 in column 0
    };
    
    // Test Case 4: Empty Board (Valid)
    vector<vector<char>> emptyBoard = {
        {'.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.'}
    };
    
    // Run all tests
    testAllApproaches(sol, validBoard, "Valid Sudoku Board");
    testAllApproaches(sol, invalidRowBoard, "Invalid Board (Duplicate in Row)");
    testAllApproaches(sol, invalidColBoard, "Invalid Board (Duplicate in Column)");
    testAllApproaches(sol, emptyBoard, "Empty Board");
    
    cout << "=== PERFORMANCE COMPARISON ===" << endl;
    cout << "All methods have O(1) time complexity since board is always 9x9" << endl;
    cout << "Space Complexity:" << endl;
    cout << "- Hash Set: O(n²) = O(1) - Most intuitive" << endl;
    cout << "- Bit Manipulation: O(n) = O(1) - Most space efficient" << endl;
    cout << "- Array Method: O(n²) = O(1) - Cache friendly" << endl;
    cout << "- String Keys: O(n²) = O(1) - Single data structure" << endl;
    cout << "- Brute Force: O(n²) = O(1) - Multiple passes" << endl;
    
    return 0;
}

/*
VALID SUDOKU ALGORITHM EXPLANATION
==================================

PROBLEM: Determine if a 9×9 Sudoku board is valid according to Sudoku rules.

CONSTRAINTS:
- Board is always 9×9
- Only filled cells need validation
- Empty cells are represented by '.'
- Each row, column, and 3×3 box must contain digits 1-9 without repetition

KEY INSIGHTS:
1. Validation vs Solvability: We only check if current board is valid, not if it's solvable
2. Three Validation Rules: Row, column, and 3×3 box uniqueness
3. Single Pass Optimization: Check all three conditions simultaneously
4. Box Index Formula: (row/3)*3 + (col/3) for 3×3 box identification

APPROACHES COMPARISON:

1. HASH SET METHOD (Recommended for Interviews):
   - Most intuitive and readable
   - Uses separate sets for rows, columns, and boxes
   - Easy to understand and explain
   - Good for interview situations

2. BIT MANIPULATION (Most Efficient):
   - Uses integers to represent seen digits as bits
   - Most space-efficient approach
   - Fast bitwise operations
   - More complex but highly optimized

3. ARRAY METHOD (Cache Friendly):
   - Uses boolean arrays instead of hash sets
   - Better cache performance
   - Simple and efficient
   - Good balance of readability and performance

4. STRING KEYS (Single Data Structure):
   - Uses one hash set with unique string keys
   - Simple implementation
   - Easy to understand
   - Slightly more overhead due to string operations

5. BRUTE FORCE (Educational):
   - Checks rows, columns, and boxes separately
   - Multiple passes through the board
   - Good for understanding the problem
   - Not recommended for production

TIME COMPLEXITY: O(1) since board is always 9×9
SPACE COMPLEXITY: O(1) for all approaches

EDGE CASES HANDLED:
- Empty board (all '.' characters)
- Boards with duplicates in rows, columns, or boxes
- Partially filled boards
- Boards with no duplicates

INTERVIEW TIPS:
1. Start with hash set approach for clarity
2. Mention bit manipulation as optimization
3. Explain the box index formula
4. Discuss space-time trade-offs
5. Consider edge cases and validation

COMMON MISTAKES:
1. Forgetting to check 3×3 boxes
2. Incorrect box index calculation
3. Not handling empty cells properly
4. Comparing characters instead of converting to integers
5. Not considering early termination
*/ 