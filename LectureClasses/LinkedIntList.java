// Represents a list of integers.
public class LinkedIntList {
    // ArrayIntList => int[] elementdata
    private ListNode front;
    
    public LinkedIntList() {
        front = null;
    }
    
    // pre : max >= 0
    // post: Constructs a list with the numbers [max, max - 1, ..., 2, 1, 0]
    public LinkedIntList(int max) {
        // Hunter: Unnecessary unrolling loop; loop zen
        // front = new ListNode(0, front);
        for (int i = 0; i <= max; i++) {
            front = new ListNode(i, front);
            
            /* Hunter: could also do
             * ListNode temp = new ListNode(i);
             * temp.next = front;
             * front = temp;
             */
        }  
    }
    
    // pre : the list is sorted numerically (non-decreasing)
    // post: adds the value to the list such that the list is stil sorted
    public void addSorted(int value) {
        //  robust test       sensitive test
        if (front == null || front.data > value) {
            front = new ListNode(value, front);
        } else { 
            ListNode current = front;
            while (current.next != null && current.next.data < value) {
                current = current.next;
            }
            current.next = new ListNode(value, current.next);
        }
    }

    // post: Adds the given value to the end of this list
    public void add(int value) {
        if (front == null) {
            front = new ListNode(value);
        } else {
            ListNode current = front;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new ListNode(value);
        } 
    }
    
    // post: Returns a comma-separated, bracketed version of the list
    public String toString() {
        if (front == null) {
            return "[]";
        } else {       
            // Have to fencepost for comma separation, easier to do at end rather than beginning.
            ListNode current = front;
            String result = "[";
            while (current.next != null) {
                result += current.data + ", ";
                current = current.next;
            }
            result += current.data + "]";
            return result;
        }
    }
}