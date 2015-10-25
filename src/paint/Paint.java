/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint;

/**
 *
 * @author 1960681
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;

public class Paint {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame app = new MenuFrame();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }  
}

/** MenuFrame class
 * This class does most of the heavy lifting: sets up window with dropdown menus and drawing area.
 */
class MenuFrame extends JFrame implements KeyListener
{
    // private data //
    private final String [] shapeNames = { "Line", "Rectangle", "Filled Rectangle", "Ellipse", "Filled Ellipse" };
    private final String [] colorNames = { "Red", "Green", "Blue", "Yellow", "Purple", "Orange", "Black", "White" };
    private JRadioButtonMenuItem [] shapeItems;
    private JRadioButtonMenuItem [] colorItems;

    // MenuDemo class methods //

    /** MenuFrame constructor
     *  This constructor builds the GUI.
     */
    public MenuFrame()
    {
        // call superclass constructor with window title
        super( "MenuDemo - click and drag to draw a line" );
        addKeyListener( this );
        setSize( 640, 480 );

        // add drawing panel to content pane
        Container container = getContentPane();
        container.add( new DrawPanel() );

        // menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar( menuBar );

        // File menu
        JMenu menu = new JMenu( "File" );
        menuBar.add( menu );
        
        JMenuItem mItem = new JMenuItem( "Open" );
        mItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                OpenFile();
            }
        } );
        menu.add( mItem );
        
        mItem = new JMenuItem( "Save" );
        mItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                System.out.println( "File|Save selected." );
            }
        } );
        menu.add( mItem );

        mItem = new JMenuItem( "Quit" );
        mItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                System.exit( 0 );
            }
        } );
        menu.add( mItem );

        // shape button menu example
        menu = new JMenu( "Shapes" );
        menuBar.add( menu );
        ButtonGroup shapeGroup = new ButtonGroup();
        shapeItems = new JRadioButtonMenuItem [ shapeNames.length ];
        for ( int i = 0; i < shapeNames.length; i++ )
        {
            shapeItems[i] = new JRadioButtonMenuItem( shapeNames[i] );
            shapeGroup.add( shapeItems[i] );
            shapeItems[i].addActionListener( new ActionListener()
            {
                public void actionPerformed( ActionEvent ae )
                {
                    menuSelection( ae.getActionCommand() );
                }
            } );
            menu.add( shapeItems[i] );
        }
        // default radio button selection
        shapeItems[0].setSelected( true );
        
        // color button menu example
        menu = new JMenu( "Colors" );
        menuBar.add( menu );
        ButtonGroup colorGroup = new ButtonGroup();
        colorItems = new JRadioButtonMenuItem [ colorNames.length ];
        for ( int i = 0; i < colorNames.length; i++ )
        {
            colorItems[i] = new JRadioButtonMenuItem( colorNames[i] );
            colorGroup.add( colorItems[i] );
            colorItems[i].addActionListener( new ActionListener()
            {
                public void actionPerformed( ActionEvent ae )
                {
                    menuSelection( ae.getActionCommand() );
                }
            } );
            menu.add( colorItems[i] );
        }
        // default radio button selection
        shapeItems[0].setSelected( true );

        // Help menu
        menu = new JMenu( "Help" );
        menuBar.add( menu );
        mItem = new JMenuItem( "Help" );
        mItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                System.out.println( "Abandon all help, ye who enter here." );
            }
        } );
        menu.add( mItem );
        mItem = new JMenuItem( "About" );
        mItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                JOptionPane.showMessageDialog( null, "MenuDemo, v.0.0.0", "About MenuDemo", JOptionPane.INFORMATION_MESSAGE );
            }
        } );
        menu.add( mItem );

        // set initial window size
        setSize( 640, 480 );

        // display window
        setVisible( true );
    }

    // event handler for radio buttons
    public boolean menuSelection( String s )
    {
        for ( int i = 0; i < shapeNames.length; i++ )
        {
            if ( s == shapeNames[i] )
            {
                System.out.println("you selected item " + shapeNames[i] );
                for ( int j = 0; j < shapeNames.length; j++ )
                    shapeNames[j].setSelected( j == i );
                return true;
            }
        }
        return false;
    }

    // bring up a file chooser dialog
    private void OpenFile()
    {
        // create a file chooser
        JFileChooser fc = new JFileChooser();

        // respond to menu selection
        int returnVal = fc.showOpenDialog( this );

        if ( returnVal == JFileChooser.APPROVE_OPTION )
        {
            File file = fc.getSelectedFile();
            // this is where a real application would open the file.
            System.out.println( "Opening: " + file.getName() );
        }
        else
        {
            System.out.println( "Open command cancelled by user." );
        }
    }

    // KeyListener methods
    public void keyReleased( KeyEvent event ) { }
    public void keyTyped( KeyEvent event ) { }

    // exit when Escape key is pressed
    public void keyPressed( KeyEvent event )
    {
        if ( event.getKeyCode() == 27 )
            System.exit( 0 );
    }
}

/** DrawPanel class
 *  This class configures the drawing panel to support mouse events.
 */
class DrawPanel extends JPanel implements MouseListener
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