/*
Reverse-Words-In-String.cpp
--------------------------
This program demonstrates how to reverse the words in a string in-place using C++.
It includes two approaches:
  1. Solution: Cleans spaces, reverses the whole string, then reverses each word.
  2. Solution2: Single-pass, in-place reversal and cleaning.

Author: (Your Name)
*/

#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

/**
 * Class Solution
 * Implements the approach of cleaning spaces, reversing the whole string,
 * and then reversing each word to achieve the desired result.
 */
class Solution {
public:
    /**
     * Reverses the words in a string in-place.
     * @param s The input string.
     * @return The string with words reversed and extra spaces removed.
     */
    string reverseWords(string s) {
        // Step 1: Remove extra spaces and trim
        string cleaned = cleanSpaces(s);
        
        // Step 2: Reverse the entire string
        reverse(cleaned.begin(), cleaned.end());
        
        // Step 3: Reverse each word individually
        reverseWordsInPlace(cleaned);
        
        return cleaned;
    }
    
private:
    /**
     * Removes leading, trailing, and extra spaces between words.
     * @param s The input string.
     * @return A cleaned string with single spaces between words.
     */
    string cleanSpaces(string s) {
        string result;
        int i = 0;
        
        // Skip leading spaces
        while (i < s.length() && s[i] == ' ') i++;
        
        // Process the rest of the string
        while (i < s.length()) {
            if (s[i] != ' ') {
                result += s[i];
            } else {
                // Add only one space between words
                if (result.back() != ' ') {
                    result += ' ';
                }
            }
            i++;
        }
        
        // Remove trailing space if exists
        if (!result.empty() && result.back() == ' ') {
            result.pop_back();
        }
        
        return result;
    }
    
    /**
     * Reverses each word in the string in-place.
     * @param s The string to process (by reference).
     */
    void reverseWordsInPlace(string& s) {
        int start = 0;
        int end = 0;
        
        while (end < s.length()) {
            // Find the end of current word
            while (end < s.length() && s[end] != ' ') {
                end++;
            }
            
            // Reverse the current word
            reverse(s.begin() + start, s.begin() + end);
            
            // Move to next word
            start = end + 1;
            end = start;
        }
    }
};

/**
 * Class Solution2
 * Implements a single-pass, in-place approach to reverse words and clean spaces.
 */
class Solution2 {
public:
    /**
     * Reverses the words in a string in-place (single pass).
     * @param s The input string.
     * @return The string with words reversed and extra spaces removed.
     */
    string reverseWords(string s) {
        // Step 1: Reverse the entire string
        reverse(s.begin(), s.end());
        
        int i = 0, j = 0;
        int n = s.length();
        
        while (i < n) {
            // Skip leading spaces
            while (i < n && s[i] == ' ') i++;
            
            if (i < n) {
                // If we're not at the beginning, add a space
                if (j > 0) s[j++] = ' ';
                
                // Find the end of current word
                int start = j;
                while (i < n && s[i] != ' ') {
                    s[j++] = s[i++];
                }
                
                // Reverse the word we just copied
                reverse(s.begin() + start, s.begin() + j);
            }
        }
        
        // Resize to remove any trailing characters
        s.resize(j);
        return s;
    }
};

int main() {
    Solution sol;
    Solution2 sol2;
    
    // Test cases
    string test1 = "the sky is blue";
    string test2 = "  hello world  ";
    string test3 = "a good   example";
    
    cout << "Original: \"" << test1 << "\"" << endl;
    cout << "Reversed: \"" << sol.reverseWords(test1) << "\"" << endl;
    cout << "Reversed (alt): \"" << sol2.reverseWords(test1) << "\"" << endl;
    cout << endl;
    
    cout << "Original: \"" << test2 << "\"" << endl;
    cout << "Reversed: \"" << sol.reverseWords(test2) << "\"" << endl;
    cout << "Reversed (alt): \"" << sol2.reverseWords(test2) << "\"" << endl;
    cout << endl;
    
    cout << "Original: \"" << test3 << "\"" << endl;
    cout << "Reversed: \"" << sol.reverseWords(test3) << "\"" << endl;
    cout << "Reversed (alt): \"" << sol2.reverseWords(test3) << "\"" << endl;
    
    return 0;
} 