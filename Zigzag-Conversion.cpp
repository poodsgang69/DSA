class Solution {
public:
    string convert(string s, int r) {
        ios_base::sync_with_stdio(false);
        int i = 0, jumpLen, j1, j2, j, jPrev;
        bool j1NotUsed;
        string answer = "";
        
        jumpLen = (r-1)*2;
        int len_s = s.size();
        
        if (r == 1 || r > len_s) return s;
        
        for ( int i = 0 ; i < r ; ++i ) {
            j1 = jumpLen - (i*2);
            j2 = i*2;
            
            j1NotUsed = true;
            j = i;
            jPrev = j;
            answer.push_back(s[i]);
            
            while ( j < len_s ) {
                jPrev = j;
                
                if(j1NotUsed) {
                    //use j1
                    j += j1;
                } else {
                    //use j2
                    j += j2;
                }
                j1NotUsed = !j1NotUsed;

                if (j == jPrev) {
                    continue;
                } else if (j < len_s){
                    answer.push_back(s[j]);
                }
                
            }
        }
        return answer;
    }
};

/* 
This is basically a pattern finding question where we solve it in O(N) as we go over all the numbers only once (though we use 2 loops). Refer my notes for the answer and intuition behind this. 
NOTE: Also go over other solutions to figure out what other ways we can solve this.
*/
