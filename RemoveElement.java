class Solution {
    public int removeElement(int[] nums, int val) {
        List<Integer> notKValues = new ArrayList<>();
        for (int num : nums) {
            if (num != val) {
                notKValues.add(num);
            }
        }

        for(int i = 0 ; i < notKValues.size() ; ++i ) {
            nums[i] = notKValues.get(i);
        }

        return notKValues.size();
    }
}

/*
StraightForward solution where we initially scan over the list of numbers and copy the non val (num[i] != val) values into an array called notKvalues and then
we simply copy those values back to the nums array as we need to modify that. The k (the number of numbers not equal to val) is simply the lenght of the 
notKValues array.

Time Complexity: O(N) (Average) -> O(N) + O(len(notKValues)) [Actual]
Space Complexity: O(N) (Average) -> O(len(notKValues)) [Actual]
*/
