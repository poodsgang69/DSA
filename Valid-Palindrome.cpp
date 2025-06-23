class Solution {
public:
    bool isPalindrome(string s) {
        int i = 0, size = s.size(), j = size-1;
        // bool answer = true;
        
        while( i <= j ) {
            if(
                !isalnum(s[i]) 
            ) {
                i++;
                continue;
            }
            if (!isalnum(s[j])) {
                j--;
                continue;
            }
            if(areEqual(s[i], s[j])) {
                i++;
                j--;
            } else {
                return false;
            }
        }

        return true;
    }
private:
    bool areEqual(char a, char b) {
        return std::tolower(a) == std::tolower(b);
    }
};

/*
    Pretty Straightforward Solution with two pointers.
    We use isalnum to check if the character is a letter or a number.
    We use tolower to convert the character to lowercase.
    We use areEqual to check if the characters are equal.
    We use i and j to iterate through the string.
    We use i <= j to check if we have iterated through the whole string.
    We use i++ and j-- to move the pointers.
    We use return false to return false if the characters are not equal.
    We use return true to return true if the characters are equal.
*/