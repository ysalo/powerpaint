/*
 * TCSS 305 - Autumn 2016
 * Assignment 3 - Easy Street
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 * 
 * Creates a line based on a starting and ending points.
 * 
 * @author Yaro Salo
 * @version November 22 2016
 */
public class LineTool extends AbstractDrawingTool {
    

    /**
     * {@inheritDoc} 
     */
    @Override
    public Shape getShape() {
        
        // return a new line based on the staring and ending points.
        return new Line2D.Double(super.getStartPoint(), super.getEndPoint());
    }

}
