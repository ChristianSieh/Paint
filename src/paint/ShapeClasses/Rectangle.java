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
public class Rectangle extends Shape
{

    /**
     *
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     */
    public Rectangle(int x1, int x2, int y1, int y2){
        super(x1, x2, y1, y2);   
    }
    
    /**
     *
     * @param g
     */
    public void draw(Graphics g){
        g.setColor(shapeColor);
        g.drawRect(upperLeftX, upperLeftY, lowerRightX - upperLeftX, lowerRightY - upperLeftY);
    }
    
    /**
     *
     */
    public void move(){
        //stuff
    }
    public String toString(){
        return "";
    }
}
