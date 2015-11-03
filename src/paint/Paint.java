package paint;

import javax.swing.JFrame;

/**
 * @author Dr. John Weiss (wrote the orignal program that this code is 
 * modified from)
 * @author Christian Sieh
 * @author Joe Mowry
 * 
 * This program will create a GUI with a draw panel and various menu options.
 * A user can different shapes inside the draw panel by using the shapes
 * drop down menu. This program also allows a user to select colors for a shape
 * by using two buttons, one for outline color and one for fill color. These
 * buttons implement a JColorChooser so as to allow the user to pick any color
 * they want. The default shape is a line and the default colors are black.
 * Also included are menu options under "File" for deleting a shape, undoing 
 * the deletion of a shape, clearing the screen of all shapes, and exiting the
 * program. Finally there is a help and about section under the "Help" menu.
 */
public class Paint {

    /**
     * @author Dr. John Weiss
     * @param args the command line arguments
     * 
     * Main creates a new JFrame, makes sure the program closes on exit,
     * and sets the JFrame visible.
     */
    public static void main(String[] args) {
        JFrame app = new MenuFrame();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }  
}

