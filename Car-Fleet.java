class Solution {
    public int carFleetNormal(int target, int[] position, int[] speed) {
        List<int[]> carPairs = new ArrayList<int[]>();
        Deque<Double> stack = new ArrayDeque<Double>();

        for(int i = 0 ; i < position.length ; ++i) {
            carPairs.add(new int[]{position[i], speed[i]});
        }

        carPairs.sort((a, b) -> Integer.compare(b[0], a[0]));

        for(int[] pair : carPairs) {
            double remainingDistance = target - pair[0];
            double time = (double) remainingDistance/pair[1];

            if(stack.isEmpty() || time > stack.peek()) {
                stack.push(remainingDistance/pair[1]);
            }
        }

        return stack.size();
    }

    public int carFleet(int target, int[] position, int[] speed) {
        float[] remTimes = new float[target + 1];
        for(int i = 0 ; i < position.length ; ++i) {
            remTimes[position[i]] = (float) (target - position[i]) / speed[i];
        }
        
        float miniTime = 0;
        int fleetCount = 0;
        for(int i = remTimes.length - 1 ; i >= 0 ; --i) {
            if (remTimes[i] == 0) continue;
            else if(remTimes[i] > miniTime) {
                miniTime = remTimes[i];
                fleetCount++;
            } 
        }
        return fleetCount;
    }
}



/*
Intuition (carFleetNormal):
- Each car’s “time to reach target” = (target - position) / speed.
- Sort cars by position descending (closest to target first). Walk left→right.
- Maintain a stack of fleet times (monotonic non-decreasing from top to bottom):
    - If current car’s time > top, it can’t catch the fleet ahead → new fleet (push time).
    - Else it catches up and merges → ignore (don’t push).
- The stack size at the end equals the number of fleets.
Why this works: Once a car has a time ≤ the fleet in front, it will never pass it; fleets only merge forward, never split.
*/

/*
Intuition (carFleet):
- Same idea as above, but avoid explicit sorting by using a “time-at-position” array.
- Store time-to-target at remTimes[position]; then scan positions from target down to 0.
- Maintain a running maxTime (monotonic, non-decreasing as we move backward):
    - If remTimes[i] > maxTime, this car cannot catch the fleet ahead → new fleet, update maxTime.
    - Else it merges into the fleet ahead.
- This mimics sorting by position and doing a single backward pass (like a monotonic stack),
  but uses direct indexing (counting-sort flavor) to achieve the same effect.
  (Trade-off: O(target) space; good if target is reasonably bounded.)
*/
