/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int helper(TreeNode root) {
        // Base Case #1 (as an empty node is also height balanced)
        if(root == null) return 0;

        // Call the recursive funtions
        int rHeight = helper(root.right);
        int lHeight = helper(root.left);

        // Base Case #2
        if(
            rHeight == -1 ||
            lHeight == -1 ||
            Math.abs(rHeight - lHeight) > 1    
        ) {
            return -1;
        }

        // return the max of the heights + 1
        return Math.max(rHeight, lHeight) + 1;
    }
    public boolean isBalanced(TreeNode root) {
        return helper(root) == -1 ? false : true;
    }
}

/*

Simple algo to check whether a tree is height balanced or not.

Time Complexity: O(N) - N is the number of nodes
Space Complexity: O(H) - H is th number of nodes currently being traversed. 
*/
