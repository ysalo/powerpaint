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

import tools.LineTool;

/**
 * 
 * A line action for the line tool.
 * 
 * @author Yaro Salo
 * @version November 16, 2016
 * 
 */
public class LineAction extends AbstractAction {

    /** Generated serialization ID.*/
    private static final long serialVersionUID = 4593426681746288034L;

    /** The JPanel to associate with this Action. */
    private final DrawingPanel myPanel;

    /** The line tool for this line. */
    private final LineTool myLine;
   

   
    /**
     * 
     * Construct the line action.
     * 
     * @param thePanel to associate this action with.
     */
    public LineAction(final DrawingPanel thePanel) {
        
        super("Line", new ImageIcon("images/line_bw.gif"));
        
        myLine = new LineTool();
        myPanel = thePanel;
        
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_L);
        putValue(Action.SELECTED_KEY, true);
    }


    
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        
        myPanel.setTool(myLine); // set the panel tool to line tool.
    }

}
