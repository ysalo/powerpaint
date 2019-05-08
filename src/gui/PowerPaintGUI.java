/*
 * TCSS 305 - Autumn 2016
 * Assignment 5 - Power Paint
 */

package gui;

import actions.EllipseAction;
import actions.LineAction;
import actions.PencilAction;
import actions.RectangleAction;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * 
 * Create the GUI for the power paint application.
 * 
 * @author Yaro Salo    
 * @version November 16, 2016
 */
public class PowerPaintGUI {

    /**
     * GUI constructor that passes the responsibility to the start() method
     * to set up the GUI components.
     */
    public PowerPaintGUI() {
        
        start();
    }
    
    /** 
     * Sets up the components for this GUI application.
     */
    private void start() {
        
        final JFrame paintFrame = new JFrame("TCSS 305 - PowerPaint");
        
        // Change the default icon to a custom icon.
        paintFrame.setIconImage(new ImageIcon("images/paint.png").getImage());
        
        // Create the components of the program.
        final DrawingPanel drawingPanel = new DrawingPanel();
        
        // The bar menu need the frame because it closes it
        // and it need the panel because it makes changes to it.
        final PaintMenuBar paintMenuBar = new PaintMenuBar(paintFrame, drawingPanel);
        final PaintToolBar paintToolBar = new PaintToolBar(); 
      
        // Register the paintMenu as listener for the changes from the 
        // the drawingPanel.
        drawingPanel.addPropertyChangeListener(paintMenuBar);
      
        // All the drawing actions of this program.
        final Action[] actions = {new LineAction(drawingPanel), 
                                  new PencilAction(drawingPanel),
                                  new RectangleAction(drawingPanel),
                                  new EllipseAction(drawingPanel)};

        // Add the actions to the menu bar and the tool bar.
        for (final Action action : actions) {
            paintMenuBar.createMenuButton(action);
            paintToolBar.createToolBarButton(action);     
        }
        
        
        
        // Add the components to the needed positions on the frame.
        paintFrame.setJMenuBar(paintMenuBar);

        paintFrame.add(paintToolBar, BorderLayout.SOUTH);
        paintFrame.add(drawingPanel, BorderLayout.CENTER);
        paintFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       
        // Pack the frame around the panel.
        paintFrame.pack();
        paintFrame.setMinimumSize(paintFrame.getSize()); // set the minimum size so the 
                                                        // program can't be made to small.
        
        final Toolkit kit = Toolkit.getDefaultToolkit();
        
        // Center the frame on the screen.
        paintFrame.setLocation(
            (int) (kit.getScreenSize().getWidth() / 2 - paintFrame.getWidth() / 2),
            (int) (kit.getScreenSize().getHeight() / 2 - paintFrame.getHeight() / 2));
        
        paintFrame.setVisible(true);
    }
}
