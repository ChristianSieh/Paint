package paint.ShapeClasses;

import java.awt.Color;
import java.awt.Graphics;
import paint.MenuFrame;

/**
 * @author Christian Sieh
 * 
 * This class is the abstract class from which all other shapes will inherit
 * from. 
 */
public abstract class Shape {
    
    /**
     *
     */
    public int upperLeftX;

    /**
     *
     */
    public int upperLeftY;

    /**
     *
     */
    public int lowerRightX;

    /**
     *
     */
    public int lowerRightY;

    /**
     *
     */
    public int leftX;

    /**
     *
     */
    public int rightX;

    /**
     *
     */
    public int leftY;

    /**
     *
     */
    public int rightY;

    /**
     *
     */
    public Color shapeColor;

    /**
     *
     */
    public Color fillColor;

    /**
     *
     */
    public float centerX;

    /**
     *
     */
    public float centerY;

    /**
     * Default constructor used to initialize all of the variables.
     */
    public Shape(){
        upperLeftX = 0;
        upperLeftY = 0;
        lowerRightX = 0;
        lowerRightY = 0;
        leftX = 0;
        rightX = 0;
        leftY = 0;
        rightY = 0;
        shapeColor = Color.BLACK;
        fillColor = Color.BLACK;
        centerX = 0;
        centerY = 0;
    }
    
    /**
     * @author Christian Sieh
     * 
     * Constructor used when 4 coordinate values are passed in. Sets the upper
     * left corner of the shape, the colors, and the center point.
     * 
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     */
    public Shape(int x1, int x2, int y1, int y2){
        upperLeftX = min(x1, x2);
        upperLeftY = min(y1, y2);
        lowerRightX = max(x1, x2);
        lowerRightY = max(y1, y2);
        leftX = x1;
        rightX = x2;
        leftY = y1;
        rightY = y2;
        shapeColor = MenuFrame.outlineColor;
        fillColor = MenuFrame.fillColor;
        centerX = (x1 + x2) / 2;
        centerY = (y1 + y2) / 2;
    }
    
    /**
     * @author Christian Sieh
     * 
     * An abstract method that will be used to draw shapes.
     * 
     * @param g
     */
    public abstract void draw(Graphics g);

    /**
     * @author Christian Sieh
     * 
     * An abstract method that will be used to move shapes around on the screen.
     */
    public abstract void move();
    
    /**
     * @author Christian Sieh
     * 
     * An abstract method that will print information about each shape to the
     * console.
     * @return 
     */
    public abstract String toString();
    
    /**
     * @author Christian Sieh
     * 
     * A function that returns the minimum of the two integer values.
     * 
     * @param x1
     * @param x2
     * @return
     */
    public int min (int x1, int x2)
    {
        if ( x1 < x2 )
            return x1;
        return x2;
    }
    
    /**
     * @author Christian Sieh
     * 
     * A function that returns the maximum of the two integer values.
     * 
     * @param x1
     * @param x2
     * @return
     */
    public int max (int x1, int x2)
    {
        if ( x1 > x2 )
            return x1;
        return x2;
    }
}