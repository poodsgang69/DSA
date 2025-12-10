class Solution {
    private int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        rec(root);
        return diameter;
    }

    private int rec(TreeNode root) {
        if (root == null) return 0;

        int left = rec(root.left);
        int right = rec(root.right);

        diameter = Math.max(diameter, left + right);

        return Math.max(left, right) + 1;
    }
}
