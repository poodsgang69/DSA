class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        // Ensure l1 starts with the smaller (or equal) head
        if (l2.val < l1.val) {
            ListNode t = l1;
            l1 = l2;
            l2 = t;
        }

        ListNode head = l1;
        ListNode prev = null;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev = l1;
                l1 = l1.next;
            } else {
                // splice l2 before l1
                ListNode next = l2.next;
                prev.next = l2;
                l2.next = l1;
                prev = l2;
                l2 = next;
            }
        }

        if (l2 != null) prev.next = l2;
        return head;
    }
}

/*
The only idea you need

Your code uses l1 as the main chain and sometimes inserts (splices) one node from l2 into l1 when l2’s current node should come before l1’s current node.

It does not “skip” nodes. It either:

moves forward in l1 (when l1.val <= l2.val), or

moves one node from l2 into the right place (when l2.val < l1.val).

A cleaner example where splicing is super clear
Example
l1: 1 → 10 → 20
l2: 2 → 3 → 4 → 30

Step 0: ensure head is smallest

l1.head=1, l2.head=2, no swap.

Set:

head = 1

prev = null

l1 = 1 → 10 → 20

l2 = 2 → 3 → 4 → 30

Iteration 1

Compare l1=1 and l2=2:

1 <= 2 so we just move in l1

Action:

prev = 1

l1 = 10

Now:

prev is at 1

l1 at 10

l2 at 2

Iteration 2 (here comes splicing)

Compare l1=10 and l2=2:

2 < 10 so 2 must be placed before 10

Your code does this:

What we want (conceptually)

Insert 2 between prev(1) and l1(10):

Before:

1 → 10 → 20
^
prev

l2: 2 → 3 → 4 → 30


After insertion:

1 → 2 → 10 → 20
    ^
   prev   (moves to 2)

l2: 3 → 4 → 30  (l2 advances)

How the pointer steps achieve that (in plain English)

Save where l2 continues (so we don’t lose it)

Connect prev to l2 (attach from left)

Connect l2 to l1 (attach to right)

Move prev forward (because l2 is now part of merged)

Move l2 forward (to the saved remainder)

So after this one splice:

merged chain is now 1 → 2 → 10 → 20

prev = 2

l2 = 3

l1 is still 10

Notice: we did not move l1 because 10 is still waiting to be compared with the next l2 node.

Iteration 3 (multiple smaller nodes case!)

Now compare:

l1 = 10

l2 = 3

Again 3 < 10 → splice 3 in front of 10.

Result becomes:

1 → 2 → 3 → 10 → 20
prev = 3
l2 = 4 → 30
l1 = 10 → 20

Iteration 4

Compare:

l1=10

l2=4

Again splice 4:

Result:

1 → 2 → 3 → 4 → 10 → 20
prev = 4
l2 = 30
l1 = 10 → 20

Iteration 5 (now l1 has smaller nodes)

Compare:

l1=10

l2=30

Now 10 <= 30 → just move forward in l1:

prev = 10

l1 = 20

Compare again:

20 <= 30 → move forward in l1

prev = 20

l1 = null

Loop ends. Append remaining l2:

prev.next = 30

Final:

1 → 2 → 3 → 4 → 10 → 20 → 30

Now your main question: “what if multiple nodes are <… do we skip?”
Case A: Many l2 nodes are smaller than current l1

That’s exactly what happened with 2,3,4 vs 10.

Do we skip them? No.
We insert them one by one in consecutive loop iterations.

Why one by one? Because singly linked lists only let you move one node at a time, and the code is written to splice a single l2 node per else hit.

So it feels like we’re “skipping l1” because l1 doesn’t move, but what’s really happening is:

l1 is waiting,

l2 keeps getting inserted in front of it until it’s no longer smaller.

Case B: Many l1 nodes are smaller than current l2

Then the if (l1.val <= l2.val) branch runs repeatedly.
That “skips” nothing; it just means those l1 nodes are already in the correct order and we walk through them.

Example:

l1: 1 → 2 → 3 → 100
l2: 50 → 60


We’ll walk l1 through 1,2,3 (prev keeps moving), and only when we hit 100 do we start splicing 50,60.

Why your code looks confusing (truthfully)

This in-place approach is correct but not beginner-friendly because:

the merged list is being formed inside l1’s existing structure

“merged so far” is better thought of as head..prev, not the entire visible chain

If you want the simplest mental model, the dummy-head solution is much easier.

If you paste one specific input you’re stuck on (like l1: ..., l2: ...), I’ll dry-run it step by step, but using only:

head

prev

l1

l2
and showing exactly how they change each iteration.
*/
