/**
 * Class that holds and dimensions of a Rectangle
 * 
 * @author Patrick Marlow (patrickmarlow)
 * @version 2022-4-28
 */
public class Rect {

    private int x;
    private int y;
    private int width;
    private int height;

    /**
     * The Rectangle constructor
     * 
     * @param x1
     *            x coordinate
     * @param y1
     *            y coordinate
     * @param w
     *            The width
     * @param h
     *            The height
     */
    public Rect(int x1, int y1, int w, int h) {

        // Set the parameters of the rectangle
        x = x1;
        y = y1;
        width = w;
        height = h;
    }


    /**
     * Copies a rectangle into the created Rectangle (Param Constructor)
     * 
     * @param rectangle
     *            The rectangle to be copied
     */
    public Rect(Rect rectangle) {
        x = rectangle.getX();
        y = rectangle.getY();
        width = rectangle.getWidth();
        height = rectangle.getHeight();
    }


    /**
     * Changes the parameters of the rectangle
     * 
     * @param x1
     *            the x coordinate
     * @param y1
     *            the y coordinate
     * @param w
     *            the width
     * @param h
     *            the height
     */
    public void setParam(int x1, int y1, int w, int h) {
        // Set the parameters of the rectangle
        x = x1;
        y = y1;
        width = w;
        height = h;
    }


    /**
     * Returns the x coordinate
     * 
     * @return
     *         The x coordinate
     */
    public int getX() {
        return x;
    }


    /**
     * Returns the y coordinate
     * 
     * @return
     *         the y coordinate
     */
    public int getY() {
        return y;
    }


    /**
     * Retures the width
     * 
     * @return
     *         the width
     */
    public int getWidth() {
        return width;
    }


    /**
     * Returns the height
     * 
     * @return
     *         the height
     */
    public int getHeight() {
        return height;
    }


    /**
     * Overrides the toString operator for objects
     * 
     * @return
     *         the string
     */
    @Override
    public String toString() {
        String s = "";
        s += x + ", ";
        s += y + ", ";
        s += width + ", ";
        s += height + "";
        return s;
    }


    /**
     * Overrides the equals operator
     * 
     * @return
     *         True if equal, false if not
     */
    @Override
    public boolean equals(Object o) {

        // Check if we are comparing the same object to itself
        if (this == o)
            return true;

        // Make sure the object is an instance of Rect
        if (!(o instanceof Rect))
            return false;

        // If the object is an instance of Rect, then pass it as Rect
        Rect r = (Rect)o;

        return (x == r.getX() && y == r.getY() && width == r.getWidth()
            && height == r.getHeight());
    }


    /**
     * Checks if the rectangle is within a certain boundary
     * 
     * @param worldboxlim
     *            The limit
     * @return
     *         True if it is, false if not
     */
    public boolean isValid(int worldboxlim) {

        // Check coordinates
        if (x < 0 || x > worldboxlim)
            return false;
        if (y < 0 || y > worldboxlim)
            return false;

        if (width <= 0 || height <= 0)
            return false;

        // Check boundries
        if (x + width > worldboxlim)
            return false;

        return (!(y + height > worldboxlim));

    }


    /**
     * Checks if the given rectangle intersects this rectangle
     * 
     * @param r
     *            The other rectangle
     * @return
     *         True if it intersects, False if not
     */
    public boolean isIntersection(Rect r) {

        boolean intersect = true;

        if (x + width < r.x || x > r.x + r.width)
            intersect = false;

        if (y > r.y + r.height || y + height < r.y)
            intersect = false;

        // Check for borders
        if (x + width == r.x)
            if (r.x + r.width > x + width)
                intersect = false;

        if (x == r.x + r.width)
            if (x + width > r.x + r.width)
                intersect = false;

        if (y + height == r.y)
            if (r.y + r.height > y + height)
                intersect = false;
        if (y == r.y + r.height)
            if (y + height > r.y + r.height)
                intersect = false;

        return intersect;
    }
}
