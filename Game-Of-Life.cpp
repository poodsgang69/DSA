class Solution {
public:
    // Game of Life using directions array for neighbor counting
    // This approach works for all cells (center, edge, corner) because:
    // - It defines all 8 possible neighbor directions in a directions array.
    // - For each cell, it iterates over all 8 directions and checks if the neighbor indices are within bounds.
    // - Only valid neighbors (within the board) are counted, so there is no out-of-bounds access.
    // - The board is copied at the start, so updates do not affect neighbor calculations for other cells in the same iteration.
    // - This ensures that every cell, regardless of its position, is updated according to the correct number of live neighbors.
    void gameOfLifeDirections(vector<vector<int>>& board) {
        int m = board.size(), n = board[0].size();
        vector<vector<int>> copy = board; // Use a copy for reference

        // All 8 possible directions: top-left, top, top-right, left, right, bottom-left, bottom, bottom-right
        int dirs[8][2] = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int live = 0;
                // Check all 8 neighbors
                for (auto& d : dirs) {
                    int ni = i + d[0], nj = j + d[1];
                    // Only count if neighbor is within bounds and alive
                    if (ni >= 0 && ni < m && nj >= 0 && nj < n && copy[ni][nj] == 1)
                        ++live;
                }
                // Apply Game of Life rules:
                // 1. Any live cell with fewer than two or more than three live neighbors dies.
                // 2. Any dead cell with exactly three live neighbors becomes alive.
                if (copy[i][j] == 1) {
                    if (live < 2 || live > 3)
                        board[i][j] = 0;
                } else {
                    if (live == 3)
                        board[i][j] = 1;
                }
            }
        }
    }

    // Same logic as above, but with different variable names and structure
    void gameOfLife(vector<vector<int>>& board) {
        int m = board.size(), n = board[0].size();
        vector<vector<int>> boardCopy = board;
        int directions[8][2] = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
        };
        for (int row = 0; row < m; ++row) {
            for (int col = 0; col < n; ++col) {
                int liveNeighbors = 0;
                // Check all 8 neighbors
                for (auto& dir : directions) {
                    int neighborRow = row + dir[0], neighborCol = col + dir[1];
                    // Only count if neighbor is within bounds and alive
                    if (neighborRow >= 0 && neighborRow < m && neighborCol >= 0 && neighborCol < n && boardCopy[neighborRow][neighborCol] == 1)
                        ++liveNeighbors;
                }
                // Apply Game of Life rules:
                // 1. Any live cell with fewer than two or more than three live neighbors dies.
                // 2. Any dead cell with exactly three live neighbors becomes alive.
                if (boardCopy[row][col] == 1) {
                    if (liveNeighbors < 2 || liveNeighbors > 3)
                        board[row][col] = 0;
                } else {
                    if (liveNeighbors == 3)
                        board[row][col] = 1;
                }
            }
        }
    }

    void gameOfLifeSolver(int i, int j, vector<vector<int>>& board, int m, int n, vector<vector<int>>& boardCopy) {
        int liveNeighborCount;
        liveNeighborCount = countOfLiveNeighbors(i, j, board, m, n, boardCopy);
        if (board[i][j] == 1) {
            if (liveNeighborCount < 2) {
                // that cell dies
                board[i][j] = 0;
            } else if (liveNeighborCount > 3) {
                // cell dies
                board[i][j] = 0;
            }
        } else {
            if (liveNeighborCount == 3) {
                // make that dead cell live
                board[i][j] = 1;
            }
        }
    }

    int countOfLiveNeighbors(int i, int j, vector<vector<int>>& board, int m, int n, vector<vector<int>>& boardCopy) {
        int count = 0;
        int directions[8][2] = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
        };
        for (int d = 0; d < 8; ++d) {
            int ni = i + directions[d][0];
            int nj = j + directions[d][1];
            if (ni >= 0 && ni < m && nj >= 0 && nj < n && boardCopy[ni][nj] == 1) {
                ++count;
            }
        }
        return count;
    }
};