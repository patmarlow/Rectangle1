import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The class containing the main method.
 *
 * @author Patrick Marlow (patrickmarlow)
 * @version 2022-4-28
 */

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

/**
 * The Rectangle Class
 * 
 * @author Patrick Marlow (patrickmarlow)
 * @version 5-4-2022
 *
 */
public class Rectangle1 {

    private static final int WORLDBOXLIMIT = 1024;

    private SkipList<String, Rect> sl;

    /**
     * Defualt constructor
     */
    public Rectangle1() {
        // Create a skiplist holding strings as keys and rectangles as values
        sl = new SkipList<String, Rect>();
    }


    /**
     * Main Function
     * 
     * @param args
     *            The arguments of the program call
     */
    public static void main(String[] args) {

        String filename = args[0];

        Rectangle1 r1 = new Rectangle1();

        try {
            r1.parseandeval(filename);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Parses and Evaluates the file
     * 
     * @param filename
     *            The name of the file to evaluate
     * @throws FileNotFoundException
     *             If file is not found
     * @throws IOException
     *             If something went wrong with reading the file
     */
    public void parseandeval(String filename)
        throws FileNotFoundException,
        IOException {

        // Try opening the file
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(
            filename).getAbsolutePath()))) {

            String line = "";

            // Continues until at EOF
            while ((line = reader.readLine()) != null) {

                // Replaces any \t character with spaces
                line = line.replaceAll("\t", " ");

                // If the line is blank then do nothing
                if (!line.isBlank()) {

                    // Clean the input by getting rid of leading and trailing
                    // blank spaces
                    line = line.trim();

                    // get the command
                    String command;

                    // if there is only one input then use it
                    if (line.indexOf(' ') != -1)
                        command = line.substring(0, line.indexOf(' '));
                    else
                        command = line;

                    // If else structure

                    // 0 Parameters
                    if (command.equals("dump")) {
                        System.out.println("SkipList dump:");
                        System.out.print(dump());
                        continue;
                    }

                    if (command.equals("intersections")) {
                        System.out.println("Intersection pairs:");

                        // If there is an output then print it
                        String s = intersections();
                        if (!s.isBlank())
                            System.out.print(s);
                    }

                    // Check how many parameters to the command there are
                    line = line.trim();

                    String modifier = "";
                    // Grabs a modifier to the command (if there is one)
                    if (line.indexOf(' ') != -1)
                        line = line.substring(line.indexOf(' '));
                    line = line.trim();

                    if (line.indexOf(' ') != -1)
                        modifier = line.substring(0, line.indexOf(' '));
                    else
                        modifier = line;

                    line = line.trim();

                    // Grabs a modifier to the command (if there is one)
                    if (line.indexOf(' ') != -1)
                        line = line.substring(line.indexOf(' '));
                    line = line.trim();

                    // If there are no more arguments then the modifier is a
                    // name
                    if (line.indexOf(' ') == -1) {
                        // Remove
                        if (command.equals("remove")) {
                            // Pointer to node
                            SkipNode<String, Rect> ptr = remove(modifier);

                            // If the node was not found
                            if (ptr == null) {
                                System.out.print("Rectangle not removed: ");
                                System.out.println(modifier);

                            }
                            else {
                                System.out.print("Rectangle removed: ");
                                System.out.println(ptr.toString());
                            }

                        }

                        // Search
                        if (command.equals("search")) {
                            String out = search(modifier);

                            if (out.isBlank())
                                System.out.println("Rectangle not found: "
                                    + modifier);
                            else {
                                System.out.println("Rectangles found:");
                                System.out.print(out);
                            }
                        }
                    }

                    // command has multiple parameters
                    else {
                        // Insert
                        if (command.equals("insert")) {
                            String name = modifier;
                            int x;
                            int y;
                            int w;
                            int h;

                            if (line.indexOf(' ') != -1)
                                modifier = line.substring(0, line.indexOf(' '));
                            else
                                modifier = line;
                            line = line.trim();

                            x = Integer.parseInt(modifier);

                            // Grabs a modifier to the command (if there is one)
                            if (line.indexOf(' ') != -1)
                                line = line.substring(line.indexOf(' '));
                            line = line.trim();
                            if (line.indexOf(' ') != -1)
                                modifier = line.substring(0, line.indexOf(' '));
                            else
                                modifier = line;
                            line = line.trim();

                            y = Integer.parseInt(modifier);

                            // Grabs a modifier to the command (if there is one)
                            if (line.indexOf(' ') != -1)
                                line = line.substring(line.indexOf(' '));
                            line = line.trim();
                            if (line.indexOf(' ') != -1)
                                modifier = line.substring(0, line.indexOf(' '));
                            else
                                modifier = line;
                            line = line.trim();

                            w = Integer.parseInt(modifier);

                            // Grabs a modifier to the command (if there is one)
                            if (line.indexOf(' ') != -1)
                                line = line.substring(line.indexOf(' '));
                            line = line.trim();
                            if (line.indexOf(' ') != -1)
                                modifier = line.substring(0, line.indexOf(' '));
                            else
                                modifier = line;
                            line = line.trim();

                            h = Integer.parseInt(modifier);

                            // Make a Rect
                            Rect rec = new Rect(x, y, w, h);

                            // Check if the rectangle is valid
                            SkipNode<String, Rect> temp =
                                new SkipNode<String, Rect>(name, rec, 0);

                            if (!insert(name, rec)) {
                                System.out.println("Rectangle rejected: " + temp
                                    .toString());
                            }
                            else
                                System.out.println("Rectangle inserted: " + temp
                                    .toString());
                        }
                        // Remove
                        else if (command.equals("remove")) {
                            int x;
                            int y;
                            int w;
                            int h;

                            x = Integer.parseInt(modifier);

                            // Grabs a modifier to the command (if there is one)
                            if (line.indexOf(' ') != -1)
                                modifier = line.substring(0, line.indexOf(' '));
                            else
                                modifier = line;
                            line = line.trim();

                            y = Integer.parseInt(modifier);

                            // Grabs a modifier to the command (if there is one)
                            if (line.indexOf(' ') != -1)
                                line = line.substring(line.indexOf(' '));
                            line = line.trim();
                            if (line.indexOf(' ') != -1)
                                modifier = line.substring(0, line.indexOf(' '));
                            else
                                modifier = line;
                            line = line.trim();

                            w = Integer.parseInt(modifier);

                            // Grabs a modifier to the command (if there is one)
                            if (line.indexOf(' ') != -1)
                                line = line.substring(line.indexOf(' '));
                            line = line.trim();
                            if (line.indexOf(' ') != -1)
                                modifier = line.substring(0, line.indexOf(' '));
                            else
                                modifier = line;
                            line = line.trim();

                            h = Integer.parseInt(modifier);

                            Rect rec = new Rect(x, y, w, h);

                            if (!rec.isValid(WORLDBOXLIMIT)) {
                                System.out.println("Rectangle rejected: (" + rec
                                    .toString() + ")");
                                continue;
                            }

                            SkipNode<String, Rect> temp = remove(rec);

                            // If not found then print that it was not removed
                            if (temp == null)
                                System.out.println("Rectangle not removed: ("
                                    + rec.toString() + ")");
                            else
                                System.out.println("Rectangle removed: " + temp
                                    .toString());
                        }
                        // Region Search
                        else if (command.equals("regionsearch")) {
                            int x;
                            int y;
                            int w;
                            int h;

                            x = Integer.parseInt(modifier);

                            // Grabs a modifier to the command (if there is one)
                            if (line.indexOf(' ') != -1)
                                modifier = line.substring(0, line.indexOf(' '));
                            else
                                modifier = line;
                            line = line.trim();

                            y = Integer.parseInt(modifier);

                            // Grabs a modifier to the command (if there is one)
                            if (line.indexOf(' ') != -1)
                                line = line.substring(line.indexOf(' '));
                            line = line.trim();
                            if (line.indexOf(' ') != -1)
                                modifier = line.substring(0, line.indexOf(' '));
                            else
                                modifier = line;
                            line = line.trim();

                            w = Integer.parseInt(modifier);

                            // Grabs a modifier to the command (if there is one)
                            if (line.indexOf(' ') != -1)
                                line = line.substring(line.indexOf(' '));
                            line = line.trim();
                            if (line.indexOf(' ') != -1)
                                modifier = line.substring(0, line.indexOf(' '));
                            else
                                modifier = line;
                            line = line.trim();

                            h = Integer.parseInt(modifier);

                            Rect rec = new Rect(x, y, w, h);

                            boolean valid = true;

                            if (w <= 0 || h <= 0)
                                valid = false;

                            // If the rec is invalid then provide error
                            if (!valid) {
                                System.out.println("Rectangle rejected: (" + rec
                                    .toString() + ")");
                                continue;
                            }

                            String s = regionsearch(rec);
                            System.out.println(
                                "Rectangles intersecting region (" + rec
                                    .toString() + "):");
                            if (!s.isBlank()) {
                                System.out.print(s);
                            }
                        }
                    }

                }
            }

            reader.close();
        }
    }


    /**
     * Inserts into the SkipList
     * 
     * @param key
     *            The key to insert
     * @param r
     *            The rectangle to be inserted
     * @return
     *         True if it was inserted, false if the rectangle is incorrect
     */
    private boolean insert(String key, Rect r) {
        // Check that the rectangle is valid
        if (!r.isValid(WORLDBOXLIMIT))
            return false;

        sl.insert(key, r);

        return true;
    }


    /**
     * Removes a Node based off the key
     * 
     * @param key
     *            The key of the node to be removed
     * @return
     *         The node of the removed node
     */
    private SkipNode<String, Rect> remove(String key) {

        // Remove the node given the key
        return sl.remove(key);

    }


    /**
     * Removes a Node based off the Rectangle value
     * 
     * @param r
     *            The Rectangle to be searched and removed
     * @return
     *         The node of the removed node
     */
    private SkipNode<String, Rect> remove(Rect r) {

        // remove the node given the rectangle
        return sl.remove(r);

    }


    /**
     * Conducts the regionsearch
     * 
     * @param r
     *            The rectangle to be searched for intersections
     * @return
     *         The string of the output
     */
    private String regionsearch(Rect r) {
        // Put all of the intersections in a string
        String output = "";

        sl.resetIt(Iterator.OUTER);

        // Check for intersections
        while (sl.advanceIt(Iterator.OUTER)) {
            if (r.isIntersection(sl.getIt(Iterator.OUTER).element()))
                output += sl.getIt(Iterator.OUTER).toString() + "\n";
        }

        return output;
    }


    /**
     * Finds all of the intersections in the SkipList
     * 
     * @return
     *         The output
     */
    private String intersections() {
        String output = "";
        String substr = "";

        sl.resetIt(Iterator.OUTER);

        // Pointers to inner and outer Nodes to make visibility easier
        SkipNode<String, Rect> inner;
        SkipNode<String, Rect> outer;

        // Check each intersection
        while (sl.advanceIt(Iterator.OUTER)) {
            outer = sl.getIt(Iterator.OUTER);

            sl.resetIt(Iterator.INNER);

            // Compare each current node to every other node in the SkipList
            while (sl.advanceIt(Iterator.INNER)) {
                inner = sl.getIt(Iterator.INNER);
                substr = "";

                // Check that the current rectangle is not equal to the original
                // rectangle being compared to eliminate duplicates
                if (!outer.equals(inner)) {

                    // If they intersect then add it to the output
                    if (outer.element().isIntersection(inner.element())) {
                        substr += outer.toString();
                        substr += inner.toString();

                        substr = substr.replace(")(", " | ");
                        substr += "\n";

                        output += substr;
                    }
                }

            }
        }

        return output;

    }


    /**
     * Searches and prints all of the Nodes in the SkipList given a key
     * 
     * @return
     *         The output
     */
    private String search(String key) {
        String output = "";

        if (sl.getSize() == 0)
            return "";

        // Print all Strings with the key
        sl.resetIt(Iterator.OUTER);

        while (sl.advanceIt(Iterator.OUTER)) {
            if (sl.getIt(Iterator.OUTER).key().equals(key))
                output += sl.getIt(Iterator.OUTER).toString() + "\n";
        }

        return output;

    }


    /**
     * Prints all of the nodes in the SkipList
     * 
     * @return
     *         The output
     */
    public String dump() {
        String output = "";

        output += sl.dump();
        output += "SkipList size is: " + sl.getSize() + "\n";
        return output;
    }

}
