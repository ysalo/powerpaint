/*
 * TCSS 305 - Autumn 2016
 * Assignment 3 - Easy Street
 */

package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JPanel;

import tools.DrawingTool;
import tools.DrawnShape;

/**
 * 
 * A panel on which the user will draw their shapes.
 * 
 * @author Yaro Salo
 * @version November 16, 2016
 */
public class DrawingPanel extends JPanel {

    /** The UW Purple color. */
    public static final Color UW_PURPLE = new Color(51, 0, 111);
    
    /** The UW Gold color. */
    public static final Color UW_GOLD = new Color(232, 211, 162);
    
    /** Generated Serialization ID for this class. */
    private static final long serialVersionUID = -2463040025654760722L;

    /** The preferred width of the panel. */
    private static final int WIDTH = 600;
    
    /** The preferred height of the panel. */
    private static final int HEIGTH = 400;
    
    /** An object to represent that no point is assigned. */
    private static final Point NO_POINT = null;

    /** A collection of the shapes drawn on the panel. */
    private final List<DrawnShape> myDrawnShapes;
    
    /** The drawing color. */
    private Color myDrawColor;
    
    /** The filling color. */
    private Color myFillColor;
    
    /** Indicates if the user pressed the clear button. */ 
    private boolean myClearFlag;
    
    /** Indicates whether that shape should be filled.*/
    private boolean myFill;
    
    /** Indicates whether a shape is currently being drawn.*/
    private boolean myCurrentDraw;
    
    /** The start point for the Shape. */
    private Point myStartPoint;
    
    /** The end point for the Shape. */
    private Point myEndPoint;

    /** The tool used to draw on the panel. */
    private DrawingTool myTool;
    
    /** The thickness of the shape. */
    private int myThickness;
    
    /**
     * 
     * Construct the panel.
     * Initialize the instance fields.
     */
    public DrawingPanel() {
    
        super();
        
        myDrawColor = UW_PURPLE;
        myFillColor = UW_GOLD;

        myThickness = 1;
        
        //Filling and clearing is disabled.
        myFill = false;
        myClearFlag = false;
        myCurrentDraw = false; // No shape is being drawn initially.
        
        myDrawnShapes = new ArrayList<DrawnShape>();
        
        setup();  // pass on responsibility for the rest of the setup.
    }
    
    /**
     * 
     * Set up the components of the panel.
     */
    private void setup() {
        
        setPreferredSize(new Dimension(WIDTH, HEIGTH));
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

        setBackground(Color.WHITE);
        setOpaque(true);
        
        // Instantiate and add a mouse lister as a mouse listener and a mouse motion listener.
        final PaintMouseAdapter mouse = new PaintMouseAdapter();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }
    
    /**
     * 
     * Sets whether the current shape can be drawn.
     * 
     * @param theFlag is the value to set to.
     */
    public void setClearFlag(final boolean theFlag) {
        
        myClearFlag = theFlag;
    }
    
    /** 
     * 
     * Sets the current tool with the tool.
     * 
     * @param theTool the tool to set to.
     */
    public void setTool(final DrawingTool theTool) {
        
        myTool = theTool;
    }
    
    /**
     * 
     * Sets the drawing color of the tool.
     * 
     * @param theColor the color to set to.
     */
    public void setDrawColor(final Color theColor) {
        
        myDrawColor = theColor;
        
    }
    
    
    /**
     * 
     * Sets the filling color of the tool.
     * 
     * @param theColor the color to set to.
     */
    public void setFillColor(final Color theColor) {
        
        myFillColor = theColor;
    }
    
    /**
     * 
     * Sets if the shape needs to be filled.
     * 
     * @param theFlag indicates if the shape need to be filled. True: yes False: no.
     */
    public void setFill(final boolean theFlag) {
        
        myFill = theFlag;
        
    }
    
    /**
     * 
     * Sets the thickness of the shape.
     * 
     * @param theThickness the thickness to set to. 
     */
    public void setThickness(final int theThickness) {
        
        myThickness = theThickness;     
    }
    
    
    /**
     * 
     * Return the drawing color.
     * @return the current drawing color. 
     */
    public Color getDrawColor() {
        
        return myDrawColor;
    }
    
    /**
     * 
     * Return the filling color.
     * @return the current filling color.
     */
    public Color getFillingColor() {
        
        return myFillColor;
    }

    /**
     * 
     * Clear the drawing panel.
     */
    public void clearDrawn() {
        
        myDrawnShapes.clear(); // remove all drawn shapes from the collection.
        repaint();
    }
    
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Redraw all previously drawn shapes.
        for (final DrawnShape shape : myDrawnShapes) {
            // If the fill box is check get the fill color and fill the shape.
            if (shape.isFill()) {
                
                g2d.setColor(shape.getFillColor());
                g2d.fill(shape.getShape());
            }
            // Get the necessary information for redrawing a shape
            // Pass is 2 extra parameters to make the drawing look a little nicer.
            final  BasicStroke stroke = new BasicStroke(shape.getThickness(),
                                                 BasicStroke.CAP_ROUND, 
                                                 BasicStroke.JOIN_ROUND);

            g2d.setStroke(stroke);
            g2d.setColor(shape.getDrawColor());
            g2d.draw(shape.getShape());

        }

        // Get the current settings and draw the shape with them.
        final BasicStroke currentStroke = new BasicStroke(myThickness, 
                                                    BasicStroke.CAP_ROUND, 
                                                    BasicStroke.JOIN_ROUND);
  

        
        // If the user pressed the clear button prevent the graphics from 
        // redrawing the current shape.
        if (!Objects.equals(myEndPoint, NO_POINT) && !myClearFlag && myCurrentDraw) { 
            
            g2d.setStroke(currentStroke);
            g2d.setColor(myDrawColor);
            
            // If the box is checked and the shape is fillable, fill the shape.
            if (myFill && myTool.isFillable()) {
                
                // Set the fill color and fill the shape with it.
                g2d.setColor(myFillColor);
                g2d.fill(myTool.getShape());
            }
            
            g2d.setColor(myDrawColor); // set the drawing color back.
            g2d.draw(myTool.getShape());
        }
        g2d.dispose();
    }
    
    
    /**
     * 
     * Create a mouse listener for the paint program.
     * 
     * @author Yaro Salo
     * @version November 21 2016
     */
    public class PaintMouseAdapter extends MouseAdapter {
     
        @Override 
        public void mousePressed(final MouseEvent theEvent) {

            // Inform the clear button that it needs to enable.
            firePropertyChange("button", false, true); 
            myCurrentDraw = true;
            
            // Get the point where the mouse is clicked at the start.
            myStartPoint = theEvent.getPoint();
            myEndPoint = NO_POINT; // Don't have the end point yet.
            
            myTool.setStartPoint(myStartPoint); // set the staring point of the tool.
            repaint(); 
        }
        
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            
            //Get the end point so that we can draw as the user drags the mouse around.
            myEndPoint = theEvent.getPoint();
            
            myTool.setEndPoint(myEndPoint); // continually set the end point.
            repaint();
            
        }
        
        @Override 
        public void mouseReleased(final MouseEvent theEvent) {
           
            myCurrentDraw = false; // a shape is no longer being drawn.
            //Get the end point last time to complete the shape.
            myEndPoint = theEvent.getPoint();
            myTool.setEndPoint(myEndPoint);
            
       
            // The user finished drawing the shape so it can stored.
            // Pass in the tool drawing the current shape so it can be checked
            // if it is fillable.
            myDrawnShapes.add(new DrawnShape(myDrawColor, myFillColor, 
                                             myFill, myThickness, myTool.getShape()));   
        }
    }
}

