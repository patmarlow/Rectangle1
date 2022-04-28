/**
 * Class that holds the name and dimensions of a Rectangle
 * 
 * @author Patrick Marlow (patrickmarlow)
 * @version 2022-4-28
 */
public class Rect {

    private String name;

    private int x;
    private int y;
    private int width;
    private int height;

    public Rect(String rectname, int x1, int y1, int w, int h) {        
        
        // Set the parameters of the rectangle
        x = x1;
        y = y1;
        width = w;
        height = h;

        name = rectname;
    }
    
    public Rect(Rect rectangle) {
        x = rectangle.getX();
        y = rectangle.getY();
        width = rectangle.getWidth();
        height = rectangle.getHeight();
        
        name = rectangle.getName();
    }


    public String getName() {
        return name;
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

}
