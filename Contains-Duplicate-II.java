class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, List<Integer>> hashMap = new HashMap<>();
        for(int i = 0 ; i < nums.length ; ++i) {
            hashMap.computeIfAbsent(nums[i], ele -> new ArrayList<>()).add(i);
        }
        for (Map.Entry<Integer, List<Integer>> entry : hashMap.entrySet()) {
            Integer num = entry.getKey();
            List<Integer> indices = entry.getValue();
            
            if(indices.size() < 2) continue;
            for (int i = 0 ; i < indices.size() - 1 ; ++i) {
                // abs(i-j) <= k logic here
                if(indices.get(i+1) - indices.get(i) <= k) return true;
            }
        }

        return false;
    }
}