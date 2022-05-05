import student.TestCase;

/**
 * @author Patrick Marlow (patrickmarlow)
 * @version 2022.05.04
 *
 */
public class RectTest extends TestCase {

    /**
     * Tests Rect
     */
    public void testConstructor() {
        Rect r1 = new Rect(0, 0, 5, 5);

        assertTrue(r1.equals(r1));

        Rect r2 = new Rect(r1);

        assertTrue(r2.equals(r1));

        Rect r3 = new Rect(1, 2, 3, 4);

        r2.setParam(1, 2, 3, 4);

        assertTrue(r2.equals(r3));
        assertFalse(r2.equals(r1));

        assertTrue(r2.isValid(1024));

        assertTrue(r2.getX() == 1);
        assertTrue(r2.getY() == 2);
        assertTrue(r2.getWidth() == 3);
        assertTrue(r2.getHeight() == 4);

        // Test isValid
        Rect r4 = new Rect(-1, -1, -1, -1);

        assertFalse(r4.isValid(10));

        r4.setParam(0, -1, -1, -1);

        assertFalse(r4.isValid(10));

        r4.setParam(0, 0, -1, -1);

        assertFalse(r4.isValid(10));

        r4.setParam(0, 0, 1, -1);

        assertFalse(r4.isValid(10));

        r4.setParam(10000, 10000, 10000, 10000);

        assertFalse(r4.isValid(1024));

        r4.setParam(0, 10000, 10000, 10000);

        assertFalse(r4.isValid(1024));

        r4.setParam(0, 1, 1024, 1024);

        assertFalse(r4.isValid(1024));

        r4.setParam(1, 0, 1024, 1024);

        assertFalse(r4.isValid(1024));
    }


    /**
     * Tests Intersections
     */
    public void testIntersection() {
        Rect r1 = new Rect(0, 0, 10, 10);
        Rect r2 = new Rect(0, 0, 20, 20);
        
        assertTrue(r1.isIntersection(r1));
        assertTrue(r1.isIntersection(r2));
        
        // Same height
        r2.setParam(0, 0, 20, 10);
        assertTrue(r1.isIntersection(r2));
        assertTrue(r2.isIntersection(r1));
        
        // Same width
        r2.setParam(0, 0, 10, 20);
        assertTrue(r1.isIntersection(r2));
        assertTrue(r2.isIntersection(r1));
        
        // Does not intersect at all
        r2.setParam(51, 23, 10, 20);
        assertFalse(r1.isIntersection(r2));
        assertFalse(r2.isIntersection(r1));
        
        // Overlapping x but not y
        r2.setParam(5, 23, 10, 20);
        assertFalse(r1.isIntersection(r2));
        assertFalse(r2.isIntersection(r1));
        
        // Overlapping y but not x
        r2.setParam(24, 5, 10, 20);
        assertFalse(r1.isIntersection(r2));
        assertFalse(r2.isIntersection(r1));
        
        // Same height but does not intersect
        r2.setParam(10, 0, 10, 10);
        assertFalse(r1.isIntersection(r2));
        assertFalse(r2.isIntersection(r1));
        
     // Same width but does not intersect
        r2.setParam(0, 10, 10, 10);
        assertFalse(r1.isIntersection(r2));
        assertFalse(r2.isIntersection(r1));
        
        // Intersecting corners but does not intersect
        r2.setParam(10, 10, 10, 10);
        assertFalse(r1.isIntersection(r2));
        assertFalse(r2.isIntersection(r1));
        
        // Intersections with each corner
        r1.setParam(10, 10, 10, 10);
        r2.setParam(5, 5, 10, 10);
        assertTrue(r1.isIntersection(r2));
        assertTrue(r2.isIntersection(r1));
        
        r2.setParam(5, 15, 10, 10);
        assertTrue(r1.isIntersection(r2));
        assertTrue(r2.isIntersection(r1));
        
        
    }
}































