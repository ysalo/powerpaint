/*
 * TCSS 305 - Autumn 2016
 * Assignment 3 - Easy Street
 */

package tools;

import java.awt.Color;
import java.awt.Shape;


/**
 * 
 * A helper class to store the information about a drawn shape.
 * 
 * @author Yaro Salo
 * @version November 21 2016
 */
public class DrawnShape {

    /** The color with which the shape has been drawn. */
    private final Color myDrawColor;
    
    /** The color with which the shape has been filled. */
    private final Color myFillColor;
    
    /** The shape drawn. */
    private final Shape myShape;
    
    /** The thickness of the shape. */
    private final int myThickness;
    /** Indicates whether the shape has been filled. */
    private boolean myFill;
    
    /** 
     * 
     * Initialize the instance fields.
     * 
     * @param theDrawColor the draw color. 
     * @param theFillColor the fill color.
     * @param theFill if the shape is filled.
     * @param theThickness the thickness of the shape.
     * @param theShape the shape.
     */
    public DrawnShape(final Color theDrawColor, final Color theFillColor,
                      final boolean theFill, final int theThickness, 
                      final Shape theShape) {
       
        myShape = theShape;
        myDrawColor = theDrawColor;
        myFillColor = theFillColor;
        myFill = theFill;
        myThickness = theThickness;
        
        checkFillable(); // Check if the shape can be filled.
        
    }
    
    /**
     * 
     * Checks if the shape drawn is fillable. If it is not then it
     * sets its fillable flag to false.
     */
    private void checkFillable() {
       
        if (myShape.getClass().getSimpleName().equals("GeneralPath")) {
           
            myFill = false;
        }
        
        
    }
    
    /**
     * 
     * Returns the type of shape.
     * 
     * @return the type of shape.
     */
    public Shape getShape() {

        return myShape;
    }
    
    /**
     * 
     * Returns the draw color of the shape.
     * 
     * @return the draw color.
     */
    public Color getDrawColor() {
        
        return myDrawColor;
    }
    
    /**
     * 
     * Returns the fill color of the shape.
     *  
     * @return the fill color.
     */
    public Color getFillColor() {
        

        return myFillColor;
    }
    
    
    /**
     * 
     * Returns true if the shape was filled and false otherwise.
     * 
     * @return if the shape was filled.
     */
    public boolean isFill() {

        return myFill; 
    }
    
    /**
     * 
     * Returns the thickness of the shape.
     *  
     * @return the thickness.
     */
    public int getThickness() {
        
        return myThickness;
    }
}
