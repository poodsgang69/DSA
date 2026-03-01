class Solution {
    public int minPartitions(String n) {
        int max = Integer.MIN_VALUE;
        for(char c: n.toCharArray()) {
            max = Math.max(max, c - '0');
            if(max == 9) return 9;
        }
        return max;
    }
}

/*
LTC Daily March 1
*/
