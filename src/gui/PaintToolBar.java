/*
 * TCSS 305 - Autumn 2016
 * Assignment 3 - Easy Street
 */

package gui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

/**
 * 
 * A tool bar from which the user could choose a tool with which to draw.
 * 
 * @author Yaro Salo
 * @version November 16, 2016
 *
 */
public class PaintToolBar extends JToolBar {


    /** Auto generated serialization ID. */
    private static final long serialVersionUID = -132991977444959623L;
    
    /** A button group for the mutually exclusive tool bar buttons. */ 
    private final ButtonGroup myGroup;
    
    /** Indicates whether the button should be selected. */
    private boolean mySelectFlag;
    
    /**
     * 
     * Initialize the instance field.
     */
    public PaintToolBar() {
    
        super();
        myGroup = new ButtonGroup();
        mySelectFlag = true;
       
    }
    
    /**
     * 
     * Create the JToggleButton for the ToolBar.
     * 
     * @param theAction to associate with the created JToggleButton
     */
    public void createToolBarButton(final Action theAction) {
        String actionClassName = theAction.getClass().getName();
        String actionName = actionClassName.substring(actionClassName.lastIndexOf(".") + 1);
        actionName = actionName.replaceAll("Action", "");
        actionName = actionName.toLowerCase();
        String colorPath = "images/" + actionName + ".gif";
        String noColorPath = "images/" + actionName + "_bw.gif";
        
        final JToggleButton toggleButton = new JToggleButton(theAction);
        //toggleButton.setIcon(new ImageIcon(noColorPath));
        toggleButton.setDisabledIcon(new ImageIcon(noColorPath));
        toggleButton.setPressedIcon(new ImageIcon(colorPath));
        toggleButton.setSelectedIcon(new ImageIcon(colorPath));
        toggleButton.setDisabledSelectedIcon(new ImageIcon(noColorPath));
        
        myGroup.add(toggleButton);
        add(toggleButton);     
        if (mySelectFlag) {
            mySelectFlag = false;
            toggleButton.doClick();
        }
    }
}
