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
    //TODO: set up a size depending on the shape
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

    // mouse button press events (start drawing)
    public void mousePressed( MouseEvent event )
    {
        // check for left mouse button press
        if ( SwingUtilities.isLeftMouseButton( event ) )
        {
            x1 = event.getX();
            y1 = event.getY();
            System.out.println( "Mouse left button click: (" + x1 + "," + y1 + ")" );
            leftButtonPress = true;
        }
        if ( SwingUtilities.isRightMouseButton(event))
        {
            x1 = event.getX();
            y1 = event.getY();
            System.out.println( "Mouse right button click: (" + x1 + "," + y1 + ")" );
            rightButtonPress = true;
        }
    }
  
    // mouse button release events (finish drawing)
    public void mouseReleased( MouseEvent event )
    {
        if ( leftButtonPress )
        {
            x2 = event.getX();
            y2 = event.getY();
            System.out.println( "Mouse left button release: (" + x2 + "," + y2 + ")" );
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
            System.out.println( "Mouse right button release: (" + x2 + "," + y2 + ")" );
            
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
            shapeList.get(shapeList.size()-1).setCenter(x1, y1, x2, y2);
            
            repaint();
        }
    }
    
    public void moveSquare(int x, int y){
        
    }
    
    public int findClosestShape(){
        double min = Double.MAX_VALUE;
        double currentResult = 0;
        int minIndex = -1;
        int i = 0;
        
        for(i = 0; i < shapeList.size(); i++){
            System.out.println( "shape " + i + " centerX: " + shapeList.get(i).centerX);
            System.out.println( "shape " + i + " centerY: " + shapeList.get(i).centerY);
            currentResult = euclideanDistance(shapeList.get(i).centerX, shapeList.get(i).centerY, x1, y1);
            System.out.println( "distance: " + currentResult);

            if(currentResult < 25)
            {
                if(currentResult < min){
                    min = currentResult;
                    minIndex = i;
                }
            }
        }
        System.out.println( "index of closest: " + minIndex);
        return minIndex;
    }
    
    public double euclideanDistance(double centerX, double centerY, double x2, double y2){
        return sqrt(pow((centerX-x2),2) + pow((centerY-y2),2));
    }

    // paintComponent() is the display callback function
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        for(Shape x : shapeList)
        {
            x.draw(g);
        }
    }
    
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
    
    public void clear()
    {
        if ( shapeList.isEmpty())
            return;
        shapeList.clear();
        repaint();
    }
    
    
    public void undo()
    {
        if(undoList.isEmpty())
            return;
        shapeList.add(undoList.get(undoList.size() - 1));
        undoList.remove(undoList.size() - 1);
        repaint();
    }
}
