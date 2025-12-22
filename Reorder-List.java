/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public void reorderListIterative(ListNode head) {
        List<ListNode> nodes = new ArrayList<>();
        //calc len(linkedList)
        ListNode ptr = head;
        int len = 0;
        while(ptr != null) {
            len++;
            ptr = ptr.next;
        }

        if(!(len == 1 || len == 2)) {
            //find out the split idx
            int split_idx = len/2; //always rounds down


            //only add split and post split nodes to array
            ListNode split = head;
            int counter = 0;
            while(counter < len) {
                if(counter >= split_idx) {
                    // add to list
                    nodes.add(split);
                }
                split = split.next;
                counter++;
            }

            if(len%2 == 1) nodes.remove(0);

            // perform logic
            int i = nodes.size() - 1;
            ListNode temp = null, n1 = head;
            while(i >= 0) {
                temp = n1.next;
                n1.next = nodes.get(i);
                nodes.get(i).next = temp;
                n1 = temp;
                i--;
            }

            n1.next = null;
            // return head;
        }

    }
    private ListNode front;
    public void reorderList(ListNode head) {
        if(head==null || head.next==null) return;
        front = head;
        rec(head);
    }

    public boolean rec(ListNode back) {
        if (back == null) return true; // base case 1 (end of ll)

        boolean continuee = rec(back.next);
        if(!continuee) return false;

        if(front == back || front.next == back) {
            // reached middle
            back.next = null;
            return false;
        }

        //swap front and back
        ListNode temp = front.next;
        front.next = back;
        back.next = temp;

        //update global front
        front = temp;

        return true;
    }
}

/*
First soln is where we do the followig using arrays.
Its equivalent to finding the middle -> reversing from the middle to the end -> interweaving the first and last part of the LL's.
Its instead done using arrays to store the 2nd part of the LL (using split_index) and then interweving using the ListNodes stored in the Arrays.

The 2nd solution is recursive where we use a global Front variable to fo front and as we cant go back normally in an LL, we use the recursive call stack as a natual "go-back" mechanism and when we reach the middle (indicated by the front == back - odd length or the 
front.next == back - even length), we stop. 

Detailed explaination SOLUTION 1:
==================== EXPLANATION ====================

This solution reorders the linked list by using extra space (ArrayList).

Overall idea:
- Keep the first half of the list in place.
- Store the second half of the list in an ArrayList.
- Insert nodes from the END of that ArrayList one by one between nodes
  from the front of the list.

Steps:

1) Compute the length of the linked list.
   - If length <= 2, no reordering is needed.

2) Compute split index = len / 2.
   - This marks where the "second half" begins.

3) Traverse the list again and store all nodes from split index onward
   into an ArrayList.
   - These nodes will later be inserted in reverse order.

4) If the list length is odd, remove the first element from the ArrayList.
   - This removes the middle node, which should remain in place.

5) Reorder:
   - Start from the head of the list (n1).
   - Take nodes from the END of the ArrayList.
   - For each node:
       a) Save n1.next
       b) Insert the ArrayList node after n1
       c) Restore the saved next pointer
       d) Move n1 forward

   This creates the pattern:
   L0 → Ln → L1 → Ln-1 → ...

6) Explicitly set n1.next = null at the end to prevent cycles.

Complexity:
- Time: O(n)
- Space: O(n) due to ArrayList

Tradeoff:
- Easier to understand than pointer-heavy in-place solutions
- Slower and uses extra memory compared to the optimal O(1) space approach

============================================================
Detailed explaination SOLUTION 2:
==================== EXPLANATION ====================
This is a recursive, in-place solution to “Reorder List” (LeetCode 143).
It’s clever, compact, and non-obvious, so your confusion is justified.

I’ll explain it in three layers:

What the idea is (high level)

What each variable means

What actually happens step-by-step

No hand-waving.

1️⃣ What is this code trying to do?

The goal of reorder list is:

L0 → L1 → L2 → L3 → L4
↓
L0 → L4 → L1 → L3 → L2


This code does it by:

Walking to the end using recursion, then weaving nodes from the back with nodes from the front while unwinding the recursion.

So instead of:

finding middle

reversing second half

merging

…it uses the call stack as the “back pointer”.

That’s the key idea.

2️⃣ What do the variables really represent?
front
private ListNode front;


This is a global pointer that always moves forward from the head.

Think of it as:

“The next node from the front that needs to be paired.”

back (method parameter)
private boolean rearrange(ListNode back)


This is:

“The node we are currently visiting while coming back from recursion.”

So:

front moves forward

back moves backward (via recursion unwinding)

This simulates two pointers without explicitly reversing the list.

3️⃣ How the recursion actually works (this is the important part)

Let’s use a concrete example:

1 → 2 → 3 → 4 → 5

Step A: Go all the way to the end
rearrange(1)
→ rearrange(2)
→ rearrange(3)
→ rearrange(4)
→ rearrange(5)
→ rearrange(null)


At back == null, recursion stops and starts returning.

Step B: Start rewiring while returning

Now the calls unwind from the back of the list.

Unwinding at back = 5

front = 1

Check stopping condition:

front == back?  // 1 == 5 ❌
front.next == back? // 2 == 5 ❌


So we weave:

1 → 5 → 2 → 3 → 4


And move front forward:

front = 2

Unwinding at back = 4

front = 2

Again:

2 → 4 → 3


Full list now:

1 → 5 → 2 → 4 → 3


Move front = 3.

Unwinding at back = 3

Now:

front == back   // true (3 == 3)


This means:

we’ve reached the middle

stop further rewiring

terminate the list

back.next = null;
return false;


From now on, recursion just returns without doing anything.

4️⃣ Why the boolean return exists

This line:

if(!keepGoing) return false;


Prevents over-weaving.

Once the middle is reached:

further unwinding must NOT modify pointers

otherwise you’d undo the reorder or create cycles

So the boolean is a “stop signal”.

5️⃣ Why this code works

Because it enforces this invariant:

After each unwind step, the list is correctly reordered up to front and back.

And it stops exactly when:

odd length → front == back

even length → front.next == back

That’s the correct termination point.
*/
