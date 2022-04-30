import java.util.Random;

/**
 * SkipList Documentation from OpenDSA
 * 
 * @author OpenDSA
 * 
 *         Modified by Patrick Marlow
 *
 * @param <K> The Key
 * @param <E> The Value
 */
class SkipList<K extends Comparable<K>, E> {
	private SkipNode<K, E> head;
	private int level;
	private int size;
	static private Random ran = new Random(); // Hold the Random class object

	public SkipList() {
		head = new SkipNode<K, E>(null, null, 0);
		level = -1;
		size = 0;
	}

	// Return the (first) matching matching element if one exists, null
	// otherwise
	public E find(K key) {
		SkipNode<K, E> x = head; // Dummy header node
		for (int i = level; i >= 0; i--) // For each level...
			while ((x.forward[i] != null) && (x.forward[i].key().compareTo(key) < 0)) // go forward
				x = x.forward[i]; // Go one last step
		x = x.forward[0]; // Move to actual record, if it exists
		if ((x != null) && (x.key().compareTo(key) == 0))
			return x.element(); // Got it
		else
			return null; // Its not there
	}

	// Normal Traversal of the SkipList to find the first rectangle that matches the
	// input
	public K find(E elem) {
		SkipNode<K, E> x = head; // pointer to header node
		while ((x.forward[0] != null) && (!x.forward[0].element().equals(elem)))
			x = x.forward[0];

		// If x is null, then the elem was not found
		if ((x != null) && (x.element().equals(elem)))
			return x.key();
		else
			return null;
	}

	// Pick a level using a geometric distribution
	int randomLevel() {
		int lev;
		for (lev = 0; Math.abs(ran.nextInt()) % 2 == 0; lev++) // ran is random
																// generator
			; // Do nothing
		return lev;
	}

	/** Insert a key, element pair into the skip list */
	public void insert(K key, E elem) {
		int newLevel = randomLevel(); // New node's level
		if (newLevel > level) // If new node is deeper
			adjustHead(newLevel); // adjust the header
		// Track end of level
		SkipNode<K, E>[] update = new SkipNode[level + 1];
		SkipNode<K, E> x = head; // Start at header node
		for (int i = level; i >= 0; i--) { // Find insert position
			while ((x.forward[i] != null) && (x.forward[i].key().compareTo(key) < 0))
				x = x.forward[i];
			update[i] = x; // Track end at level i
		}
		x = new SkipNode<K, E>(key, elem, newLevel);
		for (int i = 0; i <= newLevel; i++) { // Splice into list
			x.forward[i] = update[i].forward[i]; // Who x points to
			update[i].forward[i] = x; // Who points to x
		}
		size++; // Increment dictionary size
	}

	private void adjustHead(int newLevel) {
		SkipNode<K, E> temp = head;
		head = new SkipNode<K, E>(null, null, newLevel);
		for (int i = 0; i <= level; i++)
			head.forward[i] = temp.forward[i];
		level = newLevel;
	}

	// Removes a specific key and data pair
	public boolean remove(K key) {

		// Check if the key is in the SkipList
		if (this.find(key) == null)
			return false;

		// Track end of level
		SkipNode<K, E>[] update = new SkipNode[level + 1];
		SkipNode<K, E> x = head; // Pointer to head
		// Gets the pointer to the Node before the key at each level
		for (int i = level; i >= 0; i--) {
			while ((x.forward[i] != null) && (x.forward[i].key().compareTo(key) < 0))
				x = x.forward[i];
			update[i] = x;
		}

		// Disconnect all pointers to the key that will be removed
		// And make the previous node point to the removed key's pointers
		for (int i = level; i >= 0; i--) {
			// Check if the Node at the level of the update array points to the key
			if ((update[i].forward[i] != null) && (update[i].forward[i].key().compareTo(key) == 0)) {
				// Set the forward pointer to whatever the key was pointing to
				update[i].forward[i] = update[i].forward[i].forward[i];
			}

		}

		return true;
	}

	// Removes a specific KVPair given the value
	public boolean remove(E elem) {
		// Check if the element is in the SkipList
		K key = this.find(elem);
		if (key == null)
			return false;

		// Track end of level
		SkipNode<K, E>[] update = new SkipNode[level + 1];
		SkipNode<K, E> x = head; // Pointer to head
		// Gets the pointer to the Node before the key at each level
		for (int i = level; i >= 0; i--) {
			while ((x.forward[i] != null) && (x.forward[i].key().compareTo(key) < 0))
				x = x.forward[i];

			// At this point, x should be the node right before the desired key, however
			// since there are duplicate keys with different values, we must check that we
			// choose the right KVPair
			while ((x.forward[i] != null) && (x.forward[i].key().compareTo(key) == 0)) {
				// Check if the next value is the target element
				if (x.forward[i].element().equals(elem))
					break;

				x = x.forward[i];
			}
			update[i] = x;
		}

		// Disconnect all pointers to the key that will be removed
		// And make the previous node point to the removed key's pointers
		for (int i = level; i >= 0; i--) {
			// Check if the Node at the level of the update array points to the key
			if ((update[i].forward[i] != null) && (update[i].forward[i].key().compareTo(key) == 0)
					&& (update[i].forward[i].element().equals(elem))) {
				// Set the forward pointer to whatever the key was pointing to
				update[i].forward[i] = update[i].forward[i].forward[i];
			}

		}

		return true;

	}

	// Searches and prints the keys and values of a given key
	public String search(K key) {
		String s = "";

		// If not found then return empty string
		if (this.find(key) == null)
			return s;

		// If the key exists, find the first instance
		SkipNode<K, E> x = head; // Dummy header node
		for (int i = level; i >= 0; i--) // For each level...
			while ((x.forward[i] != null) && (x.forward[i].key().compareTo(key) < 0)) // go forward
				x = x.forward[i]; // Go one last step
		x = x.forward[0]; // Move to actual record, if it exists
		
		// Print all nodes with the key
		while (x != null && x.key().compareTo(key) == 0) {
			s += x.toString() + "\n";
			x = x.forward[0];
		}

		return s;
	}
}
