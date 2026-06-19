class Solution {
    public int largestAltitude(int[] gain) {
        int n = gain.length;
        int[] res = new int[n+1];
        res[0] = 0;
        int maxGain = 0;
        
        for(int i = 0; i < gain.length ; ++i){
            res[i+1] = res[i]+gain[i];
            maxGain = Math.max(res[i+1], maxGain);
        }
        return maxGain;
    }
}