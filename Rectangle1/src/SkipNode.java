/**
 * * SkipNode documentation made by OpenDSA in chapter 15.1
 * 
 * @author OpenDSA
 * @version 15.1
 * 
 *          Modified by Patrick Marlow
 * 
 * @param <K>
 *            The key
 * @param <E>
 *            The element/value
 */
class SkipNode<K extends Comparable<K>, E> {
    private KVPair<K, E> rec;
    private SkipNode<K, E>[] forward;

    /**
     * Returns the element
     * 
     * @return
     *         the element
     */
    public E element() {
        return rec.value();
    }


    /**
     * Returns the key
     * 
     * @return
     *         the key
     */
    public K key() {
        return rec.key();
    }


    /**
     * Constructor for the ndoe
     * 
     * @param key
     *            The key
     * @param elem
     *            the element
     * @param level
     *            The maximum level to be saved in the SkipList
     */
    @SuppressWarnings("unchecked")
    public SkipNode(K key, E elem, int level) {
        rec = new KVPair<K, E>(key, elem);
        forward = new SkipNode[level + 1];
        for (int i = 0; i < level; i++)
            forward[i] = null;
    }


    /**
     * Converts the Node to a string
     * 
     * @return
     *         The string
     */
    public String toString() {
        return rec.toString();
    }


    /**
     * Returns the forward array
     * 
     * @return
     *         Forward
     */
    public SkipNode<K, E>[] getForward() {
        return forward;
    }
}
