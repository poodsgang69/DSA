class Solution {
    private int gn =0;
    public int goodNodes(TreeNode root) {
        if (root==null) return 0;
        rec(root, root.val);
        return gn;
    }
    public void rec(TreeNode curr, int currMax){
        if (curr == null) return;
        if (curr.val >= currMax) {
            gn++;
            currMax=curr.val;
        }
        rec(curr.left , currMax);
        rec(curr.right, currMax);
    }
}

/*
EXPLANATION / THOUGHT PROCESS:

Problem:
Count the number of "good" nodes in a binary tree.
A node is "good" if there is no node with a greater value
than it on the path from the root to that node.

Approach:
- Traverse the tree using DFS (preorder style).
- While traversing, keep track of the maximum value seen so far
  on the path from the root to the current node.

Key Idea:
- At each node, compare its value with the maximum value
  encountered on the path so far (currMax).
- If curr.val >= currMax, then this node is a "good" node.

How it works step by step:
1. Start recursion from the root.
   - Initialize currMax with root.val since the root is always good.
2. For every node:
   - If curr.val >= currMax:
       - Increment the global good-node counter (gn).
       - Update currMax to curr.val.
   - Recurse into left and right children,
     passing the updated currMax.
3. Null nodes simply return (base case).

Why a global variable (gn)?
- The recursive function does not need to return anything.
- We just accumulate the count as we traverse the tree.

Time Complexity:
- O(N), where N is the number of nodes (each node visited once).

Space Complexity:
- O(H), where H is the height of the tree (recursion stack).

Summary:
This solution performs a DFS while carrying forward
the maximum value seen so far, and counts nodes that
are not smaller than any ancestor on their path.
*/