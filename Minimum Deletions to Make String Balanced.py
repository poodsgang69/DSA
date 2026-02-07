class Solution:
    def minimumDeletions(self, s: str) -> int:
        removed = 0

        num_a_after_pivot = 0
        num_b_before_pivot = 0

        ans = len(s)

        # calculate the number of a's in the string
        for ch in s:
            if(ch == 'a'):
                num_a_after_pivot += 1

        for ch in s:
            # reduce the count of a if it is "a"
            if(ch == 'a'):
                num_a_after_pivot -= 1
            ans = min(ans, num_a_after_pivot + num_b_before_pivot)    
            # increase the b count if the pivot is currently b (after the calc, we pass the pivot, hence we increase the b count after)
            if(ch == 'b'):
                num_b_before_pivot += 1
        

        return removed + ans

        

'''

for any given length, the answer lies in one of 3 cases
"aaaaaaa"
"bbbbbbb"
"aaabbbb"
"aaaabbb"

Basically, there shouldnt be any a's or b's in the other's section
we can boil this question down to only 1 case:
    i)   All a's at the start and then all b's

Any Other case is invalid and needs checks.

We consider every case as one pivot and calculate the min number of deletions

ans = num_a's_that_need_to_be_removed_from_right_of_pivot + num_b's_that_need_to_be_removed_from_left_of_pivot

'''
