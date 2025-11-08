class Solution {
    public int strStr2(String haystack, String needle) {
        for ( int i = 0 ; i <= haystack.length() - needle.length() ; ++i ) {
            String temp = "";
            temp = haystack.substring(i, i+needle.length());
            if(temp.equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    public int strStr(String haystack, String needle) {
        List<Integer> lpsArray = getLpsArray(needle);
        int idx = findFirstOcc(lpsArray, haystack, needle);
        return idx;
    }

    private List<Integer> getLpsArray(String needle) {
        int left = 0, right = 1;
        List<Integer> lps = new ArrayList<>(Collections.nCopies(needle.length(), 0));
        while(right < needle.length()) {
            if(needle.charAt(left) == needle.charAt(right)) {
                lps.set(right, left + 1);
                left++;
                right++;
            } else {
                if (left == 0) {
                    lps.set(right, 0);
                    right++;
                } else {
                    left = lps.get(left - 1);
                }
            }
        }

        return lps;
    }

    private int findFirstOcc(List<Integer> lps, String haystack, String needle) {
        int i = 0 , j = 0;
        while(i < haystack.length()) {
            if(haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                if(j == 0) {
                    i++;
                } else {
                    j = lps.get(j - 1);
                }
            }

            if ( j == needle.length() ) {
                return i - needle.length();
            }
        }

        return -1;
    }
}



/* 
Too hard to explain here. Please see Abdul bari or NeetCode video on KMP Algorithm to understand. 
*/
