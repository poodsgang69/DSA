class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        currentMinPrice = prices[0]
        maxProfit = 0

        # for i in range(1, len(prices)):
        #     #calculate the current cost
        #     currentCost = prices[i] - currentMinPrice
        #     maxProfit = max(currentCost, maxProfit)
        #     currentMinPrice = min(currentMinPrice, prices[i])

        for price in prices[0:]:
            currentMinPrice = min(currentMinPrice, price)
            maxProfit = max(maxProfit, price - currentMinPrice)
        
        return maxProfit
    
'''
Pretty strightforward. 
assuming the first cost is the minimum cost we encountered till now and starting with 0 profit, we start comparing from the 2nd element trying to find the current minimum price we can possibly achieve
parallely, we also keep calculating the maximum profit while we go to keep a check of the best profit we encounter so far. 
Eventually we come across a combination where we select the minimum element and the maximum element after it which we consider our best profit. 
'''