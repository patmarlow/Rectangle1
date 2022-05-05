import java.util.Random;

/**
 * Enum to choose which iterator to choose from
 * 
 * @author Patrick Marlow (patrickmarlow)
 * @version 5.4.2022
 *
 */
enum Iterator {
    /**
     * The inner iterator
     */
    INNER,
    /**
     * The outer iterator
     */
    OUTER,
}




/**
 * SkipList Documentation from OpenDSA
 * 
 * @author OpenDSA
 * 
 *         Modified by Patrick Marlow
 * @version 15.1
 *
 * @param <K>
 *            The Key
 * @param <E>
 *            The Value
 */
class SkipList<K extends Comparable<K>, E> {
    private SkipNode<K, E> head;
    private SkipNode<K, E> init;
    private SkipNode<K, E> outit;
    private int level;
    private int size;
    static private Random ran = new Random(); // Hold the Random class object

    /**
     * Default Constructor
     */
    public SkipList() {
        head = new SkipNode<K, E>(null, null, 0);
        init = head;
        outit = head;
        level = -1;
        size = 0;
    }


    /**
     * Return the (first) matching matching element if one exists, null
     * otherwise
     * 
     * @param key
     *            The key
     * @return
     *         The element
     */
    public E find(K key) {
        SkipNode<K, E> x = head; // Dummy header node
        for (int i = level; i >= 0; i--) // For each level...
            while ((x.getForward()[i] != null) && (x.getForward()[i].key()
                .compareTo(key) < 0)) // go forward
                x = x.getForward()[i]; // Go one last step
        x = x.getForward()[0]; // Move to actual record, if it exists
        if ((x != null) && (x.key().compareTo(key) == 0))
            return x.element(); // Got it
        else
            return null; // Its not there
    }


    /**
     * Normal Traversal of the SkipList to find the first rectangle that matches
     * the input
     * 
     * @param elem
     *            The element to be found
     * @return
     *         The key
     */
    public K find(E elem) {
        SkipNode<K, E> x = head; // pointer to header node

        while (x.getForward()[0] != null) {
            if (x.getForward()[0].element().equals(elem))
                break;

            x = x.getForward()[0];
        }

        // Go to the next node
        x = x.getForward()[0];

        // If x is null, then the elem was not found
        if ((x != null) && (x.element().equals(elem)))
            return x.key();
        else
            return null;
    }


    /**
     * Pick a level using a geometric distribution
     * 
     * @return
     *         The level
     */
    int randomLevel() {
        int lev;
        for (lev = 0; Math.abs(ran.nextInt()) % 2 == 0; lev++) {
        }

        return lev;
    }


    /**
     * Insert a key, element pair into the skip list
     * 
     * @param key
     *            the key
     * @param elem
     *            the element
     */
    @SuppressWarnings("unchecked")
    public void insert(K key, E elem) {
        int newLevel = randomLevel(); // New node's level
        if (newLevel > level) // If new node is deeper
            adjustHead(newLevel); // adjust the header
        // Track end of level
        SkipNode<K, E>[] update = new SkipNode[level + 1];
        SkipNode<K, E> x = head; // Start at header node
        for (int i = level; i >= 0; i--) { // Find insert position
            while ((x.getForward()[i] != null) && (x.getForward()[i].key()
                .compareTo(key) < 0))
                x = x.getForward()[i];
            update[i] = x; // Track end at level i
        }
        x = new SkipNode<K, E>(key, elem, newLevel);
        for (int i = 0; i <= newLevel; i++) { // Splice into list
            x.getForward()[i] = update[i].getForward()[i]; // Who x points to
            update[i].getForward()[i] = x; // Who points to x
        }
        size++; // Increment dictionary size
    }


    private void adjustHead(int newLevel) {
        SkipNode<K, E> temp = head;
        head = new SkipNode<K, E>(null, null, newLevel);
        for (int i = 0; i <= level; i++)
            head.getForward()[i] = temp.getForward()[i];
        level = newLevel;
    }


    /**
     * Removes a specific key and data pair
     * 
     * @param key
     *            The key to be removed
     * @return
     *         The node of the removed key
     */
    @SuppressWarnings("unchecked")
    public SkipNode<K, E> remove(K key) {

        // Check if the key is in the SkipList
        if (this.find(key) == null)
            return null;

        SkipNode<K, E> removed;

        // Track end of level
        SkipNode<K, E>[] update = new SkipNode[level + 1];
        SkipNode<K, E> x = head; // Pointer to head
        // Gets the pointer to the Node before the key at each level
        for (int i = level; i >= 0; i--) {
            while ((x.getForward()[i] != null) && (x.getForward()[i].key()
                .compareTo(key) < 0))
                x = x.getForward()[i];
            update[i] = x;
        }

        // Gets the node that will be removed
        removed = update[0].getForward()[0];

        // Disconnect all pointers to the key that will be removed
        // And make the previous node point to the removed key's pointers
        for (int i = level; i >= 0; i--) {
            // Check if the Node at the level of the update array points to the
            // key
            if ((update[i].getForward()[i] != null) && (update[i]
                .getForward()[i].key().compareTo(key) == 0)) {
                // Set the forward pointer to whatever the key was pointing to
                update[i].getForward()[i] = update[i].getForward()[i]
                    .getForward()[i];
            }

        }

        size--;

        return removed;
    }


    /**
     * Removes a specific KVPair given the value
     * 
     * @param elem
     *            the element
     * @return
     *         the node that was removed
     */
    public SkipNode<K, E> remove(E elem) {
        // Check if the element is in the SkipList
        K key = this.find(elem);
        if (key == null)
            return null;

        SkipNode<K, E> removed;

        // Track end of level
        @SuppressWarnings("unchecked")
        SkipNode<K, E>[] update = new SkipNode[level + 1];
        SkipNode<K, E> x = head; // Pointer to head
        // Gets the pointer to the Node before the key at each level
        for (int i = level; i >= 0; i--) {
            while ((x.getForward()[i] != null) && (x.getForward()[i].key()
                .compareTo(key) < 0))
                x = x.getForward()[i];

            // At this point, x should be the node right before the desired key,
            // however
            // since there are duplicate keys with different values, we must
            // check that we
            // choose the right KVPair

            // Get a pointer to the original node
            SkipNode<K, E> ptr = x;

            while ((x.getForward()[i] != null) && (x.getForward()[i].key()
                .compareTo(key) == 0)) {
                // Check if the next value is the target element
                if (x.getForward()[i].element().equals(elem))
                    break;

                x = x.getForward()[i];
            }

            // The above while can accidentally skip over the desired node,
            // therefore if the next node does not equal the key, the set x to
            // the original node

            if ((x.getForward()[i] != null) && (x.getForward()[i].key()
                .compareTo(key) != 0))
                x = ptr;

            update[i] = x;
        }

        removed = update[0].getForward()[0];

        // Disconnect all pointers to the key that will be removed
        // And make the previous node point to the removed key's pointers
        for (int i = level; i >= 0; i--) {
            // Check if the Node at the level of the update array points to the
            // key
            if ((update[i].getForward()[i] != null) && (update[i]
                .getForward()[i].key().compareTo(key) == 0) && (update[i]
                    .getForward()[i].element().equals(elem))) {
                // Set the forward pointer to whatever the key was pointing to
                update[i].getForward()[i] = update[i].getForward()[i]
                    .getForward()[i];
            }

        }

        size--;

        return removed;

    }


    /**
     * Searches and prints the keys and values of a given key
     * 
     * @param key
     *            the key
     * @return
     *         The node in string format
     */
    public String search(K key) {
        String s = "";

        // If not found then return empty string
        if (this.find(key) == null)
            return s;

        // If the key exists, find the first instance
        SkipNode<K, E> x = head; // Dummy header node
        for (int i = level; i >= 0; i--) // For each level...
            while ((x.getForward()[i] != null) && (x.getForward()[i].key()
                .compareTo(key) < 0)) // go forward
                x = x.getForward()[i]; // Go one last step
        x = x.getForward()[0]; // Move to actual record, if it exists

        // Print all nodes with the key
        while (x != null && x.key().compareTo(key) == 0) {
            s += x.toString() + "\n";
            x = x.getForward()[0];
        }

        return s;
    }


    /**
     * Gets all of the node's data and dumps it into a String
     * 
     * @return
     *         All of the Nodes in the List
     */
    public String dump() {
        String s = "";

        // Get a pointer to the head
        SkipNode<K, E> x = head;

        // Print all of the Nodes in the SkipList
        do {
            s += "Node has depth " + x.getForward().length + ", ";
            s += "Value ";
            // Changes (null, null) to (null)
            if (x.toString().equals("(null, null)"))
                s += "(null)\n";
            else
                s += x.toString() + "\n";

            x = x.getForward()[0];
        }
        while (x != null);

        return s;
    }


    /**
     * Returns the node the iterator is pointing at
     * 
     * @param type
     *            Which node the function is referring to
     * @return
     *         The node
     */
    public SkipNode<K, E> getIt(Iterator type) {
        if (type == Iterator.INNER)
            return init;

        return outit;
    }


    /**
     * Makes the iterator point at the head
     * 
     * @param type
     *            The type of iterator to change
     */
    public void resetIt(Iterator type) {
        if (type == Iterator.INNER)
            init = head;
        else
            outit = head;
    }


    /**
     * Moves the Iterator by one Node unless at end
     * 
     * @param type
     *            The type of iterator
     * @return
     *         True if it was successful, False if the next node is null or if
     *         the iterator is null
     */
    public boolean advanceIt(Iterator type) {
        if (type == Iterator.INNER) {
            // Check that the iterator is not null
            if (init == null)
                return false;
            else {
                init = init.getForward()[0];

                // Check if the the iterator is now null, and if so then return
                // false
                return (init != null);

            }
        }

        // Outer
        if (outit == null)
            return false;
        else {
            outit = outit.getForward()[0];

            return (outit != null);

        }
    }


    /**
     * Returns the size of the list
     * 
     * @return
     *         the size
     */
    public int getSize() {
        return size;
    }
}
