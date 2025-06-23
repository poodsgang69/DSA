class Solution {
public:
    vector<int> twoSum(vector<int>& numbers, int target) {
        int i = 0, j = numbers.size() - 1, currSum;
        vector<int> answer(2,0);
        while(true) {
            currSum = numbers[i] + numbers[j];
            if (currSum == target) {
                answer[0] = i+1;
                answer[1] = j+1;
                break;
            } else if (currSum > target) {
                j--;
            } else {
                i++;
            }
        }
        return answer;
    }
    
    // Alternative method using more complex C++ features
    vector<int> twoSumAdvanced(vector<int>& numbers, int target) {
        // Using iterators and STL algorithms
        auto left = numbers.begin();
        auto right = numbers.end() - 1;
        
        // Lambda function to find the pair
        auto findPair = [&]() -> vector<int> {
            while (left < right) {
                int sum = *left + *right;
                if (sum == target) {
                    // Calculate 1-indexed positions using distance
                    return {static_cast<int>(distance(numbers.begin(), left) + 1),
                            static_cast<int>(distance(numbers.begin(), right) + 1)};
                } else if (sum > target) {
                    --right;
                } else {
                    ++left;
                }
            }
            return {}; // Should never reach here given problem constraints
        };
        
        return findPair();
    }
};

/*
Pretty simple greedy solution to the 2 sum where we know the array is sorted so we maintain i and j as 0 and len(numbers).
3 cases: 
    -> if the sum(number[i] + number[j]) = targetSum, we add the indexes + 1 (1 indexed) to the answer array and return
    -> if the sum > target, we have added a larger number than required, so we need to add the next smallest number; hence j--
    -> reverse logic of the above topic applies here, hence i++
As there is always an answer that exists, we break and we return the answer array.
*/

/*
TWO SUM II - INPUT ARRAY IS SORTED ALGORITHM EXPLANATION
========================================================

PROBLEM: Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, 
find two numbers such that they add up to a specific target number. Return the indices of the two 
numbers, index1 and index2, added by one as an integer array [index1, index2].

CONSTRAINTS:
- 2 <= numbers.length <= 3 * 10^4
- -1000 <= numbers[i] <= 1000
- numbers is sorted in non-decreasing order
- -1000 <= target <= 1000
- There is exactly one solution

APPROACH 1: Two-Pointer Technique (Basic)
- Use two pointers: left (start) and right (end)
- Since array is sorted, we can use the sorted property to our advantage
- Move pointers based on comparison with target sum

ALGORITHM LOGIC:
1. Initialize left pointer at start (i=0) and right pointer at end (j=size-1)
2. Calculate current sum = numbers[left] + numbers[right]
3. Three cases:
   - If sum == target: Found the pair! Return indices (1-indexed)
   - If sum > target: Need smaller sum, move right pointer left (j--)
   - If sum < target: Need larger sum, move left pointer right (i++)
4. Continue until we find the pair (guaranteed to exist)

WHY THIS WORKS:
- Array is sorted in ascending order
- If sum > target, we need a smaller number, so we move right pointer left
- If sum < target, we need a larger number, so we move left pointer right
- This approach is optimal because we eliminate half the remaining possibilities each time

APPROACH 2: Advanced C++ Features
- Uses STL iterators instead of indices
- Lambda function for encapsulation
- distance() function to calculate positions
- More idiomatic C++ code

TIME COMPLEXITY: O(n) - we visit each element at most once
SPACE COMPLEXITY: O(1) - only using constant extra space

EDGE CASES HANDLED:
- Array with 2 elements: works normally
- Target is sum of first and last elements: optimal case
- All elements are the same: still works correctly

EXAMPLE TRACE:
numbers = [2,7,11,15], target = 9
i=0, j=3: sum = 2+15 = 17 > 9 → j=2
i=0, j=2: sum = 2+11 = 13 > 9 → j=1  
i=0, j=1: sum = 2+7 = 9 == 9 → return [1,2]

ALTERNATIVE METHODS:
1. Binary Search: For each element, binary search for (target - element) - O(n log n)
2. Hash Map: Works but doesn't use sorted property - O(n) time and space
3. Two Pointers: Most efficient for sorted arrays - O(n) time, O(1) space

WHY TWO POINTERS IS OPTIMAL:
- Takes advantage of sorted property
- No extra space needed
- Single pass through array
- Each iteration eliminates one element from consideration
*/