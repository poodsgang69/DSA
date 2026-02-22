class Solution {
    public int binaryGap2(int n) {
        
        // First check if the number is a power of 2
        if( n > 0 && (n & (n - 1)) == 0 ) {
            return 0;
        }
        // normal numbers - every number has atleast 2 1's in its binary 
        String binary = Integer.toBinaryString(n);
        int[] result = new int[binary.length()];

        for (int idx = 0; idx < binary.length(); idx++) {
            result[idx] = binary.charAt(idx) - '0';
        }

        System.out.println(Arrays.toString(result));

        int i = 0, j = i+1;
        int dist = 0, maxDist = 0;

        while(j < result.length) {
            if(result[j] == 0) {
                j++;
            } else {
                // calc the distance
                dist = Math.abs(j - i);
                maxDist = Math.max(dist, maxDist);
                i = j;
                j = i+1;
            }
        }

        return maxDist;
    }

    public int binaryGap(int n) {
        int last = -1;      // position of previous 1-bit
        int pos = 0;        // current bit position (LSB = 0)
        int max = 0;

        while (n != 0) {
            if ((n & 1) == 1) {
                if (last != -1) {
                    max = Math.max(max, pos - last);
                }
                last = pos;
            }
            n >>>= 1; // unsigned shift right (safe even if n is negative)
            pos++;
        }
        return max;
    }
}
