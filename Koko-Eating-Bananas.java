class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int minSpeed = 1;
        int maxSpeed = 0;
        // int maxSpeed = Arrays.stream(piles).max().getAsInt();

        for(int i : piles){
            maxSpeed = Math.max(maxSpeed,i);
        }
        int currBestTime = -1;

        // binary search on the lower -> higher speed
        while(minSpeed <= maxSpeed) {
            int mid = (minSpeed + maxSpeed)/2; 
            double currTotalTime = 0;
            currTotalTime = timeTaken(piles,mid);
            // if time taken is faster, update the currentFastestTime and move on to a lower speed (as we need to find the lowest int)
            if(currTotalTime <= h) {
                currBestTime = mid;
                maxSpeed = mid - 1;
            } else {
                minSpeed = mid + 1;
            }
        }

        return currBestTime;
    }

    long timeTaken(int[] piles , int k){
        long totalHour = 0;
        for(int i : piles ){
            totalHour+= (i+k-1)/k;
        }
        return totalHour;
    }
}

/*
We run a binary search between the lowest speed (1) and the highest (arbitrary) speed (max value in the list of piles). 
If we find a speed which results in a time > h , then we know that we need to search a faster speed (because the eating speed is so slow, it results in a longer time to eat) -> remember we are running binary search on the set of possible SPEEDS --> hence we need to go mid + 1 to find
a faster speed which will hopefully give us an answer <= the time contraint we have.

If we find a speed which makes us eat faster ( i.e. < h ) then we save that value as the current fastest speed and then as we need to find the MINIMUM value that satisfies our time constraint, we reduce our right to mid - 1 (we need to find the minimum speed that makes our total time to eat
all the bananas still < the provided time threshold).

Time Complexity :- O(logN)
Space Complexity :- O(1) 
*/
