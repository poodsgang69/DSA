class Solution {
public:
    int canCompleteCircuit(vector<int>& gas, vector<int>& cost) {
        int start = 0;
        int n = gas.size(), gasAvailable= 0, gasCost = 0, gasSoFar = 0;
        for(int i = 0; i < n; i++) {
            gasAvailable += gas[i];
            gasCost += cost[i];
            gasSoFar += (gas[i] - cost[i]);
            if(gasSoFar < 0) {
                start = i + 1;
                gasSoFar = 0;
            }
        }
        if(gasCost > gasAvailable)
            return -1;
        return start;
    }
};

/*
Try to undetstand the intuition behind this. 

I got the algo till the gas and gas_left concept. Try to understand the greedy concept as to why we need to circle the entire array and how after we figure out one point we can say that we can we can circle till the starting index. 
*/
