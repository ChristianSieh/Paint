package paint;

import java.awt.Color;
import java.awt.Container;
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
 * @author Joseph Mowry
 * 
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
     * @author Dr. John Weiss (original author for this constructor)
     * @author Christian Sieh
     * @author Joseph Mowry
     * 
     *  This constructor builds the GUI by adding a container that holds the
     * DrawPanel and a MenuBar with various menus and menu items. The menu
     * bar also has two buttons that handle outline and fill color as well as
     * having text tool tips.
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
        mItem.addActionListener((ActionEvent ae) -> {
            myPanel.delete();
        });
        menu.add( mItem );
        
        //Option to undo the last delete
        mItem = new JMenuItem( "Undo Delete" );
        mItem.addActionListener((ActionEvent ae) -> {
            myPanel.undo();
        });
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
        mItem.addActionListener((ActionEvent ae) -> {
            System.exit( 0 );
        });
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
            shapeItems[i].addActionListener((ActionEvent ae) -> {
                shapeSelection( ae.getActionCommand() );
            });
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
        outlineBtn.addActionListener((ActionEvent e) -> {
            outlineColor = JColorChooser.showDialog(outlineBtn,
                    "Outline Color Chooser", outlineColor);
            outlineBtn.setBackground(outlineColor);
        });
       
        
        //Fill Color
        JButton fillBtn = new JButton();
        fillBtn.setBackground( Color.BLACK);
        fillBtn.setToolTipText("Fill Color");
        fillBtn.setFocusable(false);
        menuBar.add(fillBtn);
        JColorChooser fillChooser = new JColorChooser();
        fillBtn.addActionListener((ActionEvent e) -> {
            fillColor = JColorChooser.showDialog(fillBtn,
                    "Fill Color Chooser", fillColor);
            fillBtn.setBackground(fillColor);
        });
        
        // Help menu - JW
        menu = new JMenu( "Help" );
        menuBar.add( menu );
        mItem = new JMenuItem( "Help" );
        mItem.addActionListener((ActionEvent ae) -> {
            JOptionPane.showMessageDialog( null, 
                    "\n\nLeft-click and drag to draw\n" +
                    "Right-click near the center of a shape and drag to move that shape\n\n" +
                    "Use the two color pickers to choose an outline (left) and a fill color (right)\n" +
                    "       NOTE: You can also hover over the pickers\n " +
                    "       if you ever forget which one is which!\n\n" +
                    "Keyboard Shortcuts: \n\n" +
                    "'d'                                -                   delete the topmost shape\n" +
                    "'c'                                -                   clear the screen of all shapes\n" +
                    "ESC/'q'                        -                   exit the program\n" +
                    "CTRL+'Z'                    -                   undo the last delete (does not revert a \"clear\" option \n\n" +
                    "Tips on moving shapes:\n" +
                    "*Try to click nearest to the center of the desired shape to move it\n" +
                    "*When releasing the right-mouse button after dragging, the upper left corner of the shape\n" +
                    "       will appear at the tip of the mouse, not the center of the shape.\n" +
                    "*To avoid undesired selections, in order to select a shape to move, the cursor must be withi\n" +
                    "       a certain distance from the center of that shape.\n" +
                    "*When a shape is moved, it is automatically brought to the front of the canvas.\n" 
                    , "Helpful Tips",
                    JOptionPane.INFORMATION_MESSAGE );
        });
        //About Item - JW
        menu.add( mItem );
        mItem = new JMenuItem( "About" );
        mItem.addActionListener((ActionEvent ae) -> {
            JOptionPane.showMessageDialog( null, "Authors: Christian Sieh &"
                    + " Joseph Mowry\n\n" + "Class: CSC 461\n" + "Program #2: Java"
                    + " Paint Program" + "\n\nDescription: \n\n" + "This program "
                    + "implements a basic paint program that allows you to draw various shapes"
                    + " in different\n colors, and allows you to move them by right"
                    + "-clicking toward the center of the shape and dragging!\n\n Enjoy!"
                    , "About Paint\n",
                    JOptionPane.INFORMATION_MESSAGE );
        });
        menu.add( mItem );

        // set initial window size - JW
        setSize( 640, 480 );

        // display window - JW
        setVisible( true );
    }

    /**
     * @author John Weiss
     * @param s
     * @return true or false
     * 
     * This method will set the shape selection based on the radio button
     * selected in the Shape menu.
     */
    public boolean shapeSelection( String s )
    {
        selectedShape = s;
        for ( int i = 0; i < shapeNames.length; i++ )
        {
            if ( s == null ? shapeNames[i] == null : s.equals(shapeNames[i]) )
            {
                System.out.println("you selected item " + shapeNames[i] );
                for ( int j = 0; j < shapeNames.length; j++ )
                    shapeItems[j].setSelected( j == i );
                return true;
            }
        }
        return false;
    }

    // KeyListener methods - JW
    @Override
    public void keyReleased( KeyEvent event ) { }
    @Override
    public void keyTyped( KeyEvent event ) { }

    /**
     * @author Christian Sieh
     * 
     * This method will handle when a key is pressed by the user.
     */
    @Override
    public void keyPressed( KeyEvent event )
    {
        //Exits the application
        if ( event.getKeyChar() == 'q' )
            System.exit( 0 );
        if ( event.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit( 0 );
        //Deletes the most recently drawn shape
        if ( event.getKeyChar() == 'd')
            myPanel.delete();
        //Clears all shapes on the screen
        if ( event.getKeyChar() == 'c')
            myPanel.clear();
        //When Ctrl + z is pressed it will undo the last delete
        if ( event.isControlDown() && event.getKeyChar() != 'z' 
                && event.getKeyCode() == 90)
        {
            myPanel.undo();
        }
    }
}
