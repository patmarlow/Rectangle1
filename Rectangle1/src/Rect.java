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

	public Rect(int x1, int y1, int w, int h) {

		// Set the parameters of the rectangle
		x = x1;
		y = y1;
		width = w;
		height = h;
	}

	public Rect(Rect rectangle) {
		x = rectangle.getX();
		y = rectangle.getY();
		width = rectangle.getWidth();
		height = rectangle.getHeight();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	// Overrides the toString operator used in KVPair
	@Override
	public String toString() {
		String s = null;
		s += x + ", ";
		s += y + ", ";
		s += width + ", ";
		s += height + "";
		return s;
	}

	@Override
	public boolean equals(Object o) {

		// Check if we are comparing the same object to itself
		if (this == o)
			return true;

		// Make sure the object is an instance of Rect
		if (!(o instanceof Rect))
			return false;

		// If the object is an instance of Rect, then pass it as Rect
		Rect r = (Rect) o;

		return (x == r.getX() && y == r.getY() && width == r.getWidth() && height == r.getHeight());
	}

}
