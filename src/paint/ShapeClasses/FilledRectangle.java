/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.ShapeClasses;

import java.awt.Color;
import java.awt.Graphics;
import paint.MenuFrame;

/**
 *
 * @author 1960681
 */
public class FilledRectangle extends Rectangle
{
    public FilledRectangle(int x1, int x2, int y1, int y2){
        super(x1, x2, y1, y2); 
    }
    
    public void draw(Graphics g){
        g.setColor( Color.BLUE );
        g.fillRect(upperLeftX, upperLeftY, lowerRightX - upperLeftX, lowerRightY - upperLeftY);
        g.setColor( Color.RED );
        g.drawRect(upperLeftX, upperLeftY, lowerRightX - upperLeftX, lowerRightY - upperLeftY);
    }
    
    public void move(){
        //stuff
    }
    public String toString(){
        return "";
    }
}
