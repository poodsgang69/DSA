#include <climits>
class Solution {
public:
    int findLucky(vector<int>& arr) {
        unordered_map<int, int> map;
        int largest = INT_MIN;
        bool found = false;
        for (auto& n : arr) map[n]++;
        for (auto&n : arr) {
            if (map[n] == n) {
                found = true;
                largest = std::max(largest, n);
            }
        }
        if (found) return largest;
        return -1;
    }
};