/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.ShapeClasses;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author 1960681
 */
public class Rectangle extends Shape
{
    public void draw(Graphics g){
        g.setColor( Color.RED );
        g.drawRect(leftX, leftY, rightX - leftX, rightY - leftY);
    }
    
    public void move(){
        //stuff
    }
    public String toString(){
        return "";
    }
}
