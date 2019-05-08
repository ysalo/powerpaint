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

import tools.EllipseTool;

/**
 * 
 * An ellipse action for the ellipse tool.
 * 
 * @author Yaro Salo
 * @version November 16, 2016
 * 
 */
public class EllipseAction extends AbstractAction {

    /** Generated serialization ID for this class. */
    private static final long serialVersionUID = -6974134312436344285L;
    
    /** The JPanel to associate with this Action. */
    private final DrawingPanel myPanel;
   
    /** The ellipse tool.*/ 
    private final EllipseTool myEllipse;
    
    /**
     * 
     * Construct the ellipse action.
     * 
     * @param thePanel a panel to associate this action with.
     */
    public EllipseAction(final DrawingPanel thePanel) {
        
        super("Ellipse", new ImageIcon("images/ellipse_bw.gif")); // set the ellipse icon
        
        myPanel = thePanel;
        myEllipse = new EllipseTool();
        
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_E);
        putValue(Action.SELECTED_KEY, true);
    }


    
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myPanel.setTool(myEllipse); // set the panels tool to the ellipse tool.
    }
}
