/*
 * TCSS 305 - Autumn 2016
 * Assignment 3 - Easy Street
 */

package tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;

/**
 * 
 * Create a path to simulate drawing with a pencil.
 * 
 * @author Yaro Salo
 * @version November 23 2016
 */
public class PencilTool extends AbstractDrawingTool {
    
    /** The pencil path. */
    private Path2D myPencilPath;
    
    /**
     * 
     * Initialize the instance fields.
     */
    public PencilTool() {
        
        super();

        myPencilPath = new GeneralPath();
        
    }
    
    /**
     * {@inheritDoc} 
     */
    @Override
    public void setStartPoint(final Point theStartPoint) {
        
        final Point startPoint = theStartPoint; // set with a defensive copy.
        
        // The path needs to be new for every path the user draws so 
        // I need a new path to set set the pencil path with, every time the user 
        // draws a new path.
        final Path2D tempPath = new GeneralPath();
        
        myPencilPath = tempPath;
        
        // Move my path to the where the user clicked before creating the path.
        myPencilPath.moveTo(startPoint.getX(), startPoint.getY());
        
        // Start creating the path.
        myPencilPath.lineTo(startPoint.getX(), startPoint.getY());
    }

    
    /**
     * {@inheritDoc} 
     */
    @Override
    public void setEndPoint(final Point theEndPoint) {
        
        final Point endPoint = theEndPoint;
        
        // Append the end point to the end of the path.
        myPencilPath.lineTo(endPoint.getX(), endPoint.getY());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Shape getShape() {
       
        return myPencilPath; 
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFillable() {
        
        return false; 
    }


}