#include <iostream>
#include <algorithm>
#include <cstddef>

using namespace std;

std::ostream& operator<<(std::ostream& os,
                         const std::vector<std::pair<int, int>>& vec) {
    os << "[";
    for (size_t i = 0; i < vec.size(); ++i) {
        os << "(" << vec[i].first << ", " << vec[i].second << ")";
        if (i != vec.size() - 1) {
            os << ", ";
        }
    }
    os << "]";
    return os;
}

class Solution {
public:
    // Naive solution will work but will give TLE for huge cases as the
    // TimeComplexity is O(N^2)
    int maxAreaNaive(vector<int>& height) {
        // Naive
        int area = 0;
        for (int h1 = 0; h1 < height.size() - 1; h1++) {
            for (int h2 = h1 + 1; h2 < height.size(); h2++) {
                if (h1 == h2)
                    continue;
                area = std::max(area,
                                (h2 - h1) * std::min(height[h1], height[h2]));
            }
        }

        return area;
    }

    // Better Solution
    int maxAreaTried(vector<int>& height) {
        int size = height.size(), max = -1, maxIdx = -1;
        vector<pair<int, int>> fp(size, {-1, -1}), bp(size, {-1, -1});

        // calculate forward arr
        for (int i = 0; i < size; i++) {
            if (height[i] > max) {
                maxIdx = i;
                max = height[i];
            }
            std::pair<int, int> currPair{max, maxIdx};
            fp[i] = currPair;
        }
        max = -1;
        // calculate backward arr
        for (int j = size - 1; j >= 0; j--) {
            if (height[j] > max) {
                maxIdx = j;
                max = height[j];
            }
            std::pair<int, int> currPair{max, maxIdx};
            bp[j] = currPair;
        }

        cout << fp << endl;
        cout << bp << endl;

        int maxArea = -1;

        for (int i = 0; i < size; i++) {
            maxArea = std::max(maxArea, (bp[i].second - fp[i].second) *
                                            std::min(bp[i].first, fp[i].first));
        }
        return maxArea;
    }
    int maxArea(vector<int>& height) {
        int left = 0;
        int right = height.size()-1;
        int maxArea = -1;

        while (left <= right) {
            maxArea = max(maxArea, (right - left)*(min(height[right], height[left])));
            //height adjustment
            if (height[left] < height[right]) left++;
            else right--;
        }

        return maxArea;
    }
};

/*
    Write the comments explaining the code later. 

basic idea is when we use 2 pointers, we keep checking area and when we find the area for a particular
set of left and right, we also check which of the height is lesser. this indicates that the height part of the area
is being limited by that (min(height.left, height.right))

so we move left ++ in hopes of finding a height higher than the current to increase area. 
but as the width decreases per shift in left or right, we maintain a maxArea variable. 

Finally we end up with the max area we can make. If they had asked us to return the indices, would have been harder.
think aboit this.  
*/