/*
 * TCSS 305 - Autumn 2016
 * Assignment 5 - Power Paint
 */

package actions;

import gui.DrawingPanel;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import tools.RectangleTool;

/**
 * 
 * A rectangle action for the rectangle tool.
 * 
 * @author Yaro Salo
 * @version November 16, 2016
 * 
 */
public class RectangleAction extends AbstractAction {

    /** Generated serialization ID for this class. */
    private static final long serialVersionUID = -5301520193710308152L;


    /** The JPanel to associate with this Action. */
    private final DrawingPanel myPanel;

    /** The line tool for this line. */
    private final RectangleTool myRectangle;
    
    /**
     * 
     * Construct the rectangle action.
     * 
     * @param thePanel to associate this action with.
     */
    public RectangleAction(final DrawingPanel thePanel) {
        
        super("Rectangle", new ImageIcon("images/rectangle_bw.gif")); // set rectangle icon.

        myPanel = thePanel;
        myRectangle = new RectangleTool();   
        
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
        putValue(Action.SELECTED_KEY, true);
    }


    
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        
        myPanel.setTool(myRectangle); // set the panels tool to rectangle.
    } 
}