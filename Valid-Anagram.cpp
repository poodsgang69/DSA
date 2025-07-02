class Solution {
public:
    bool isAnagramNormal(string s, string t) {
        if (s.size() != t.size()) return false;

        vector<int> charMap(26, 0);
        for (char c : s) charMap[c - 'a']++;
        for (char c : t) {
            if (--charMap[c - 'a'] < 0) return false;
        }
        return true;
    }

    bool isAnagram(string s, string t) {
        if (s.size() != t.size()) return false;

        unordered_map<char, int> charCount;
        for (char c : s) charCount[c]++;
        for (char c : t) {
            if (--charCount[c] < 0) return false;
        }
        return true;
    }
};

/*
    Pretty straightforward solution where we use initially an array of length 26 to store the count of each character in s.
    Then we iterate over the t string and decrement the count of each character.
    if at any point the count of a character is less than 0, we return false because that means that the character in t is not present in s.

    We make an improvement to the solution by using a map to store the count of each character in s because we can handle all unicode characters.
    it follows the same logic as the first solution but is more efficient because it uses a map instead of an array.
*/