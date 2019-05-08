/*
 * TCSS 305 - Autumn 2016
 * Assignment 3 - Easy Street
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;

/**
 * 
 * Creates and returns a elliptical shape.
 * 
 * @author Yaro Salo
 * @version November 22 2016
 */
public class EllipseTool extends AbstractDrawingTool {

    /**
     * {@inheritDoc}
     */
    @Override
    public Shape getShape() {
        
        // create my ellipse as a rectangular shape because 
        // them I have access to the setFrameFromDiagonal() method.
        final RectangularShape ellipse = new Ellipse2D.Double();
        ellipse.setFrameFromDiagonal(super.getStartPoint(), super.getEndPoint());
        return ellipse;
    }
    
}
