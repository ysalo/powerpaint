/*
 * TCSS 305 - Autumn 2016
 * Assignment 5 - Power Paint
 */

package gui;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * 
 * Runs the PowerPaint program.
 * 
 * @author Yaro Salo
 * @version November 11 2016
 */
public final class PowerPaintMain {
    
    /**
     * 
     * Private constructor to prevent instantiation.
     */
    private PowerPaintMain() {
    
        throw new AssertionError("Prevent default constructor instantiation.");
    }

    /**
     * 
     * Sets the look and feel to 'Metal'.
     */
    private static void setLookAndFeel() {
        
        try {
          
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            
        } catch (final UnsupportedLookAndFeelException e) {
            System.out.println("UnsupportedLookAndFeelException");
        } catch (final ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
        } catch (final InstantiationException e) {
            System.out.println("InstantiationException");
        } catch (final IllegalAccessException e) {
            System.out.println("IllegalAccessException");
        }
        
    }
    
    /**
     *
     * The starting point for this application.
     * 
     * @param theArgs Command line arguments ignored in this application.
     */
    public static void main(final String... theArgs) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                setLookAndFeel();
                new PowerPaintGUI();     
            }
        });
    }
}