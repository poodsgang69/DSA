class Solution {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> arr = new ArrayList<>();
        inorder(root, k, arr);
        return arr.get(k-1);

    }

    public void inorder(TreeNode curr, int k, List<Integer> arr) {
        if(curr.left != null) inorder(curr.left, k, arr);
        arr.add(curr.val);
        if(curr.right != null) inorder(curr.right, k, arr);
    }
}

class Solution {
    int rank = 0;
    int ans = -1;
     public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return ans;
    }

    public void inorder(TreeNode curr, int k) {
        if(curr.left != null) {
            inorder(curr.left, k);
        }
        rank++;
        if(rank == k) {
            ans = curr.val;
            return;
        }
        if(curr.right != null) {
            inorder(curr.right, k);
        }
    }
}

/*
Concept here is that In-order always prints BST in sorted order. So finding kth smallest is just finding the kth ranked element in the BST's InorderTraversal. 
First Solution used array to store all elements in order and then returned kth element from the list. 

2nd solution takes it a but further by returning the element at that ranking from the InOrder Traversal and saving time. 
*/
