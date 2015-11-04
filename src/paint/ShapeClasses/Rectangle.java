package paint.ShapeClasses;

import java.awt.Graphics;
/**
 * @author Christian Sieh
 * 
 * This class handles the data storage, drawing, movement, and outputting a 
 * string of information for a Rectangle shape and extends the Shape class.
 */
public class Rectangle extends Shape
{

    /**
     * This constructor will pass the 4 coordinates provided up to the Shape
     * class in order to initialize a new rectangle class.
     * 
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     */
    public Rectangle(int x1, int x2, int y1, int y2){
        super(x1, x2, y1, y2);   
    }
    
    /**
     * This function will take the color of the object, the two points, and
     * then use those two points to determine the width and height of the
     * rectangle. Finally it will draw the rectangle to the screen.
     * 
     * @param g
     */
    public void draw(Graphics g){
        g.setColor(shapeColor);
        g.drawRect(upperLeftX, upperLeftY, lowerRightX - upperLeftX, 
                lowerRightY - upperLeftY);
    }
    
    /**
     *
     */
    public void move(){

    }
    
    /**
     * @author: Christian Sieh
     * 
     * Method that will output the information about the rectangle shape as a 
     * string
     */
    public String toString(){
        return String.format("Shape: Rectangle, Coordinates: (%d,%d) (%d,%d), "
                + "Width: %d, Height: %d, Color: %s", upperLeftX, upperLeftY, 
                lowerRightX, lowerRightY, lowerRightX - upperLeftX,
                lowerRightY - upperLeftY, shapeColor);
    }
}
