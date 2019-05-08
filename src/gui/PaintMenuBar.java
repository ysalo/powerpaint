/*
 * TCSS 305 - Autumn 2016
 * Assignment 3 - Easy Street
 */

package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ButtonGroup;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * A menu bar from which a user can select different options.   
 * 
 * @author Yaro Salo
 * @version November 16, 2016
 */
public class PaintMenuBar extends JMenuBar implements PropertyChangeListener {

    /** Generated serialization ID for this class. */
    private static final long serialVersionUID = 8021086345663182613L;

    /** Draw Color string. */
    private static final String COLOR_STRING = "Draw Color...";
    
    /** The max thickness of the slider in the options menu. */
    private static final int MAX_THICKNESS = 20;
    
    /** The initial thickness of the slider in the options menu. */
    private static final int INITIAL_THICKNESS = 1;
    
    /** The spacing of the slide in the options menu. */
    private static final int SLIDER_SPACING = 5;
    
    /** A JMenu that contains drawing tools. */
    private final JMenu myToolsMenu;
    
    /** A button to exit the program. */
    private final JMenuItem myQuitButton;
    
    /** The button to clear the panel. */
    private  final JMenuItem myClearButton;
    
    /** A button group for radio buttons. */
    private final ButtonGroup myGroup;

    /** The icon to display the current draw color. */
    private final CustomIcon myDrawIcon;
    
    /** The icon to display the current fill color. */
    private final CustomIcon myFillIcon;

    /** The panel. */
    private final DrawingPanel myPanel;

    
    /**
     * 
     * Initialize the instance fields.
     * 
     * @param theFrame the JFrame which will contain this MenuBar.
     * @param thePanel the JPanel which this MenuBar will effect.
     */
    public PaintMenuBar(final JFrame theFrame, final DrawingPanel thePanel) {
        
        super();

        // Set the icons with default UW colors.
        myDrawIcon = new CustomIcon(DrawingPanel.UW_PURPLE);
        myFillIcon = new CustomIcon(DrawingPanel.UW_GOLD);
       
        myToolsMenu = new JMenu("Tools");
        
        myQuitButton = new JMenuItem("Quit");
        myClearButton =  new JMenuItem("Clear");
        
        myGroup = new ButtonGroup();
        myPanel = thePanel;
               
        setup(theFrame); // pass responsibility to the setup method.
    }
    


    /**
     * 
     * Set up the components of this JMenuBar.
     * 
     * @param theFrame the JFrame which will contain this MenuBar.
     */
    private void setup(final JFrame theFrame) {
        
        //Pass the responsibility of creating the menus to helper methods.
        //file menu needs a reference to the frame because it needs to close it.
        createFileMenu(theFrame); 
        createOptionsMenu();
        add(myToolsMenu);
        myToolsMenu.setMnemonic(KeyEvent.VK_T);
        createHelpMenu();
    }
    

    /**
     * 
     * Set up the file menu.
     * 
     * @param theFrame the JFrame which will contain this MenuBar.
     */
    private void createFileMenu(final JFrame theFrame) {
        
        final JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        myQuitButton.setMnemonic(KeyEvent.VK_Q);
        
        myQuitButton.addActionListener(new ActionListener() {
            
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                
                // Shut off the program when the Quit button is chosen.
                theFrame.dispatchEvent(new WindowEvent(theFrame,
                                                       WindowEvent.WINDOW_CLOSING));
            }
        });
             
        myClearButton.setMnemonic(KeyEvent.VK_C);
        
        //The clear button is disabled initially. 
        myClearButton.setEnabled(false); 
        myClearButton.addActionListener(new ActionListener() {
          
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                
                // Prevent the panel from drawing the previous shape.
                myPanel.setClearFlag(false);
                myPanel.clearDrawn(); // Clears the panel.
                
                myClearButton.setEnabled(false); //Disable the button, nothing to clear.
                
                
            }    
        });
        
        // The clear button listens for property changes.
        myClearButton.addPropertyChangeListener(this);
        fileMenu.add(myQuitButton);
        fileMenu.addSeparator();
        fileMenu.add(myClearButton);
        add(fileMenu);
        
    }
    
    /**
     * 
     * Create the options menu.
     */
    private void createOptionsMenu() {
        
        final JMenu optionsMenu = new JMenu("Options");
        optionsMenu.setMnemonic(KeyEvent.VK_O);
        
        final JMenu thicknessMenu = new JMenu("Thickness");
        thicknessMenu.setMnemonic(KeyEvent.VK_T);
        thicknessMenu.setToolTipText("Thickness of the Drawing Tool");
        
        final JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 0, MAX_THICKNESS,
                               INITIAL_THICKNESS);
        
        slider.setMajorTickSpacing(SLIDER_SPACING);
        slider.setMinorTickSpacing(1);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        
        // Add a listener to the slider so the thickness can be changed.
        slider.addChangeListener(new ChangeListener() {
           
            @Override
            public void stateChanged(final ChangeEvent theEvent) {
                
                // Set the panels thickness with the value from the slider.
                myPanel.setThickness(slider.getValue());  
            }
        });
        
        thicknessMenu.add(slider);
        
        //Lets the user select a drawing color.
        final JMenuItem drawColorItem = new JMenuItem(COLOR_STRING, myDrawIcon);
        drawColorItem.setMnemonic(KeyEvent.VK_D);
        drawColorItem.setToolTipText("The Draw Color of a Tool");
       
        // Add a color listener.
        drawColorItem.addActionListener(new ColorListener()); 
        
        // Lets the user select a filling color.
        final JMenuItem fillColorItem = new JMenuItem("Fill Color...", myFillIcon);
        fillColorItem.setMnemonic(KeyEvent.VK_F);
        fillColorItem.setToolTipText("Fill Color of the Ellipse and Ractangle");
        fillColorItem.addActionListener(new ColorListener());
        
        // Check box to set if a shape needs to be filled.
        final JCheckBoxMenuItem fillBox = new JCheckBoxMenuItem("Fill");
        fillBox.setToolTipText("Fill Shape?");
        
        // Add an accelerator to the fill check box.
        fillBox.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        fillBox.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                
                if (fillBox.getState()) {
                    
                    myPanel.setFill(true);
                
                } else {
                    
                    myPanel.setFill(false);
                }
               
            }
        });
        
        //Add the thickness menu.
        optionsMenu.add(thicknessMenu);
        optionsMenu.addSeparator();
        
        //Add the menu items.
        optionsMenu.add(drawColorItem);
        optionsMenu.add(fillColorItem);
        optionsMenu.addSeparator();
        
        optionsMenu.add(fillBox);
        add(optionsMenu);
        
    }
    
    /** 
     * 
     * Set up the help menu. 
     */
    private void createHelpMenu() {
    
        final JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        
        final JMenuItem aboutItem = new JMenuItem("About...");
        
        aboutItem.setMnemonic(KeyEvent.VK_A);
        aboutItem.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                
                final String message = "TCSS 305 PowerPaint \n"
                                     + "Autum 2016 \n" 
                                     + "Yaro Salo";
                
                JOptionPane.showMessageDialog(null, message, "About", 
                                  JOptionPane.PLAIN_MESSAGE, 
                                  new ImageIcon("images/rsz_paint.png"));
            }
        });

        helpMenu.add(aboutItem);
        add(helpMenu);
    }
    
    /**
     * 
     * Creates a radio button menu item, associates an action with the button,
     * adds the button to a button group, adds the button to the Tools menu.
     * 
     * @param theAction the Action to associate with the new button being created.
     */
    public void createMenuButton(final Action theAction) {

        final JRadioButtonMenuItem button = new JRadioButtonMenuItem(theAction);
       
        myGroup.add(button);
        myToolsMenu.add(button);
    }
    
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
       
        // Check if the event is coming from the clear button
        if ("button".equals(theEvent.getPropertyName())) {
            
            // enable the clear button.
            myClearButton.setEnabled((boolean) theEvent.getNewValue());
        }
        
    }

    
    /**
     * 
     * Listener for the color choice of drawing and filling color.
     * 
     * @author Yaro Salo
     * @version November 22 2016
     */
    private class ColorListener implements ActionListener {

        //Listener for the draw color button.
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            
            //Check if the event is coming from the drawing button.
            if (((AbstractButton) theEvent.getSource()).
                            getText().equals(COLOR_STRING)) {
                
                // Set the initial color of the color chooser to the currently selected
                //color.
                final Color result = JColorChooser.showDialog(null, 
                                                              "Draw Color Chooser", 
                                                              myPanel.getDrawColor());
                
                if (result != null) {
                    
                    myPanel.setDrawColor(result);
                    myDrawIcon.setColor(result); // Set the color of the draw icon.
                   
                }
                

            //Otherwise change the filling color.
            } else {
                
                // Set the initial color of the color chooser to the currently selected 
                //color.
                final Color result = JColorChooser.showDialog(null, "Fill Color Chooser", 
                                                              myPanel.getFillingColor());
                if (result != null) {
                    myPanel.setFillColor(result);
                    myFillIcon.setColor(result); // set the color of the fill icon.


                }

            }
            

        }
    }



}
