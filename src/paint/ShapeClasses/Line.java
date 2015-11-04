package paint.ShapeClasses;

import java.awt.Graphics;

/**
 * @author Christian Sieh
 * 
 * This class handles the data storage, drawing, movement, and outputting a 
 * string of information for a line shape and extends the Shape class.
 */
public class Line extends Shape 
{   

    /**
     * This constructor will pass the 4 coordinates provided up to the Shape
     * class in order to initialize a new line class.
     * 
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     */
    public Line(int x1, int x2, int y1, int y2){
        super(x1, x2, y1, y2);
    }
    
    /**
     * This function will take the color of the object and the two points and
     * then paint the line to the screen.
     * 
     * @param g
     */
    public void draw(Graphics g){
        g.setColor( shapeColor );
        g.drawLine( leftX, leftY, rightX, rightY );
    }
    
    /**
     *
     */
    public void move(){

    }
    
    /**
     * @author: Christian Sieh
     * 
     * Method that will output the information about the line shape as a string
     */
    public String toString(){
        return String.format("Shape: Line, Coordinates: (%d,%d) (%d,%d), "
                + "Color: %s", leftX, leftY, rightX, rightY, shapeColor);
    }
}
