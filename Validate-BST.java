public class Solution {

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        return helper(root, null, null);
    }

    private boolean helper(TreeNode node, Integer low, Integer high) {
        if (node == null) return true;

        // Check the current node within bounds
        if (low != null && node.val <= low) return false;
        if (high != null && node.val >= high) return false;

        // Recurse into left and right subtrees
        return helper(node.left, low, node.val) &&
               helper(node.right, node.val, high);
    }
}
