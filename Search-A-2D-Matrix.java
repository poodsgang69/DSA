class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        if(target < matrix[0][0] || target > matrix[m-1][n-1]) return false;
        for(int i = 0 ; i < m ; ++i) {
            if(target <= matrix[i][n-1]) return binarySearch(matrix[i], target, 0, n);
        }
        return true;
    }

    public boolean binarySearch(int[] sortedArray, int key, int low, int high) {
        int index = -1;
        
        while (low <= high) {
            int mid = low  + ((high - low) / 2);
            if (sortedArray[mid] < key) {
                low = mid + 1;
            } else if (sortedArray[mid] > key) {
                high = mid - 1;
            } else if (sortedArray[mid] == key) {
                index = mid;
                break;
            }
        }
        return index == -1 ? false : true;
    }
}
