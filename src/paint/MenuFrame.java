/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JColorChooser;

/** MenuFrame class
 * @author Dr. John Weiss(Original author for this class. Later mentioned as JW)
 * @author Christian Sieh
 * @author Joe Mowry
 * This class extends JFrame and implements the menus and buttons for the
 * paint program. Also, handles key events.
 */
public class MenuFrame extends JFrame implements KeyListener
{
    // private data //
    private final String [] shapeNames = { "Line", 
        "Rectangle", "Filled Rectangle", "Ellipse", "Filled Ellipse" };
    private JRadioButtonMenuItem [] shapeItems;
    public static String selectedShape = "Line";
    public static Color outlineColor = Color.BLACK;
    public static Color fillColor = Color.BLACK;
    DrawPanel myPanel = new DrawPanel();

    // Paint class methods //
    /** MenuFrame constructor
     * @author Dr. John Weiss (original author for this method)
     * @author Christian Sieh
     * @author Joe Mowry
     *  This constructor builds the GUI.
     */
    public MenuFrame()
    {
        // call superclass constructor with window title - JW
        super( "Paint" );
        addKeyListener( this );
        setSize( 640, 480 );

        myPanel.setBackground(Color.WHITE);
        
        // add drawing panel to content pane - JW
        Container container = getContentPane();
        container.add( myPanel );

        // menu bar - JW
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar( menuBar );
        
        // File menu - JW
        JMenu menu = new JMenu( "File" );
        menuBar.add( menu );
        
        JMenuItem mItem = new JMenuItem( "Delete Shape" );
        mItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                myPanel.delete();
            }
        } );
        menu.add( mItem );
        
        //Option to undo the last delete
        mItem = new JMenuItem( "Undo Delete" );
        mItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                myPanel.undo();
            }
        } );
        menu.add( mItem );

        //Option to clear all shapes
        mItem = new JMenuItem( "Clear Shapes" );
        mItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                myPanel.clear();
            }
        } );
        menu.add( mItem );
        
        //Option to exit the program
        mItem = new JMenuItem( "Exit" );
        mItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                System.exit( 0 );
            }
        } );
        menu.add( mItem );
        
        // shape button menu example - JW
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
                    shapeSelection( ae.getActionCommand() );
                }
            } );
            menu.add( shapeItems[i] );
        }
        // default radio button selection
        shapeItems[0].setSelected( true );

        //Outline Color Button
        JButton outlineBtn = new JButton();
        //Default color
        outlineBtn.setBackground( Color.BLACK);
        outlineBtn.setToolTipText("Outline Color");
        
        //Disables focusing on the button so the program will stay focused
        //on the MenuFrame. Needed so key events got to the MenuFrame instead
        //of to the buttons
        outlineBtn.setFocusable(false);
        menuBar.add(outlineBtn);
        //The following code adds a JColorChooser component when the outlineBtn
        //is clicked so the user can selected a new color for the outline.
        //It handles this by using an actionListener
        JColorChooser outlineChooser = new JColorChooser();
        outlineBtn.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e){
                outlineColor = outlineChooser.showDialog(outlineBtn, 
                        "Outline Color Chooser", outlineColor);
                outlineBtn.setBackground(outlineColor);
            }
        });
       
        
        //Fill Color
        JButton fillBtn = new JButton();
        fillBtn.setBackground( Color.BLACK);
        fillBtn.setToolTipText("Fill Color");
        fillBtn.setFocusable(false);
        menuBar.add(fillBtn);
        JColorChooser fillChooser = new JColorChooser();
        fillBtn.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e){
                fillColor = fillChooser.showDialog(fillBtn, "Fill Color Chooser", fillColor);
                fillBtn.setBackground(fillColor);
            }
        });
        
        // Help menu - JW
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
        
        //About Menu
        menu.add( mItem );
        mItem = new JMenuItem( "About" );
        mItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                JOptionPane.showMessageDialog( null, "Authors: Christian Sieh\n"
                        + "Joe Mowry", "About Paint", JOptionPane.INFORMATION_MESSAGE );
            }
        } );
        menu.add( mItem );

        // set initial window size
        setSize( 640, 480 );

        // display window
        setVisible( true );
    }

    // event handler for radio buttons

    /**
     *
     * @param s
     * @return
     */
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

    // KeyListener methods
    public void keyReleased( KeyEvent event ) 
    { 
        int test = 0;
    }
    public void keyTyped( KeyEvent event ) 
    {
        int test = 0;
    }

    // exit when Escape key is pressed
    public void keyPressed( KeyEvent event )
    {
        if ( event.getKeyChar() == 'q')
            System.exit( 0 );
        if ( event.getKeyChar() == 'd')
            myPanel.delete();
        
        if ( event.getKeyChar() == 'c')
            myPanel.clear();
        
        if ( event.isControlDown() && event.getKeyChar() != 'z' && event.getKeyCode() == 90)
        {
            myPanel.undo();
        }
    }
}
