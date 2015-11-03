/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
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
    private boolean leftButtonPress = false, dragging = false;
    private double offsetX = 0, offsetY = 0;
    private ArrayList<Shape> shapeList = new ArrayList<>();

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
//        if ( SwingUtilities.isRightMouseButton(event))
//        {
//            //TODO: this
//            double newX = event.getX();
//            double newY = event.getY();
//            
//            if(newX > x1 && newX < x2 && newY > y1 && newY < y2)
//            {
//                dragging = true;
//                offsetX = newX - x1;
//                offsetY = newY - y1;
//            }
//        }
    }

//    public void mouseDragged(MouseEvent event)
//    {
//        if(dragging)
//        {
//            double newX = event.getX();
//            double newY = event.getY();
//            
//            x1 = newX - offsetX;
//            y1 = newY - offsetY;
//            x2 = x1 + size;
//            y2 = y1 + size;
//            //TODO: get current closest shape
//            //repaint();
//        }
//    }
    
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
    
    public void compareCoords( int x1, int x2, int y1, int y2){
        
        if(x2 < x1){
            int temp = x2;
            x2 = x1;
            x1 = temp;
            temp = y2;
            y2 = y1;
            y1 = temp;
        }
    } 
    
    public void delete()
    {
        if ( shapeList.isEmpty())
            return;
        shapeList.remove(shapeList.size() - 1);
        repaint();
    }
    
    public void clear()
    {
        if ( shapeList.isEmpty())
            return;
        shapeList.clear();
        repaint();
    }
}
