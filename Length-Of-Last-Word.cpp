#include <iostream>
#include <string>
using namespace std;

class Solution {
public:
    int lengthOfLastWordNaive(string s) {
        int l = s.size();
        int count = 0;

        for (int i = l-1 ; i >= 0 ; i--) {
            // cout << i << endl;
            if (s[i] == ' ' && count == 0) {
                continue;
            } else if (s[i] != ' ') {
                count++;
            } else if (s[i] == ' ' && count > 0) {
                break;
            }
        }
        return count;
    }
    
    int lengthOfLastWord(string s) {
        int l = s.size();
        bool word = false;
        int count = 0;

        for (int i = l-1 ; i >= 0 ; i--) {
            // cout << i << endl;
            if (s[i] == ' ' && !word) {
                continue;
            } else if (s[i] != ' ') {
                word = true;
                count++;
            } else {
                break;
            }
        }
        return count;
    }
};

int main() {
    Solution sol;
    string s = "Hello World";
    cout << sol.lengthOfLastWord(s) << endl;
    return 0;
}

/*
Link: https://leetcode.com/problems/length-of-last-word/?envType=study-plan-v2&envId=top-interview-150

Read the code, its pretty Self Explainatory.
*/