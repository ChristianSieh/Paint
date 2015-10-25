/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.ShapeClasses;

import java.awt.Color;

/**
 *
 * @author 1960681
 */
public class Shape {
    
    private int leftX;
    private int rightX;
    private int lowerY;
    private int upperY;
    private Color shapeColor;

    Shape(){
        leftX = 0;
        rightX = 0;
        lowerY = 0;
        upperY = 0;
        shapeColor = Color.BLACK;
    }
    
    public int getLeftX() {
        return leftX;
    }

    public void setLeftX(int leftX) {
        this.leftX = leftX;
    }

    public int getRightX() {
        return rightX;
    }

    public void setRightX(int rightX) {
        this.rightX = rightX;
    }

    public int getLowerY() {
        return lowerY;
    }

    public void setLowerY(int lowerY) {
        this.lowerY = lowerY;
    }

    public int getUpperY() {
        return upperY;
    }

    public void setUpperY(int upperY) {
        this.upperY = upperY;
    }

    public Color getShapeColor() {
        return shapeColor;
    }

    public void setShapeColor(Color shapeColor) {
        this.shapeColor = shapeColor;
    }
    
    public void draw()
    {
        
    }
}
