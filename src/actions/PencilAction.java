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

import tools.PencilTool;

/**
 * 
 * A pencil action for the pencil tool.
 * 
 * @author Yaro Salo
 * @version November 16, 2016
 * 
 */
public class PencilAction extends AbstractAction {


    /** Generated serialization ID for this class. */
    private static final long serialVersionUID = 6769325560128336404L;
    
    
    /** The JPanel to associate with this Action. */
    private final DrawingPanel myPanel;

    /** The line tool for this line. */
    private final PencilTool myPencil;
    
    /**
     * 
     * Construct the pencil action.
     * 
     * @param thePanel to associate this action with.
     */
    public PencilAction(final DrawingPanel thePanel) {
        
        super("Pencil", new ImageIcon("images/pencil_bw.gif")); // set the pencil icon.

        myPanel = thePanel;
        myPencil = new PencilTool();
        
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_P);
        putValue(Action.SELECTED_KEY, true);

    }


    
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        
        myPanel.setTool(myPencil); // set the panels tool to the pencil tool.
    }
}