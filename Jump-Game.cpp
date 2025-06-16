class Solution {
public:
    bool canJump(vector<int>& nums) {
        int range = 0;
        int siz = nums.size();
        for( int i = 0 ; i < siz ; i++ ) {
            if (i > range) {
                return false;
            }
            range = std::max(range, i + nums[i]);
            if (range >= siz-1) {
                return true;
            }
        }
        return false;
    }
};

/*
Link: https://leetcode.com/problems/jump-game/?envType=study-plan-v2&envId=top-interview-150

But its basically a greedy algo which searches whether any given index has a max_range > curr_max_range or in other words max_range = max(max_range [symbolizes the current range upto which i can go at max], i + range[i])
here the i + range[i] symbolizes the reach i have from the current index i am standing (as the value at that index is the jump length. so at any index i my range will be i+range[i])

if the range exceeds the length of the array, that means i have greedily found a solution where i can reach the end or further without needing to calculate another max_range (just take that jump without needing to put leg down somewhere and recalculating my jump distance)
Otherwise, if we find a location where my current location > max_range, i.e out of bounds, means i am standing at a location which is greater than the max_reach i have calculated prior and it has not been changed in any calculation i did while reaching this index 
this case is impossible, hence we return false. this is because if there was a max range before we reached that so called impossible index, it would have updated the max_reach and that would mean out condition (i > max_range) would have been false and we woult not have been at an inpossible index. 
By theory of contradiction , if max_range got updated before we reached that index, we cannot say it is impossible for that index.
we can only get to an impossible index if max_range was never updated and we reached somewhere we cannot physically reach (i.e index > my max jump length or max_range)
*/