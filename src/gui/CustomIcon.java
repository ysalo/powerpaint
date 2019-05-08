/*
 * TCSS 305 - Autumn 2016
 * Assignment 3 - Easy Street
 */

package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;


/**
 * 
 * Create a custom icon to be used by the menu bar options menu.
 * 
 * @author Yaro Salo
 * @version November 17, 2016
 */
public class CustomIcon implements Icon {

    /** The width and height of the square icon. */
    private static final int WIDTH_HEIGHT = 15;
   
    /** The color of the icon. */
    private Color myColor;
    
    /**
     * 
     * Construct the icon.
     * 
     * @param theColor is the color of the icon.
     */
    public CustomIcon(final Color theColor) {
       
        myColor = theColor;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int getIconWidth() {
       
        return WIDTH_HEIGHT;
    }
    
     /**
     * {@inheritDoc}
     */
    @Override
    public int getIconHeight() {
        
        return WIDTH_HEIGHT;
    }

    
    /**
     * 
     * Sets the color of the icon.
     * 
     * @param theColor the color to set to.
     */
    public void setColor(final Color theColor) {
        
        myColor = theColor;
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void paintIcon(final Component theComponent, final Graphics theGraphics, 
                          final int theX, final int theY) {
        
        final Graphics2D g2d = (Graphics2D) theGraphics.create();
        

        //Fill the icon with the selected fill color.
        g2d.setColor(myColor);
        g2d.fillRect(theX, theY, WIDTH_HEIGHT, WIDTH_HEIGHT);
 
        //Draw the border of the icon with black
        g2d.setColor(Color.BLACK);
        g2d.drawRect(theX, theY, WIDTH_HEIGHT, WIDTH_HEIGHT);
        
        g2d.dispose();
    }

    
    
}
