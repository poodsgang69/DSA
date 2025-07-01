class Solution {
public:
    bool wordPattern(string pattern, string s) {
        vector<string> patternStr, sStr;
        string currWord = "";
        for (char c : pattern) patternStr.push_back(std::string(1,c));
        for (char c : s) {
            if ( std::string(1,c) == " " ) {
                sStr.push_back(currWord);
                currWord = "";
            }
            else currWord += c;
        }
        sStr.push_back(currWord);
        return patternStr.size() == sStr.size() && doStuff(patternStr, sStr) && doStuff(sStr, patternStr);
    }
private:
    bool doStuff(vector<string> s, vector<string> t) {
        unordered_map<string, string> charMap;
        size_t siz = s.size();
        // Fill the map with mappings of each s[i] <--> t[i]
        for ( int i = 0 ; i < siz ; ++i ) {
            if ( !charMap.count(s[i]) ) charMap[s[i]] = t[i];
            else {
                if ( charMap[s[i]] != t[i] ) return false;
            }
        }

        return true;
    }
};

/*
    Solution taken inspiration from the isomorphic strings problem.
    Only new thing is that we need to split the string into a vector of strings.
    We do this by iterating through the string and checking if the current character is a space.
    If it is, we push the current word to the vector and reset the current word.
    If it is not, we add the character to the current word.
    After the loop, we push the last word to the vector.
    We then check if the size of the pattern vector is equal to the size of the s vector.
    If it is not, we return false.
    We then check if the pattern vector and the s vector are isomorphic i.e a one-to-one mapping.
*/