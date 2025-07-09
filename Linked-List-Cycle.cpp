class Solution {
public:
    bool hasCycle(ListNode *head) {
        ListNode *fast=head, *slow=head;
        while(fast!=NULL && fast->next!=NULL) {
            slow = slow->next;
            fast = fast->next->next;
            if(slow==fast)
                return true;
        }
        return false;
    }
};

/*
    Straightforward solution using two pointers.
    Fast pointer moves two steps at a time, slow pointer moves one step at a time.
    If there is a cycle, fast and slow pointers will meet at some point.
    If there is no cycle, fast pointer will reach the end of the list first.

    Time Complexity: O(n)
    Space Complexity: O(1)
*/