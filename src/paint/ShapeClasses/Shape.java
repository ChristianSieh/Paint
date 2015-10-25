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
public abstract class Shape {
    
    public int leftX;
    public int rightX;
    public int leftY;
    public int rightY;
    public Color shapeColor;

    public Shape(){
        leftX = 0;
        rightX = 0;
        leftY = 0;
        rightY = 0;
        shapeColor = Color.BLACK;
    }
    
    public abstract void draw(Graphics g);
    public abstract void move();
    public abstract String toString();
}