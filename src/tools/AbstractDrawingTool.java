/*
 * TCSS 305 - Autumn 2016
 * Assignment 3 - Easy Street
 */

package tools;


import java.awt.Point;


/**
 * 
 * Represents the common behavior between all the tools.
 * 
 * @author Yaro Salo
 * @version November 19 2016
 */
public abstract class AbstractDrawingTool implements DrawingTool {
    
    /** A place holder point for a shape. */
    private static final Point STARTING_POINT = new Point();
    
    /** 
     * The starting point of the shape.
     */
    private Point myStartPoint;
    
    /**
     * The ending point of the shape.
     */
    private Point myEndPoint;
   

    /**
     * 
     * Initialize the staring and ending point with the placeholder point.
     */
    public AbstractDrawingTool() {
        
        myStartPoint = STARTING_POINT;
        myEndPoint = STARTING_POINT;

    }
   
    /**
     * {@inheritDoc} 
     */
    @Override
    public Point getStartPoint() {
        
        return (Point) myStartPoint.clone(); // Point is mutable, return a defensive copy.
    }
    
    /**
     * {@inheritDoc} 
     */
    @Override
    public Point getEndPoint() {
        
        return (Point) myEndPoint.clone();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public  boolean isFillable() {
        
        return true;
    }
    
    /**
     * {@inheritDoc} 
     */
    @Override
    public void setStartPoint(final Point theStartPoint) {
        
        myStartPoint = (Point) theStartPoint.clone();
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public void setEndPoint(final Point theEndPoint) {
        
        myEndPoint = (Point) theEndPoint.clone();
        
    }
    
    
}
