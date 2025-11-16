class Helper {
  private Helper() {}
    private boolean alphNum(char c){
        return (c >= 'A' && c <= 'Z') || 
               (c >= 'a' && c <= 'z') || 
               (c >= '0' && c <= '9');
    }
    private char toLower(char c) {
        return (c >= 'A' && c <= 'Z') ? (char)(c + 32) : c;
    }
}
