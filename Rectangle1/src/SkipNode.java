/**
 * SkipNode documentation made by OpenDSA in chapter 15.1
 * 
 * @author OpenDSA
 * @version 15.1
 * 
 *          Modified by Patrick Marlow
 */
class SkipNode<K extends Comparable<K>, E> {
    private KVPair<K, E> rec;
    public SkipNode<K, E>[] forward;

    public E element() {
        return rec.value();
    }


    public K key() {
        return rec.key();
    }


    @SuppressWarnings("unchecked")
    public SkipNode(K key, E elem, int level) {
        rec = new KVPair<K, E>(key, elem);
        forward = new SkipNode[level + 1];
        for (int i = 0; i < level; i++)
            forward[i] = null;
    }


    public String toString() {
        return rec.toString();
    }
}
