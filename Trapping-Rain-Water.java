class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int[] leftMax = new int[n], rightMax = new int[n];
        for (int i = 1; i < n; ++i)
            leftMax[i] = Math.max(height[i-1], leftMax[i-1]);
        for (int i = n-2; i >= 0; --i)
            rightMax[i] = Math.max(height[i+1], rightMax[i+1]);

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int waterLevel = Math.min(leftMax[i], rightMax[i]);
            if (waterLevel >= height[i]) ans += waterLevel - height[i];
        }
        return ans;
    }
}

/*
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
MAKE SURE TO SOLVE THIS USING 2 POINTERS TOO. ALSO PLEASE UNDERSTAND THE INTUITION BEHIND THIS CODE AND WHY we DONT CARE ABOUT THE RIGHT MAX (BECAUSE THE HEIGHT DEPENDS ON THE LEFTMAX - IF IT IS < RIGHTMAX).
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


💡 Intuition:
Water can only be trapped *between* taller bars.
At each index, the amount of water depends on:
    - The tallest bar to its LEFT
    - The tallest bar to its RIGHT
The *lower* of these two defines the limiting height of water that can stand above it.

⚙️ Step-by-Step Logic:

1. leftMax[i] = the tallest bar to the LEFT of i
   → Build this array by iterating left → right.
   → leftMax[i] = max(height[i - 1], leftMax[i - 1])

2. rightMax[i] = the tallest bar to the RIGHT of i
   → Build this array by iterating right → left.
   → rightMax[i] = max(height[i + 1], rightMax[i + 1])

3. For each bar i:
   → waterLevel = min(leftMax[i], rightMax[i])
   → If waterLevel > height[i], that means water can be trapped.
   → Add (waterLevel - height[i]) to total.

🏗️ Why it works:
Imagine pouring water across the heights. Water collects wherever the current bar
is shorter than both its left and right boundaries.

🧮 Example:
height = [0,1,0,2,1,0,1,3,2,1,2,1]
→ leftMax  = [0,0,1,1,2,2,2,3,3,3,3,3]
→ rightMax = [3,3,3,3,3,3,3,2,2,2,1,0]
→ Water trapped at each index = min(leftMax, rightMax) - height[i] (if positive)
Total = 6

📈 Complexity:
Time:  O(n) — Single pass for each array + one for total water.
Space: O(n) — For leftMax and rightMax arrays.

🧠 Key Insight:
If you remember just one line:
“Water[i] = min(maxLeft[i], maxRight[i]) - height[i]”

✅ Alternative:
To optimize space, you can solve it using two pointers and O(1) extra space,
but this array-based version is easier to understand and debug.

*/
