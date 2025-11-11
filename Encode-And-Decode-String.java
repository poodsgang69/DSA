class Solution {
    private List<Integer> lengthsArr; // per-string CODE POINT counts

    public String encode1(List<String> strs) {
        // store code point counts, not char counts
        lengthsArr = strs.stream()
                .map(s -> s.codePointCount(0, s.length()))
                .toList();

        // flatten by CODE POINTS, shift ASCII only, keep others (e.g., emojis) unchanged
        return strs.stream()
                .flatMapToInt(String::codePoints)
                .map(cp -> cp <= 0x7F ? (cp + 1) & 0x7F : cp)
                .mapToObj(cp -> new String(Character.toChars(cp)))
                .collect(Collectors.joining());
    }

    public List<String> decode2(String encoded) {
        List<String> out = new ArrayList<>();
        int i = 0; // index into encoded (in UTF-16 code units)

        for (int cpCount : lengthsArr) {
            StringBuilder sb = new StringBuilder();
            int taken = 0;
            while (taken < cpCount) {
                int cp = encoded.codePointAt(i);
                int back = cp <= 0x7F ? (cp - 1 + 128) % 128 : cp; // undo ASCII shift
                sb.appendCodePoint(back);
                i += Character.charCount(cp); // advance by 1 or 2 chars
                taken++;
            }
            out.add(sb.toString());
        }
        return out;
    }

    public String encode(List<String> strs) {
        String joinedEncodedString = strs.stream()
                                    .map(str -> str.length() + "#" + str)
                                    .collect(Collectors.joining());
        System.out.println(joinedEncodedString);
        return joinedEncodedString;
    }

    public List<String> decode(String encoded) {

        List<String> finalAns = new ArrayList<>();
        int cont = 0;
        
        for( int i = 0 ; i < encoded.length() ; ++i ) {
            
            StringBuilder sb = new StringBuilder();
            int j = i;
            while(encoded.charAt(j) != '#') {
                cont++;
                sb.append(encoded.charAt(j));
                ++j;
            }

            int strLen = Integer.valueOf(sb.toString());
            i=j+1;
            StringBuilder sb2 = new StringBuilder();
            while(strLen > 0) {
                cont++;
                sb2.append(encoded.charAt(i));
                i++;
                strLen--;
            }
            finalAns.add(sb2.toString());
            i--;
        }

        System.out.println(cont);

        return finalAns;
    }

}



/*

🔹 Encode Algorithm

Start with a List<String> strs.

For each string s in the list:

Compute its length using s.length().

Create a formatted block: "length#string".

Concatenate all such blocks into one continuous string.

Return the combined string as the encoded output.

🔹 Decode Algorithm

Initialize an empty result list finalAns.

Use an index i to traverse the encoded string.

For each block:

Build a temporary string until you encounter '#'; this is the length field.

Convert that substring to an integer strLen.

Move past the '#' and read the next strLen characters as the actual string.

Append the extracted string to finalAns.

Continue from the end of the extracted block.

Return the list of decoded strings.

*/
