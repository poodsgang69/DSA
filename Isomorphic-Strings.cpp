class Solution {
public:
    bool isIsomorphic(string s, string t) {
        return doStuff(s, t) && doStuff(t, s);
    }

private:
    bool doStuff(string s, string t) {
        unordered_map<char, char> charMap;
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
    Simple solution to the isomorphic strings problem.
    We need to check the string both ways, because we need to make sure that the mapping is one-to-one.
    if we only check one way, we might get a false positive: example: badc , baba (needs to return false but returns true). Checking one way means only checking one-way mapping.
*/