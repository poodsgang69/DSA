class Solution {
public:
    int possibleStringCount(string word) {
        ios_base::sync_with_stdio(0);
        cin.tie(NULL);
        unordered_map<char, int> map;
        int count = 1;
        char prevChar = word[0] ;
        for ( auto& c : word ) {
            if ( c == prevChar ) {
                map[c]++;
            } else {
                count += map[prevChar] - 1;
                map[prevChar] = 0;
                map[c]++;
                prevChar = c;
            }
            
        }
        for (auto& p : map) {
            if (p.second > 0) count += p.second - 1;
        }

        return count;
    }
};


/*
    LeetCode Problem Reference: "Find the Original String From the Typed String I"

    Problem Summary:
    Given a string 'word' that may contain consecutive repeated characters (as if a key was held down and repeated), 
    determine how many possible original strings could have been typed, where each group of consecutive identical 
    characters in 'word' could have originated from a single character in the original string.

    Solution Explanation:
    The function possibleStringCount counts the number of possible original strings by considering that each group 
    of consecutive identical characters in 'word' could have been typed as a single character in the original string. 
    For example, "aabbcc" could have originated from "abc".

    The approach is:
    - Iterate through the string, tracking when the character changes.
    - Each time the character changes, increment a counter (since this marks a new character in the original string).
    - The answer is the number of such groups (i.e., the number of times the character changes, plus one for the first group).

    Example:
    For word = "aabbcc", the function will return 3, since the original could be "abc".
*/