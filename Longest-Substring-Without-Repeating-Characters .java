class Solution {
    public int lengthOfLongestSubstring(String s) {
        int left = 0, maxLen = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for( int right = 0 ; right < s.length() ; ++right ) {

            if( map.containsKey( s.charAt(right)) ) left = Math.max(
                map.get(s.charAt(right)), 
                left
            );

            maxLen = Math.max(
                maxLen, 
                right - left + 1
            );

            map.put(s.charAt(right), right+1);

        }
        return maxLen;
    }
}

/*
----------------------------------------------------------
INTUITION (FOR FUTURE ME)
----------------------------------------------------------

Goal:
- Find the longest substring without repeating characters.

Main Concept:
- Use a SLIDING WINDOW defined by two pointers: LEFT and RIGHT.
- Expand the window by moving RIGHT.
- If we hit a repeated character, move LEFT forward to skip the duplicate.
- Track max window length as we go.

Why HashMap?
- Stores each character’s **most recent index**:
     key   -> character
     value -> index + 1 (why +1 explained below)
- Helps us quickly know **where the repeated char was last seen**.

Core Logic:
- For each character at `right` pointer:
    1️⃣ If it's a repeat:
         Move `left` to one position AFTER where this char was last seen.
         BUT: never move `left` backward (use Math.max).
    2️⃣ Update longest valid substring length:
         (right - left + 1)
    3️⃣ Update character’s latest index in map:
         map.put(char, right+1)

Why store index as "right + 1"?
- This allows left pointer to directly jump to a **non-conflicting** position.
- Avoids off-by-one errors when calculating substring length.

Example (visual):
     s = "abcabcbb"
     left moves only forward → window always valid!

Performance:
- Each character processed once:
    Time:  O(n)
    Space: O(min(n, charset))   // usually 26/128/256

Big Picture:
✔ Expand window when unique
✔ Shrink window when duplicate
✔ Track max at every step

Result:
- Efficient sliding window strategy that never rechecks old characters.