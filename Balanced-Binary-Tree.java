class Solution {

    private boolean flag = true;

    public boolean isBalanced(TreeNode root) {
        rec(root);
        return flag;
    }

    private int rec(TreeNode curr) {
        if (curr == null) return 0;

        int left = rec(curr.left);
        int right = rec(curr.right);

        if (Math.abs(left - right) > 1) {
            flag = false;
        }

        return 1 + Math.max(left, right);
    }
}