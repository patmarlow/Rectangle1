/**
 * KVPair Implementation from OpenDSA
 * 
 * @author OpenDSA
 *
 * @version 15.1
 *
 * @param <K>
 *            The Key
 * @param <E>
 *            The Value
 */
public class KVPair<K extends Comparable<K>, E>
    implements Comparable<KVPair<K, E>> {
    private K theKey;
    private E theVal;

    /**
     * The Constructor
     * 
     * @param k
     *            the key
     * @param v
     *            the value
     */
    KVPair(K k, E v) {
        theKey = k;
        theVal = v;
    }


    /**
     * Compare KVPairs
     * 
     * @param it
     *            The Pair to be compared to
     * 
     * @return
     *         The result of compareTo
     */
    public int compareTo(KVPair<K, E> it) {
        return theKey.compareTo(it.key());
    }


    /**
     * Compare against a key
     * 
     * @param it
     *            the key
     * @return
     *         The result of compareTo
     */
    public int compareTo(K it) {
        return theKey.compareTo(it);
    }


    /**
     * Returns the key
     * 
     * @return
     *         the key
     */
    public K key() {
        return theKey;
    }


    /**
     * Returns the value
     * 
     * @return
     *         the value
     */
    public E value() {
        return theVal;
    }


    /**
     * Converts the Pair to a string
     * 
     * @return
     *         The string
     */
    public String toString() {
        String s = "(";
        if (theKey != null) {
            s += theKey.toString();
        }
        else {
            s += "null";
        }
        s += ", ";
        if (theVal != null) {
            s += theVal.toString();
        }
        else {
            s += "null";
        }
        s += ")";
        return s;
    }
}
