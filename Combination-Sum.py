class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        def rec(curr_ans: List[int], f_ans: List[List[int]], candidates: List[int], idx: int, remaining: int):
            if(remaining < 0): 
                # curr_sum > target
                return 
            elif(remaining == 0):
                # solution found
                f_ans.append(curr_ans.copy())
            for i in range(idx, len(candidates)):
                curr_ans.append(candidates[i])
                rec(curr_ans, f_ans, candidates, i, remaining-candidates[i])
                curr_ans.pop()
            
        f_ans = []
        curr_ans = []
        rec(curr_ans, f_ans, candidates, 0, target)
        return f_ans
        