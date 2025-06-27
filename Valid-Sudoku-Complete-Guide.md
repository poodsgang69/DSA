# Valid Sudoku - Complete Problem Solving Guide

## Table of Contents
1. [Problem Statement](#problem-statement)
2. [Understanding the Problem](#understanding-the-problem)
3. [Solution Approaches](#solution-approaches)
4. [Implementation in C++](#implementation-in-c)
5. [Time and Space Complexity Analysis](#time-and-space-complexity-analysis)
6. [Edge Cases and Testing](#edge-cases-and-testing)
7. [Practice Problems](#practice-problems)

---

## Problem Statement

**LeetCode #36: Valid Sudoku**

Determine if a 9×9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

1. Each row must contain the digits 1-9 without repetition.
2. Each column must contain the digits 1-9 without repetition.
3. Each of the nine 3×3 sub-boxes of the grid must contain the digits 1-9 without repetition.

**Note:**
- A Sudoku board (partially filled) could be valid but is not necessarily solvable.
- Only the filled cells need to be validated according to the mentioned rules.

**Example:**
```
Input: board = 
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]

Output: true
```

---

## Understanding the Problem

### Key Insights:
1. **Validation vs. Solvability**: We only need to check if the current board is valid, not if it can be solved.
2. **Three Validation Rules**: Each digit must be unique in its row, column, and 3×3 box.
3. **Empty Cells**: Represented by "." and should be ignored during validation.
4. **Board Structure**: 9×9 grid divided into 9 sub-boxes of 3×3 each.

### Visual Representation:
```
Board Layout:
┌─────────────────┬─────────────────┬─────────────────┐
│     Box 0       │     Box 1       │     Box 2       │
│  [0,0] [0,1] [0,2] │  [0,3] [0,4] [0,5] │  [0,6] [0,7] [0,8] │
│  [1,0] [1,1] [1,2] │  [1,3] [1,4] [1,5] │  [1,6] [1,7] [1,8] │
│  [2,0] [2,1] [2,2] │  [2,3] [2,4] [2,5] │  [2,6] [2,7] [2,8] │
├─────────────────┼─────────────────┼─────────────────┤
│     Box 3       │     Box 4       │     Box 5       │
│  [3,0] [3,1] [3,2] │  [3,3] [3,4] [3,5] │  [3,6] [3,7] [3,8] │
│  [4,0] [4,1] [4,2] │  [4,3] [4,4] [4,5] │  [4,6] [4,7] [4,8] │
│  [5,0] [5,1] [5,2] │  [5,3] [5,4] [5,5] │  [5,6] [5,7] [5,8] │
├─────────────────┼─────────────────┼─────────────────┤
│     Box 6       │     Box 7       │     Box 8       │
│  [6,0] [6,1] [6,2] │  [6,3] [6,4] [6,5] │  [6,6] [6,7] [6,8] │
│  [7,0] [7,1] [7,2] │  [7,3] [7,4] [7,5] │  [7,6] [7,7] [7,8] │
│  [8,0] [8,1] [8,2] │  [8,3] [8,4] [8,5] │  [8,6] [8,7] [8,8] │
└─────────────────┴─────────────────┴─────────────────┘
```

### Box Index Calculation:
For any cell at position `[row, col]`, the box index is:
```cpp
boxIndex = (row / 3) * 3 + (col / 3)
```

---

## Solution Approaches

### Approach 1: Brute Force (Naive)
**Intuition**: Check each row, column, and box separately using nested loops.

**Algorithm**:
1. Check each row for duplicates
2. Check each column for duplicates  
3. Check each 3×3 box for duplicates

**Pros**: Simple to understand and implement
**Cons**: Inefficient, lots of redundant checks

### Approach 2: Hash Set Optimization
**Intuition**: Use hash sets to track seen digits for each row, column, and box simultaneously.

**Algorithm**:
1. Create hash sets for each row, column, and box
2. Iterate through the board once
3. For each filled cell, check if digit exists in corresponding sets
4. If not, add to all three sets; if yes, return false

**Pros**: Single pass, efficient
**Cons**: Uses extra space

### Approach 3: Bit Manipulation
**Intuition**: Use bits to represent seen digits, making it more space-efficient.

**Algorithm**:
1. Use integers to represent seen digits (bit 1-9)
2. For each cell, set the corresponding bit
3. Check for conflicts using bitwise operations

**Pros**: Most space-efficient, fast
**Cons**: More complex to understand

### Approach 4: Array-based Tracking
**Intuition**: Use boolean arrays instead of hash sets for better cache performance.

**Algorithm**:
1. Create boolean arrays for rows, columns, and boxes
2. Use digit as index (1-9) to mark as seen
3. Check for duplicates in single pass

**Pros**: Cache-friendly, simple
**Cons**: Slightly more space than bit manipulation

---

## Implementation in C++

### Solution 1: Hash Set Approach
```cpp
class Solution {
public:
    bool isValidSudoku(vector<vector<char>>& board) {
        // Create hash sets for rows, columns, and boxes
        vector<unordered_set<char>> rows(9);
        vector<unordered_set<char>> cols(9);
        vector<unordered_set<char>> boxes(9);
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char cell = board[i][j];
                
                // Skip empty cells
                if (cell == '.') continue;
                
                // Calculate box index
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
};
```

### Solution 2: Bit Manipulation Approach
```cpp
class Solution {
public:
    bool isValidSudoku(vector<vector<char>>& board) {
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
                
                // Check if digit already exists
                if ((rows[i] & mask) || (cols[j] & mask) || (boxes[boxIndex] & mask)) {
                    return false;
                }
                
                // Set the bit for this digit
                rows[i] |= mask;
                cols[j] |= mask;
                boxes[boxIndex] |= mask;
            }
        }
        
        return true;
    }
};
```

### Solution 3: Array-based Approach
```cpp
class Solution {
public:
    bool isValidSudoku(vector<vector<char>>& board) {
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
};
```

### Solution 4: Single Pass with String Keys
```cpp
class Solution {
public:
    bool isValidSudoku(vector<vector<char>>& board) {
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
};
```

---

## Time and Space Complexity Analysis

### Hash Set Approach:
- **Time Complexity**: O(n²) where n = 9 (board size)
- **Space Complexity**: O(n²) for storing hash sets
- **Actual**: O(1) since board is always 9×9

### Bit Manipulation Approach:
- **Time Complexity**: O(n²) = O(1)
- **Space Complexity**: O(n) = O(1) for storing integers
- **Advantage**: Most space-efficient

### Array-based Approach:
- **Time Complexity**: O(n²) = O(1)
- **Space Complexity**: O(n²) = O(1)
- **Advantage**: Cache-friendly, simple

### String Key Approach:
- **Time Complexity**: O(n²) = O(1)
- **Space Complexity**: O(n²) = O(1)
- **Advantage**: Single data structure

---

## Edge Cases and Testing

### Test Cases to Consider:

1. **Valid Board** (should return true):
```
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
```

2. **Invalid Row** (should return false):
```
[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,["8","9","8",".",".",".",".","6","."]  // Duplicate 8 in row
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
```

3. **Invalid Column** (should return false):
```
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,["5",".",".",".","8",".",".","7","9"]]  // Duplicate 5 in column 0
```

4. **Invalid Box** (should return false):
```
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
```

5. **Empty Board** (should return true):
```
[[".",".",".",".",".",".",".",".","."]
,[".",".",".",".",".",".",".",".","."]
,[".",".",".",".",".",".",".",".","."]
,[".",".",".",".",".",".",".",".","."]
,[".",".",".",".",".",".",".",".","."]
,[".",".",".",".",".",".",".",".","."]
,[".",".",".",".",".",".",".",".","."]
,[".",".",".",".",".",".",".",".","."]
,[".",".",".",".",".",".",".",".","."]]
```

---

## Practice Problems

### Related LeetCode Problems:
1. **Sudoku Solver** (#37) - Hard
   - Extend the validation to actually solve the puzzle
   - Uses backtracking with the same validation logic

2. **Valid Sudoku** (#36) - Medium (current problem)
   - Focus on validation only

3. **Number of Valid Words for Each Puzzle** (#1178) - Hard
   - Uses similar bit manipulation concepts

### Interview Variations:
1. **Validate N×N Sudoku**: Generalize for any size
2. **Count Valid Sudoku Boards**: Count all possible valid configurations
3. **Sudoku with Additional Constraints**: Add diagonal constraints, etc.

---

## Key Takeaways

### Algorithmic Insights:
1. **Single Pass Optimization**: Check all three conditions (row, column, box) in one iteration
2. **Space-Time Trade-off**: Choose between hash sets (simple) and bit manipulation (efficient)
3. **Box Index Formula**: `(row/3)*3 + (col/3)` is crucial for 3×3 box validation

### Implementation Tips:
1. **Early Exit**: Return false immediately when duplicate is found
2. **Character to Integer**: Use `cell - '0'` for conversion
3. **Empty Cell Handling**: Skip '.' characters
4. **Boundary Conditions**: Always validate array bounds

### Common Mistakes:
1. **Forgetting Box Validation**: Many solutions only check rows and columns
2. **Incorrect Box Index**: Wrong formula for calculating 3×3 box index
3. **Character Comparison**: Comparing chars instead of converting to integers
4. **Space Complexity**: Not considering the fixed board size (9×9)

### Performance Optimization:
1. **Bit Manipulation**: Most space-efficient approach
2. **Array vs Hash Set**: Arrays are more cache-friendly
3. **Early Termination**: Stop as soon as invalid condition is found
4. **Memory Layout**: Consider cache locality in data structure choice

---

## Conclusion

The Valid Sudoku problem is an excellent example of:
- **Multiple Solution Approaches**: From brute force to optimized bit manipulation
- **Space-Time Trade-offs**: Different data structures for different priorities
- **Problem Decomposition**: Breaking complex validation into simpler checks
- **Interview Problem Patterns**: Common in technical interviews for testing algorithmic thinking

The key is understanding that we can validate all three conditions (row, column, box) in a single pass through the board, making the solution both efficient and elegant.

**Recommended Approach**: Start with the hash set solution for clarity, then optimize to bit manipulation for production code. 