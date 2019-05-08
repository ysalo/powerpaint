package tools;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

/**
 * 
 * Create and return a rectangular shape.
 * 
 * @author Yaro Salo
 * @version November 23 2016
 */
public class RectangleTool extends AbstractDrawingTool {

    @Override
    public Shape getShape() {

        // create my rectangle as a rectangular shape because 
        // then I have access to the setFrameFromDiagonal() method.
        final RectangularShape rectangle = new Rectangle2D.Double();
        rectangle.setFrameFromDiagonal(super.getStartPoint(), super.getEndPoint());
        
        return rectangle;
    }
    
}
