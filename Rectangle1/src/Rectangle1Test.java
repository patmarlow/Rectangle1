import java.io.IOException;
import student.TestCase;

/**
 * 
 * Tests Rectangle1
 * 
 * @author Patrick Marlow (patrickmarlow)
 * @version 2022.05.04
 *
 */
public class Rectangle1Test extends TestCase {

    /**
     * Tests parse and evalutate
     *
     */
    public void testProcess() {
        Rectangle1 r1 = new Rectangle1();

        try {
            r1.parseandeval("SyntaxTest.txt");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        String output = "Rectangle rejected: (r_r, -1, -20, 3, 4)\n"
            + "Rectangle rejected: (rec, 7, -8, 1, 3)\n"
            + "Rectangle rejected: (virtual_rec0, 1, 1, 0, 0)\n"
            + "Rectangle rejected: (virtual_REC0, 0, 0, 11, 0)\n"
            + "Rectangle rejected: (inExistRec_0, 1, 1, -1, -2)\n"
            + "Rectangle rejected: (11, 11, 0, 0)\n"
            + "Intersection pairs:\n"
            + "SkipList dump:\n"
            + "Node has depth 1, Value (null)\n"
            + "SkipList size is: 0\n"
            + "Rectangle not found: r_r\n"
            + "Rectangle not removed: r_r\n"
            + "Rectangle rejected: (1, 1, 0, 0)\n"
            + "Rectangles intersecting region (-5, -5, 20, 20):";

        System.out.println();

        assertTrue(systemOut().getHistory().contains(output));

    }


    /**
     * Tests test1.txt file
     */
    public void testTest() {

        Rectangle1 r1 = new Rectangle1();

        try {
            r1.parseandeval("Test1.txt");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        String output = "Rectangle inserted: (a, 0, 0, 10, 10)\n"
            + "Rectangle inserted: (a, 51, 51, 125, 125)\n"
            + "Rectangle inserted: (b, 0, 0, 20, 20)\n"
            + "Rectangles intersecting region (1, 1, 5, 5):\n"
            + "(a, 0, 0, 10, 10)\n" + "(b, 0, 0, 20, 20)\n"
            + "Rectangles intersecting region (10, 10, 20, 20):\n"
            + "(b, 0, 0, 20, 20)\n" + "Intersection pairs:\n"
            + "(a, 0, 0, 10, 10 | b, 0, 0, 20, 20)\n"
            + "(b, 0, 0, 20, 20 | a, 0, 0, 10, 10)\n" + "SkipList dump:";

        String output1 = "SkipList size is: 3\n" + "Rectangles found:\n"
            + "(a, 51, 51, 125, 125)\n" + "(a, 0, 0, 10, 10)\n"
            + "Rectangle removed: (b, 0, 0, 20, 20)\n"
            + "Rectangle inserted: (c, 0, 20, 60, 10)";

        assertTrue(systemOut().getHistory().contains(output));
        assertTrue(systemOut().getHistory().contains(output1));

    }


    /**
     * Tests remove file
     */
    public void testRemove() {
        Rectangle1 r1 = new Rectangle1();

        try {
            r1.parseandeval("Test2.txt");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        String output = "Rectangle inserted: (a, 0, 0, 2, 2)\n"
            + "Rectangle inserted: (b, 0, 0, 5, 7)\n"
            + "Rectangle inserted: (Awd, 9, 125, 94, 22)\n"
            + "Rectangle inserted: (bruh, 609, 213, 5, 12)\n"
            + "Rectangle inserted: (a, 25, 12, 56, 212)\n"
            + "Rectangle inserted: (b, 14, 52, 125, 64)\n" + "SkipList dump:\n";

        String output1 = "SkipList size is: 6\n"
            + "Rectangle removed: (a, 25, 12, 56, 212)\n"
            + "Rectangle removed: (Awd, 9, 125, 94, 22)\n"
            + "Rectangle removed: (b, 14, 52, 125, 64)\n"
            + "Rectangle removed: (a, 0, 0, 2, 2)\n"
            + "Rectangle not removed: (14, 52, 125, 64)\n" + "SkipList dump:";

        String output2 = "SkipList size is: 2";

        assertTrue(systemOut().getHistory().contains(output));
        assertTrue(systemOut().getHistory().contains(output1));
        assertTrue(systemOut().getHistory().contains(output2));
    }

}
