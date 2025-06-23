class Solution {
public:
    bool isSubsequence(string s, string t) {
        // Simple 2 pointer approach
        int i = 0 , j = 0;

        while (i < s.size() && j < t.size()) {
            if (s[i] == t[j]) {
                i++;
                j++;
            } else {
                j++;
            }
        }

        return i == s.size();
    }
};

/*
IS SUBSEQUENCE ALGORITHM EXPLANATION
====================================

PROBLEM: Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
A subsequence of a string is a new string generated from the original string with some characters 
deleted without changing the relative order of the remaining characters.

EXAMPLE: s = "abc", t = "ahbgdc" → true (because "abc" can be found in "ahbgdc" in order)

APPROACH: Two-Pointer Technique
- Use two pointers: i (for string s) and j (for string t)
- Pointer i tracks our progress through the subsequence we're looking for
- Pointer j scans through the target string t

ALGORITHM LOGIC:
1. Initialize both pointers to 0
2. While both pointers are within bounds:
   - If characters match (s[i] == t[j]): 
     * Move both pointers forward (found a match!)
   - If characters don't match:
     * Only move j forward (skip this character in t)
3. Check if we've processed all characters in s (i == s.size())

WHY THIS WORKS:
- We only increment i when we find a match
- We always increment j to scan through t
- If i reaches the end of s, we've found all characters in order
- If j reaches the end of t before i reaches end of s, we couldn't find the subsequence

TIME COMPLEXITY: O(n) where n is the length of string t
SPACE COMPLEXITY: O(1) - only using two integer variables

EDGE CASES HANDLED:
- Empty s: returns true (empty string is subsequence of any string)
- Empty t: returns false (unless s is also empty)
- s longer than t: returns false (impossible to find subsequence)

EXAMPLE TRACE:
s = "abc", t = "ahbgdc"
i=0, j=0: s[0]='a' == t[0]='a' → i=1, j=1
i=1, j=1: s[1]='b' != t[1]='h' → i=1, j=2  
i=1, j=2: s[1]='b' == t[2]='b' → i=2, j=3
i=2, j=3: s[2]='c' != t[3]='g' → i=2, j=4
i=2, j=4: s[2]='c' != t[4]='d' → i=2, j=5
i=2, j=5: s[2]='c' == t[5]='c' → i=3, j=6
i=3 == s.size()=3 → return true
*/