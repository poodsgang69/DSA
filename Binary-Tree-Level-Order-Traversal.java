
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> answer = new ArrayList<>();
        if (root == null) return answer;

        Queue<TreeNode> queue = new LinkedList<>();
        // offer the first element => root
        queue.offer(root);

        // BFS on the tree iteratively
        while(!queue.isEmpty()) {
            // get the number of children to run a loop
            int children = queue.size();
            List<Integer> levelNodeValues = new ArrayList<>();
            for(int i = 0 ; i < children ; ++i) {
                // add each child to a list
                TreeNode currNode = queue.poll();
                levelNodeValues.add(currNode.val);

                // add its children to the queue
                if(currNode.left != null) queue.offer(currNode.left);
                if(currNode.right != null) queue.offer(currNode.right);
            }

            // add the levelNodes to the final answer
            answer.add(levelNodeValues);
        }

        return answer;
    }

    public List<List<Integer>> levelOrderRec(TreeNode root) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        rec(root, map, 0);
        return new ArrayList<>(map.values());
    }

    public void rec(TreeNode root, Map<Integer, List<Integer>> map, int level) {
        if(root == null) return;
        map.computeIfAbsent(level, k -> new ArrayList<>()).add(root.val);
        rec(root.left, map, level+1);
        rec(root.right, map, level+1);
    }
}

/*
    This problem requires us to traverse a binary tree level by level and return
    the values grouped by their depth (level order traversal).

    The solution is implemented in two different ways:
    1) Iterative approach using BFS (Queue)
    2) Recursive approach using DFS + level mapping

    ------------------------------------------------------------
    1) ITERATIVE BFS APPROACH (levelOrder)
    ------------------------------------------------------------
    - We use a queue to perform Breadth First Search.
    - The root node is inserted into the queue first.
    - At each iteration of the while loop:
        - The current size of the queue represents the number of nodes at that level.
        - We iterate exactly that many times to process only the current level.
        - For each node:
            - Add its value to the current level list.
            - Push its left and right children into the queue (if they exist).
    - After processing one full level, we add the level list to the final answer.
    - This guarantees level-by-level traversal.

    Time Complexity: O(n) — every node is visited once.
    Space Complexity: O(n) — queue can hold up to n nodes in the worst case.

    ------------------------------------------------------------
    2) RECURSIVE DFS + MAP APPROACH (levelOrderRec)
    ------------------------------------------------------------
    - We use recursion to traverse the tree in a DFS manner.
    - A map is used to group node values by their level.
        Key   -> level number
        Value -> list of node values at that level
    - The recursive function:
        - Stops when the node is null.
        - Uses computeIfAbsent to initialize the list for a level if it doesn’t exist.
        - Adds the current node’s value to the list for that level.
        - Recursively processes left and right children with level + 1.
    - After traversal, we extract the map values and return them as a list of lists.

    Note:
    - Since HashMap does not guarantee ordering, this solution relies on the fact
      that levels are inserted in increasing order due to recursion.
    - If strict ordering is required, a TreeMap or sorting by level should be used.

    Time Complexity: O(n) — each node is processed once.
    Space Complexity: O(n) — recursion stack + map storage.

    ------------------------------------------------------------
    SUMMARY
    ------------------------------------------------------------
    - BFS is more intuitive for level order traversal and guarantees ordering.
    - DFS + map is elegant and concise, especially when level information is needed.
    - Both approaches are valid and commonly asked in interviews.

*/
