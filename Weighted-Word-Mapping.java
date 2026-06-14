class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            int weight = 0;
            for(int i = 0 ; i < word.length() ; ++i) {
                weight += weights[(int)word.charAt(i) - 97];
            }
            
            int modWeight = weight % 26;
            char mappedChar = (char) ('z' - modWeight);
            sb.append(mappedChar);
        }
        return sb.toString();
    }
}
