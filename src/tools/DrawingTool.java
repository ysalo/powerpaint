/*
 * TCSS 305 - Autumn 2016
 * Assignment 3 - Easy Street
 */

package tools;

import java.awt.Point;
import java.awt.Shape;

/**
 * 
 * An interface for the drawing tools. 
 * 
 * @author Yaro Salo
 * @version November 19, 2016
 */
public interface DrawingTool {



    /**
     * 
     * Return the current shape.
     * 
     * @return the shape.
     */
    Shape getShape();
   
    /**
     * 
     * Return the staring point of the shape.
     * 
     * @return starting point of the shape.
     */
    Point getStartPoint();
    
    /**
     * 
     * Return the end point of the shape.
     * 
     * @return the end point of the shape.
     */
    Point getEndPoint();
    
    /**
     * 
     * Return whether a shape can be filled.
     * All shapes except the ones drawn with the pencil can be filled.
     * 
     * @return a boolean indication if a shape can be filled.
     */
    boolean isFillable();
        
    /**
     * 
     * Set the starting point of the shape.
     * 
     * @param theStartPoint is the point 2D to set to.
     */
    void setStartPoint(final Point theStartPoint);
    
    /**
     * 
     * Set the ending point of a shape.
     *
     * @param theEndPoint is the point 2D to set to.
     */
    void setEndPoint(final Point theEndPoint);
    
}
