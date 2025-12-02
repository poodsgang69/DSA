class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] need = new int[26];
        int[] window = new int[26];

        for (char c : s1.toCharArray()) need[c - 'a']++;

        int m = s1.length();

        for (int i = 0; i < s2.length(); i++) {
            window[s2.charAt(i) - 'a']++;

            // Maintain window size == m
            if (i >= m) {
                window[s2.charAt(i - m) - 'a']--;
            }

            // Compare frequency arrays in O(26)
            if (matches(need, window)) return true;
        }

        return false;
    }

    private boolean matches(int[] n, int[] w){
        for(int i=0;i<26;++i){
            if(n[i]!=w[i]) return false;
        }
        return true;
    }
}