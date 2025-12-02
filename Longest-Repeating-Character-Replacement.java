class Solution {
    public int characterReplacement(String s, int k) {
        int[] freq = new int[26];
        int l = 0, maxFreq = 0, maxLen = 0;

        for (int r = 0; r < s.length(); r++) {
            int idx = s.charAt(r) - 'A';
            freq[idx]++;
            maxFreq = Math.max(maxFreq, freq[idx]);   // only increase

            while ((r - l + 1) - maxFreq > k) {
                freq[s.charAt(l) - 'A']--;
                l++;
                // do NOT recompute/decrease maxFreq here
            }

            maxLen = Math.max(maxLen, r - l + 1);
        }

        return maxLen;
    }
}

/*



Intuition:
- Scan the array with 2 pointers: left and right
- Store the frequency of each character
- Compute the replacement cost: cells count between left and right pointers - the highest frequency
- if the replacement cost <= k: update longest string size
- if the replacement cost > k: decrease frequency of character at left pointer; increase left pointer and repeat
- - - Since we are looking for the longest sub-string, we don't need to shrink the sliding window by more than 1 character:
- - - When we reach a window of size W, we know that our target window size is higher or equal to the current one ( >= W).
- - - Therefore, we could continue sliding with a window of W cells until we find a larger window > W.

 0   1   2   3   4   5   6   7   8   9   10  11  12
 C   A   B   A   A   A   A   B   B   B   B   B   A,  k,  Replacement Cost,    max len
l^r                                                  2   1 - 1 = 0 <=k           1    => increase r
l^  ^r                                               2   2 - 1 = 1 <=k           2    => increase r
l^       ^r                                          2   3 - 1 = 2 <=k           3    => increase r
l^           ^r                                      2   4 - 2 = 2 <=k           4    => increase r
l^               ^r                                  2   5 - 3 = 2 <=k           5    => increase r
l^                   ^r                              2   6 - 4 = 2 <=k           6    => increase r
l^                      r^                           2   7 - 5 = 2 <=k           7    => increase r
l^                          r^                       2   8 - 5 = 3 > k           7    => increase l,r
    l^                          r^                   2   8 - 5 = 3 > k           7    => increase l,r
        l^                           r^              2   8 - 4 = 4 > k           7    => increase l,r
            l^                           r^          2   8 - 4 = 4 > k           7    => increase l,r
                l^                           r^      2   8 - 5 = 3 > k           7    => increase l,r
                     l^                          r^  2   8 - 4 = 4 > k           7    => STOP

Tests: you may want to:
- to use all test cases above
- to add test cases based on your code (coverage): test your code as soon as you can
- to test edge & special cases:

    An empty string,
    A string with 1 character, A
    A string with same character: AAAAA
    A string that containing distinct characters: ABCDEFGHI

Analysis:
- Time Complexity: O(26 |s|) = O(|s|)
- Space Complexity: O(26) = O(1)


*/
