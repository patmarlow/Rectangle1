import student.TestCase;

/**
 * @author Patrick Marlow (patrickmarlow)
 * @version 2022.05.04
 *
 */
public class SkipListTest extends TestCase {

    /**
     * Tests insert
     */
    public void testInsert() {
        // Test insertion and dumps
        SkipList<String, Rect> sl = new SkipList<String, Rect>();

        Rect temp = new Rect(0, 0, 5, 5);

        sl.insert("a", temp);

        temp = new Rect(0, 0, 10, 10);

        sl.insert("b", temp);

        temp = new Rect(10, 10, 20, 20);

        sl.insert("c", temp);

        assertTrue(!sl.search("a").isBlank());
        assertTrue(!sl.search("b").isBlank());
        assertTrue(!sl.search("c").isBlank());
    }


    /**
     * Tests remove
     */
    public void testRemove() {
        // Test insertion and dumps
        SkipList<String, Rect> sl = new SkipList<String, Rect>();

        Rect temp = new Rect(0, 0, 5, 5);

        sl.insert("a", temp);

        temp = new Rect(0, 0, 10, 10);

        sl.insert("b", temp);

        temp = new Rect(10, 10, 20, 20);

        sl.insert("c", temp);

        sl.remove("a");
        sl.remove(temp);

        assertTrue(sl.search("a").isBlank());
        assertTrue(!sl.search("b").isBlank());
        assertTrue(sl.search("c").isBlank());
    }
}
