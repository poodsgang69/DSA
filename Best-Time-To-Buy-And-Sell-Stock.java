class Solution {
    public int maxProfit(int[] prices) {
        int currBuyPrice = prices[0];
        int result = 0;
        for(int price : prices) {
            if(price < currBuyPrice) currBuyPrice = price;
            else {
                int profit = price - currBuyPrice;
                result = Math.max(result, profit);
            }
        }
        return result;
    }

    public int maxProfitStreams(int[] prices) {
        AtomicInteger minPrice = new AtomicInteger(prices[0]);
        AtomicInteger maxProfit = new AtomicInteger(0);

        Arrays.stream(prices).skip(1)
            .forEach(price -> {
                minPrice.set(
                    Math.min(minPrice.get(), price)
                );
                maxProfit.set(
                    Math.max(maxProfit.get(), price - minPrice.get())
                );
            });

        return maxProfit.get();
    }
}

/*
------------------------------------
ALGORITHM INTUITION (FOR FUTURE ME)
------------------------------------

Problem:
- You want to buy a stock once & sell it later at a higher price.
- Find the maximum possible profit.

Key Insight:
- The best selling price depends on the minimum price seen *before* it.

Approach:
1. Traverse the array once.
2. Track:
   - The lowest stock price seen so far → best buying point.
   - The maximum profit if we sold at today's price.

Flow:
- For each price:
    ✓ Update minPrice if current is cheaper.
    ✓ Otherwise compute currentProfit = price - minPrice.
    ✓ Track the best maxProfit so far.

Why This Works:
- It keeps the timeline correct: buying always happens before selling.
- We sweep once → O(n), best possible time complexity.
- Constant extra space → O(1).
*/