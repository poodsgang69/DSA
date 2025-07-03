class Solution {
public:
    bool isHappyMap(int n) {
        unordered_set<int> visited;
        int currentSum = n;
        while(true) {
            currentSum = addDigitSquares(currentSum);
            if (currentSum == 1) return true;
            if (visited.count(currentSum) == 0) visited.insert(currentSum);
            else return false;
        }
    }

    bool isHappy(int n) {
        int slowSum = n, fastSum = addDigitSquares(n);

        // similar slow pointer -- fast pointer approach which we use in detect linkedlist loop
        // while((fastSum != 1 || slowSum != 1) && slowSum != fastSum ) { ----> fast will always reach 1 faster than slow so no use of keeping this condition.
        while(fastSum != 1 && slowSum != fastSum ) {
            slowSum = addDigitSquares(slowSum);
            fastSum = addDigitSquares(addDigitSquares(fastSum));
        }

        return fastSum == 1;
    }
private:
    int addDigitSquares(int num) {
        int sum = 0, digit;
        while(num > 0) {
            digit = num % 10 ;
            sum += (digit*digit);
            num /= 10;
        }
        return sum;
    }
};

/*
  Straightforward approach in the Map solution. We keep calculating the digit square sum and keep adding it to the map. If we encounter it again, that means it will go into a loop so we return false. otherwise, if we directly reach 1, we return true (as per question requirements).

  The second soltion is smarter as it uses the slowPointer <--> fastPointer approach used in the "Detect a Loop in the Linked List" problem. Thing over here is we use a slow and fast pointer where fast poiner goes double the speed of the slow pointer. Loop over here is analogous 
  to the number occurring twice (we go into loop of that number). Hence till either fast reaches 1 (fast will always reach 1 first because it does double processing, and 1 is a loop in itself). or slow reaches 1 (dont need slow == 1 check), we run the loop and move the pointers
  accordingly. If there is a loop detected, fast pointer will always meet the slow pointer (at some point or the other) -> this check is caught by the slow != fast. In this case, fast never reaches 1 (as there is no way for it to reach 1. it it did, it would get caught in the first
  condition (and if it reaches 1 , i need to return true anyway so its proved by contradiction). Once this happens, it will break out and will return false (as fast != 1). if it broke out when either fast or slow were 1, it will return true (fast == 1) [fast if reaches 1, will go into self loop].
*/
