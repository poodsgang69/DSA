class Solution {
public:
    bool canConstructArray(string ransomNote, string magazine) {
        vector<int> charMapR(26, 0);
        vector<int> charMapM(26, 0);

        for (int i = 0 ; i < ransomNote.size() ; ++i ) {
            charMapR[ransomNote[i]-'a']++;
            
        }
        for (int i = 0 ; i < magazine.size() ; ++i ) {
            charMapM[magazine[i]-'a']++;
        }
        for (int i = 0 ; i < 26 ; ++i) {
            if (charMapR[i]==0) continue;
            if (charMapM[i]==0) return false;
            else {
                if (charMapR[i] > charMapM[i]) return false;
            }
        }
        return true;
    }

    bool canConstructHashMap(string ransomNote, string magazine) {
        unordered_map<char, int> charMapR;

        for ( char letter : ransomNote ) charMapR[letter - 'a']++;

        for ( char letter : magazine ) {
            if (charMapR.count(letter - 'a')) charMapR[letter - 'a']--;
        }

        for ( auto& counter : charMapR ) {
            if ( counter.second > 0 ) return false;
        }

        return true;
    }

    bool canConstruct(string ransomNote, string magazine) {
        vector<int> charMapR(26, 0);
        for ( int i = 0 ; i < magazine.size() ; ++i ) charMapR[magazine[i]-'a']++;  

        for ( int i = 0 ; i < ransomNote.size() ; ++i ) {
            if ( --charMapR[ransomNote[i]-'a'] < 0 ) return false;
        }

        return true;
    }
};

/*
    Pretty Straightforward
    Time Complexity: O(n)
    Space Complexity: O(1)

    We use a vector to store the count of each character in the magazine.
    Then, we iterate through the ransomNote and decrement the count of each character.
    If we encounter a character that is not in the magazine, we return false.
    If we iterate through the ransomNote and all characters are in the magazine, we return true. 

    The below 2 solutions are just combinations of the above. Once we use a char array which is fast but space inefficient, 
    to overcome this we use the hashmap, which reduces the space complexity but increases the time complexity.

    The final solution is the enhanced version of the 1st solution using the -- logic from the second soltution.
*/