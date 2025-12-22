class Solution {
    public int countCollisions(String directions) {
        int ptr = 0;
        int lPtr=0;
        int rPtr = directions.length()-1;
        int ctr=0;
        while(ptr < rPtr && directions.charAt(ptr)=='L') {
            
            ptr++;
        }
        lPtr=ptr;
        if(lPtr>rPtr-1) return 0;
        ptr=rPtr;
        while(ptr >=0 && directions.charAt(ptr)=='R') {
            ptr--;
        }
        rPtr=ptr;
        if(rPtr < 0) return 0;
        for (int i = lPtr ; i<=rPtr ; i++) {
            if (directions.charAt(i)!= 'S') ctr++;
        }
        return ctr;
    }
}

/*
Major point here is that for any car direction there will be
a number of collisions = number of left + number of right 
RL or RSSL will both be 2.
so we firstly strip all the cars going left from thr right of the string
as they will never collide (you might think wont they collide with each other)
point here is that thry all happen at thr same time with constant speed. so N cars
travelling in the same direction will never collide as this is a simulation question whete
everything happens at the same time and also cuz they move at the same speed.
Same story from right to left, we remove all cars travelling right. 
now from the cleaned string (here i didnt remove any chars, i assigned range pointers lPtr and rPtr) 
we just need to count the L's and the R's ignoring the S's as they dont do anything. 
eg: RSLL is the same as just RLL. 3 collisions

time complexity: O(N) - scanning over entire string
space complexity : O(1) - no extra space
*/