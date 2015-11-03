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
 * This class does most of the heavy lifting: sets up window with dropdown menus and drawing area.
 */
public class MenuFrame extends JFrame implements KeyListener
{
    // private data //
    private final String [] shapeNames = { "Line", "Rectangle", "Filled Rectangle", "Ellipse", "Filled Ellipse" };
    private JRadioButtonMenuItem [] shapeItems;
    public static String selectedShape = "Line";
    public static Color outlineColor;
    public static Color fillColor;
    DrawPanel myPanel = new DrawPanel();

    // MenuDemo class methods //
    /** MenuFrame constructor
     *  This constructor builds the GUI.
     */
    public MenuFrame()
    {
        // call superclass constructor with window title
        super( "Paint" );
        addKeyListener( this );
        setSize( 640, 480 );

        myPanel.setBackground(Color.WHITE);
        
        // add drawing panel to content pane
        Container container = getContentPane();
        container.add( myPanel );

        // menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar( menuBar );
        
        // File menu
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
        
        mItem = new JMenuItem( "Exit" );
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
                    shapeSelection( ae.getActionCommand() );
                }
            } );
            menu.add( shapeItems[i] );
        }
        // default radio button selection
        shapeItems[0].setSelected( true );

        //Outline Color
        JButton btn = new JButton();
        btn.setBackground( Color.BLACK);
        btn.setToolTipText("Outline Color");
        btn.setPreferredSize(new Dimension(20,20));
        menuBar.add(btn);
        btn.addActionListener(new ActionListener() 
        {
            JColorChooser outlineChooser = new JColorChooser();
            public void actionPerformed(ActionEvent e){
                outlineColor = JColorChooser.showDialog(btn, "Outline Color Chooser", outlineColor);
                btn.setBackground(outlineColor);
            }
        });
       
        
        JButton btn2 = new JButton();
        btn2.setBackground( Color.BLACK);
        btn2.setToolTipText("Fill Color");
        menuBar.add(btn2);
        btn2.addActionListener(new ActionListener() 
        {
            JColorChooser fillChooser = new JColorChooser();
            public void actionPerformed(ActionEvent e){
                fillColor = JColorChooser.showDialog(btn2, "Color Chooser", fillColor);
                btn2.setBackground(fillColor);
            }
        });
        
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
