class Solution {

    // Optimized two-pointer approach to solve the Container With Most Water problem
    public int maxArea1(int[] heights) {
        int i = 0, j = heights.length - 1;  // Initialize two pointers at both ends of the array
        int area = 0;  // Store the maximum area found so far

        while(i < j) {
            // Calculate the current area between the two lines
            // Width = (j - i), Height = min of the two lines
            area = Math.max(area, (j - i) * Math.min(heights[j], heights[i]));

            // Move the pointer with the shorter line inward
            // This is because moving the taller line won't help increase area (height is bounded by the shorter one)
            if(heights[i] < heights[j]) i++;
            else j--;
        }

        // Return the maximum area found
        return area;
    }

    // Slightly different variant with skipping of duplicate heights to optimize pointer moves
    public int maxArea(int[] height) {
        int area = 0;  // Track the maximum area
        int left = 0;  // Left pointer starts at beginning
        int right = height.length - 1;  // Right pointer starts at the end

        while (left != right) {
            // Identify the limiting height (shorter of the two lines)
            int minHeight = Math.min(height[left], height[right]);

            // Calculate area for this width and height, update if it's larger than previous max
            if (minHeight * (right - left) > area) {
                area = (minHeight * (right - left));
            }

            // Move the left pointer forward past any lines shorter than or equal to the current minHeight
            // These heights can't form a larger container, so skip them
            while (left < right && height[left] <= minHeight) { left++; }

            // Similarly, move the right pointer backward past any lines not taller than minHeight
            while (right > left && height[right] <= minHeight) { right--; }
        }

        // Return the maximum area found during the scan
        return area;
    }
}
