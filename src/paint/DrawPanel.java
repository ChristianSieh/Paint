/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import paint.ShapeClasses.*;

/** DrawPanel class
 *  This class configures the drawing panel to support mouse events.
 */
public class DrawPanel extends JPanel implements MouseListener
{
    private int x1 = 0, y1 = 0, x2 = 0, y2 = 0, size;
    private boolean leftButtonPress = false, rightButtonPress = false;
    private int offsetX = 0, offsetY = 0;
    private ArrayList<Shape> shapeList = new ArrayList<>();
    private ArrayList<Shape> undoList = new ArrayList<>();

    // constructor: set up window
    public DrawPanel()
    {
        // detect mouse click events
        addMouseListener( this );       
    }

    // must override the following MouseListener methods
    public void mouseClicked( MouseEvent event ) { }
    public void mouseEntered( MouseEvent event ) { }
    public void mouseExited( MouseEvent event ) { }

    /**
     * @author Joseph Mowry 
     * @author Christian Sieh
     * 
     * A method to handle a mouse click event. It retrieves
     * coordinates by the clicking position and determines whether
     * it is a left or right click, setting the flag for drawing or 
     * moving a shape respectively.
     * 
     * @param event
     */
    public void mousePressed( MouseEvent event )
    {
        // check for left mouse button press
        if ( SwingUtilities.isLeftMouseButton( event ) )
        {
            x1 = event.getX();
            y1 = event.getY();
            //System.out.println( "Mouse left button click: (" + x1 + "," + y1 + ")" );
            leftButtonPress = true;
        }
        if ( SwingUtilities.isRightMouseButton(event))
        {
            x1 = event.getX();
            y1 = event.getY();
            //System.out.println( "Mouse right button click: (" + x1 + "," + y1 + ")" );
            rightButtonPress = true;
        }
    }
  
    /**
     * @author Joseph Mowry
     * 
     * A method to handle a mouse release event. It retrieves
     * coordinates by the clicking position and determines whether
     * it is a left or right click, resetting the flag for drawing or 
     * moving a shape respectively and calling the proper submethods to carry
     * out the desired action.
     * 
     * @param event
     */
    public void mouseReleased( MouseEvent event )
    {
        if ( leftButtonPress )
        {
            x2 = event.getX();
            y2 = event.getY();
            //System.out.println( "Mouse left button release: (" + x2 + "," + y2 + ")" );
            leftButtonPress = false;
            
            if(x2 < x1){
                int temp = x2;
                x2 = x1;
                x1 = temp;
                temp = y2;
                y2 = y1;
                y1 = temp;
            }
            
            switch(MenuFrame.selectedShape)
            {
            case ("Line"):
                shapeList.add(new Line(x1, x2, y1, y2));
                break;
                
            case ("Rectangle"):
                shapeList.add(new Rectangle(x1, x2, y1, y2));
                break;
                
            case ("Filled Rectangle"):
                shapeList.add(new FilledRectangle(x1, x2, y1, y2));
                break;
                
                case ("Ellipse"):
                shapeList.add(new Ellipse(x1, x2, y1, y2));
                break;
                
            case ("Filled Ellipse"):
                shapeList.add(new FilledEllipse(x1, x2, y1, y2));
                break;    
                
            default:
                shapeList.add(new Line(x1, x2, y1, y2));
                break;
            }
            repaint();
        }
        if ( rightButtonPress ){
            x2 = event.getX();
            y2 = event.getY();
            //System.out.println( "Mouse right button release: (" + x2 + "," + y2 + ")" );
            
            rightButtonPress = false;
            //determine closest shape
            int index = findClosestShape();
            
            if(index == -1)
            {
                return;
            }
            
            //move the shape at that index
            Shape temp = shapeList.get(index);
            shapeList.remove(index);
            shapeList.add(temp);

            //update coords
            shapeList.get(shapeList.size()-1).setCoords(x2, y2);
            shapeList.get(shapeList.size()-1).setCenter();
            
            repaint();
        }
    }

    /**
     * @author Joseph Mowry
     * 
     * This scans the list of shapes and determines the closest one to the
     * clicked location, returning an index to that shape. If no such shape
     * is found that is within the acceptable distance threshold (35 px), the
     * function returns a -1. Optional debug printing is commented out, but
     * left in for future review.
     * 
     * 
     * @return the index of the closest shape
     */
    public int findClosestShape(){
        double min = Double.MAX_VALUE;
        double currentResult = 0;
        int minIndex = -1;
        int i = 0;
        
        for(i = 0; i < shapeList.size(); i++){
//            System.out.println( "shape " + i + " centerX: " + shapeList.get(i).centerX);
//            System.out.println( "shape " + i + " centerY: " + shapeList.get(i).centerY);
            currentResult = euclideanDistance(shapeList.get(i).centerX, shapeList.get(i).centerY, x1, y1);
//            System.out.println( "distance: " + currentResult);

            if(currentResult < 50)
            {
                if(currentResult < min){
                    min = currentResult;
                    minIndex = i;
                }
            }
        }
        //System.out.println( "index of closest: " + minIndex);
        return minIndex;
    }
    
    /**
     * @author Joseph Mowry
     * 
     * A simple method to calculate the Euclidean distance between two
     * coordinates. Certain methods are imported from the math package for
     * ease of use.
     * 
     * @param centerX
     * @param centerY
     * @param x2
     * @param y2
     * 
     * @return floating point distance between the two points
     */
    public double euclideanDistance(double centerX, double centerY, double x2, double y2){
        return sqrt(pow((centerX-x2),2) + pow((centerY-y2),2));
    }
    /**
     * @author Christian Sieh
     * 
     * The display callback function that draws each shape.
     * 
     * @param g the graphics object
     */
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        for(Shape x : shapeList)
        {
            x.draw(g);
        }
    }
    /**
     * @author Christian Sieh
     * 
     * Deletes the most recent shape.
     * 
     */
    public void delete()
    {
        if ( shapeList.isEmpty())
            return;
        Shape temp = shapeList.remove(shapeList.size() - 1);
        
        if(undoList.size() != 10)
            undoList.add(temp);
        else
        {
            undoList.remove(0);
            undoList.add(temp);
        }
        repaint();
    }
    
    /**
     * @author Christian Sieh
     * 
     * Clears the list of shapes.
     * 
     */
    public void clear()
    {
        if ( shapeList.isEmpty())
            return;
        shapeList.clear();
        repaint();
    }
    
    /**
     * @author Christian Sieh
     * 
     * Maintains the list of shapes to be "undone" when called upon.
     * 
     */
    public void undo()
    {
        if(undoList.isEmpty())
            return;
        shapeList.add(undoList.get(undoList.size() - 1));
        undoList.remove(undoList.size() - 1);
        repaint();
    }
}
