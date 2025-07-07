class Solution {
public:
    void merge(vector<int>& nums1, int m, vector<int>& nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;

        while( j >= 0 ) {
            if ( i >= 0 && nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
                k--;
            } else {
                nums1[k] = nums2[j];
                j--;
                k--;
            }
        }
    }
};

/*
    Basically a merge sort algorithm.
    We start from the end of the array and compare the last element of the two arrays.
    We then place the larger element at the end of the first array.
    We then decrement the index of the array from which we took the element.
    We then decrement the index of the array to which we are placing the element.
    We then decrement the index of the merged array.
    We then repeat the process until we have placed all the elements of the second array in the first array.
    We then return the merged array.

    Time Complexity: O(m + n)
    Space Complexity: O(1)
*/