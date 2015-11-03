package paint.ShapeClasses;

import java.awt.Graphics;

/**
 * @author Christian Sieh
 * 
 * This class handles the data storage, drawing, movement, and outputting a 
 * string of information for a Filled Rectangle shape and extends the Rectangle
 * class.
 */
public class FilledRectangle extends Rectangle
{

    /**
     * This constructor will pass the 4 coordinates provided up to the Shape
     * class in order to initialize a new filled rectangle class.
     * 
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     */
    public FilledRectangle(int x1, int x2, int y1, int y2){
        super(x1, x2, y1, y2); 
    }
    
    /**
     * This function will take the color of the object, the fill color of the
     * object, the two points, and then use those two points to determine the 
     * width and height of the filled ellipse. Finally it will draw the filled 
     * ellipse to the screen.
     * 
     * @param g
     */
    public void draw(Graphics g){
        g.setColor( fillColor );
        g.fillRect(upperLeftX, upperLeftY, lowerRightX - upperLeftX, 
                lowerRightY - upperLeftY);
        g.setColor( shapeColor );
        g.drawRect(upperLeftX, upperLeftY, lowerRightX - upperLeftX, 
                lowerRightY - upperLeftY);
    }
    
    /**
     *
     */
    public void move(){
        //stuff
    }
    
    /**
     * @author: Christian Sieh
     * 
     * Method that will output the information about the filled ellipse shape as
     * a string
     */
    public String toString(){
        return String.format("Shape: Filled Rectangle, Coordinates: (%d,%d) "
                + "(%d,%d), Width: %d, Height: %d, Outline Color: %s, "
                + "Fill Color: %s", upperLeftX, upperLeftY, lowerRightX, 
                lowerRightY, lowerRightX - upperLeftX, lowerRightY - upperLeftY,
                shapeColor, fillColor);
    }
}
