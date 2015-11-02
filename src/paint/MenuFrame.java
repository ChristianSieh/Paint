/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

/** MenuFrame class
 * This class does most of the heavy lifting: sets up window with dropdown menus and drawing area.
 */
public class MenuFrame extends JFrame implements KeyListener
{
    // private data //
    private final String [] shapeNames = { "Line", "Rectangle", "Filled Rectangle", "Ellipse", "Filled Ellipse" };
    private final String [] colorNames = { "Red", "Green", "Blue", "Yellow", "Magenta", "Orange", "Black", "White" };
    private JRadioButtonMenuItem [] shapeItems;
    private JRadioButtonMenuItem [] colorItems;
    public static String selectedShape = "Line";
    public static String selectedColor = "Black";
    DrawPanel myPanel = new DrawPanel();

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
        container.add( myPanel );

        // menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar( menuBar );

        // shape button menu example
        JMenu menu = new JMenu( "Shapes" );
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
                    shapeSelection( ae.getActionCommand() );
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
                    colorSelection( ae.getActionCommand() );
                }
            } );
            menu.add( colorItems[i] );
        }
        // default radio button selection
        colorItems[0].setSelected( true );

        // Help menu
        menu = new JMenu( "Help" );
        menuBar.add( menu );
        JMenuItem mItem = new JMenuItem( "Help" );
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
    public boolean shapeSelection( String s )
    {
        selectedShape = s;
        for ( int i = 0; i < shapeNames.length; i++ )
        {
            if ( s == shapeNames[i] )
            {
                System.out.println("you selected item " + shapeNames[i] );
                for ( int j = 0; j < shapeNames.length; j++ )
                    shapeItems[j].setSelected( j == i );
                return true;
            }
        }
        return false;
    }
    
        // event handler for radio buttons
    public boolean colorSelection( String s )
    {
        selectedColor = s;
        for ( int i = 0; i < colorNames.length; i++ )
        {
            if ( s == colorNames[i] )
            {
                System.out.println("you selected item " + colorNames[i] );
                for ( int j = 0; j < colorNames.length; j++ )
                    colorItems[j].setSelected( j == i );
                return true;
            }
        }
        return false;
    }

    // KeyListener methods
    public void keyReleased( KeyEvent event ) { }
    public void keyTyped( KeyEvent event ) { }

    // exit when Escape key is pressed
    public void keyPressed( KeyEvent event )
    {
        if ( event.getKeyChar() == 'q')
            System.exit( 0 );
        if ( event.getKeyChar() == 'd')
        {
            myPanel.delete();
        }
        if ( event.getKeyChar() == 'c')
        {
            myPanel.clear();
        }
    }
}
