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
public abstract class Shape {
    
    public int upperLeftX;
    public int upperLeftY;
    public int lowerRightX;
    public int lowerRightY;
    public int leftX;
    public int rightX;
    public int leftY;
    public int rightY;
    public Color shapeColor;
    public float centerX;
    public float centerY;

    public Shape(){
        upperLeftX = 0;
        upperLeftY = 0;
        lowerRightX = 0;
        lowerRightY = 0;
        leftX = 0;
        rightX = 0;
        leftY = 0;
        rightY = 0;
        shapeColor = Color.BLACK;
        centerX = 0;
        centerY = 0;
    }
    
    public Shape(int x1, int x2, int y1, int y2){
        upperLeftX = min(x1, x2);
        upperLeftY = min(y1, y2);
        lowerRightX = max(x1, x2);
        lowerRightY = max(y1, y2);
        leftX = x1;
        rightX = x2;
        leftY = y1;
        rightY = y2;
        shapeColor = MenuFrame.selectedColor;
        centerX = (x1 + x2) / 2;
        centerY = (y1 + y2) / 2;
    }
    
    public abstract void draw(Graphics g);
    public abstract void move();
    public abstract String toString();
    
    public int min (int x1, int x2)
    {
        if ( x1 < x2 )
            return x1;
        return x2;
    }
    
    public int max (int x1, int x2)
    {
        if ( x1 > x2 )
            return x1;
        return x2;
    }
}