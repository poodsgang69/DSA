class Solution {
public:
    vector<int> productExceptSelfNormal(vector<int>& nums) {
        int count = 0, product = 1;
        vector<int> ans;
        //count the number of 0's
        for (int ele : nums) {
            if (ele == 0) {
                count++;
            }
            if (count > 1) return vector<int>( nums.size(), 0);
        }

        if (count == 1) {
            int product_without_zero = 1;
            for(int ele : nums) {
                if (ele != 0) product_without_zero *= ele;
            }
            for(int ele : nums) {
                if (ele == 0) {
                    ans.push_back(product_without_zero);
                } else {
                    ans.push_back(0);
                }
            }
        } else {
            for(int ele : nums) {
                product *= ele;
            }
            for(int ele : nums) {
                ans.push_back(product/ele);
            }
        }
        return ans;
    }

    vector<int> productExceptSelfFpBp(vector<int>& nums) {
        ios_base::sync_with_stdio(false);
        int size = nums.size();
        vector<int> fp(size, 1), bp(size, 1), ans(size, 0);
        //populate the fp
        for( int i = 1 ; i < size ; i++ ) {
            fp[i] = fp[i-1] * nums[i-1];
        }
        //populate the bp
        for ( int i = size - 2 ; i >= 0 ; i-- ) {
            bp[i] = bp[i+1] * nums[i+1];
        }

        //calculate the product of both and return
        for ( int i = 0 ; i < size ; i++ ) {
            ans[i] = fp[i] * bp[i];
        }

        return ans;
    }

    vector<int> productExceptSelf(vector<int>& nums) {
        ios_base::sync_with_stdio(false);
        int size = nums.size(), curr_prod = 1;
        vector<int> ans(size, 1);

        for (int i = 0 ; i < size ; i++ ) {
            ans[i] *= curr_prod;
            curr_prod *= nums[i];
        }

        curr_prod = 1;
        for ( int i = size - 1 ; i >= 0 ; i-- ) {
            ans[i] *= curr_prod;
            curr_prod *= nums[i];
        }

        return ans;
    }
};

/*
Link: https://leetcode.com/problems/product-of-array-except-self/description/?envType=study-plan-v2&envId=top-interview-150

first solution:
Its simple. Count the number of 0's. If they are more than 1, that means any product except self will contain the other 0 resulting in the whole array having 0's. 
if the 0 count == 1, that means only 1 element is 0, and only at that index where we find 0 in the nums array, we will have a number having value of the product of the entire array except the 0 (product_except_0) and other places will be filled with 0's. 
and the last case is when there are no 0s where we simply calculate the product of the entire array and we populate the answer array with product / element (at that index).

second solution:
intuitive in the way that we consider a forward product and a backweard product. (fp and bp)
the idea behind this is that we need to have to find a way to store the products of each element BEFORE and AFTER a certain index cuz we dont want to include the element AT the INDEX. which is why we consider the previous index (for fp) and backward index (for bp)
simply put, at each index in FP, it contains the product of each element BEFORE that index and vice versa for the bp (product of each element AFTER that index)
The answer will be the product of the 2 arrays at each index respectively cuz finally we want product except self which is being achieved by the bp and fp (product BEFORE x product AFTER - both of which DONT contain the current elememnt at the index being calculated.)

third solution:
difficult to explain here but if you trace the execution of the second soltion, you will realise that it is iterating over the same arry 2 times front and back with repeated calcs. Just trace the answer and you will understand why we are doing this. 
curr_prod gets updated after every iteration with the prod of itself and the curr_number (nums[i]) and it is used to populate the next element in the ans[i]. 
Refer the last pages of your personal diary (aniketh) to see a small calc of the above. [nums[i-2]*nums[i-1]*nums[i]*prod]
*/