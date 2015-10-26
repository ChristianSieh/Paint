/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import paint.ShapeClasses.*;

/** DrawPanel class
 *  This class configures the drawing panel to support mouse events.
 */
public class DrawPanel extends JPanel implements MouseListener
{
    private int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
    private boolean leftButtonPress = false;

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
            // TODO: determine leftX, leftY, rightX, rightY
            
            repaint();
        }
    }

    // paintComponent() is the display callback function
    public void paintComponent( Graphics g )
    {
        switch(MenuFrame.selectedShape)
        {
            case ("Line"):
                Line lineShape = new Line();
                lineShape.leftX = x1;
                lineShape.leftY = y1;
                lineShape.rightX = x2;
                lineShape.rightY = y2;
                super.paintComponent( g );
                lineShape.draw(g);
                break;
                
            case ("Rectangle"):
                Rectangle rectangleShape = new Rectangle();
                rectangleShape.leftX = x1;
                rectangleShape.leftY = y1;
                rectangleShape.rightX = x2;
                rectangleShape.rightY = y2;
                super.paintComponent(g);
                rectangleShape.draw(g);
                break;
                
            case ("Filled Rectangle"):
                FilledRectangle fRectangleShape = new FilledRectangle();
                fRectangleShape.leftX = x1;
                fRectangleShape.leftY = y1;
                fRectangleShape.rightX = x2;
                fRectangleShape.rightY = y2;
                super.paintComponent(g);
                fRectangleShape.draw(g);
                break;
                
                case ("Ellipse"):
                Ellipse ellipseShape = new Ellipse();
                ellipseShape.leftX = x1;
                ellipseShape.leftY = y1;
                ellipseShape.rightX = x2;
                ellipseShape.rightY = y2;
                super.paintComponent(g);
                ellipseShape.draw(g);
                break;
                
            case ("Filled Ellipse"):
                FilledEllipse fEllipseShape = new FilledEllipse();
                fEllipseShape.leftX = x1;
                fEllipseShape.leftY = y1;
                fEllipseShape.rightX = x2;
                fEllipseShape.rightY = y2;
                super.paintComponent(g);
                fEllipseShape.draw(g);
                break;    
                
            default:
                Line myShape = new Line();
                myShape.draw(g);
                break;
        }
        
//        g.setColor( Color.BLUE );
////        g.Draw()
//        g.drawLine( x1, y1, x2, y2 );
    }
    
    public void compareCoords( int x1, int x2, int y1, int y2){
        
        if(x1 < x2){
            
        }
    }
}
