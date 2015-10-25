/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint;

import javax.swing.JFrame;

/**
 *
 * @author 1960681
 */
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

