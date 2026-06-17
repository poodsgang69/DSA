class Solution {
    public String processStr(String s) {
        StringBuilder res = new StringBuilder();
        
        for (char c : s.toCharArray()) {
            if (c == '*') {
                if (res.length() > 0) {
                    res.deleteCharAt(res.length() - 1);
                }
            } else if (c == '#') {
                res.append(res.toString()); // Append current contents to itself
            } else if (c == '%') {
                res.reverse();
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }
}
