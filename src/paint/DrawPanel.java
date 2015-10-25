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
            repaint();
        }
    }

    // paintComponent() is the display callback function
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );			// call superclass's paint method
        g.setColor( Color.BLUE );
        g.drawLine( x1, y1, x2, y2 );
    }
}
