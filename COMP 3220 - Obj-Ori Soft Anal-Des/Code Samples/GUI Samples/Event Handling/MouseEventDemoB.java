import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class MouseEventDemoB extends JPanel {
    BlankAreaB blankArea;
    JTextArea textArea;
    
    public static void main(String[] args) {
        
        createAndShowGUI();
    }
    
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("MouseEventDemo");
        
        //Create and set up the content pane
        JComponent newContentPane = new MouseEventDemoB();
        frame.setContentPane(newContentPane);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public MouseEventDemoB() {
        super(new GridLayout(0,1));							//Don't worry about this, it's just another layout manager
        blankArea = new BlankAreaB();						//Instantiate our blank area
        add(blankArea);										//Add this to our panel
        textArea = new JTextArea();							//Create the text area
        textArea.setEditable(false);						//Don't let the user edit the text
        JScrollPane scrollPane = new JScrollPane(textArea);	//Add a scrollbar to the text
        scrollPane.setVerticalScrollBarPolicy(				//Have the vertical scroll always there
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(200, 75));
        add(scrollPane);									//Add the scrollbar to the panel
        
        //Register for mouse events on blankArea and the panel.
        blankArea.addMouseListener(new MouseListener(){
        	public void mousePressed(MouseEvent e) {				//Mouse pressed event
                eventOutput("Mouse pressed on blankArea (# of clicks: "				//Fires when the mouse is pushed down
                        + e.getClickCount() + ")");
                blankArea.setMouseDownPosition(e.getX(), e.getY());
            }
            
            public void mouseReleased(MouseEvent e) {				//Mouse released event
                eventOutput("Mouse released on blankArea (# of clicks: "				//Fires when the mouse is released
                        + e.getClickCount() + ")");
                blankArea.setMouseUpPosition(e.getX(), e.getY());
                blankArea.repaint();
            }
            
            public void mouseEntered(MouseEvent e) {				//Mouse entered event
                eventOutput("Mouse entered on blankArea");						//Fires when the mouse enters the listening component
            }
            
            public void mouseExited(MouseEvent e) {					//Mouse exited event
                eventOutput("Mouse exited on blankArea");							//Fires when the mouse exits the listening component
            }
            
            public void mouseClicked(MouseEvent e) {				//Mouse clicked event
                eventOutput("Mouse clicked on blankArea (# of clicks: "				//Fires when the mouse is pushed down and released with minimal displacement
                        + e.getClickCount() + ")");						//If there is displacement, it's a drag not a click
            }
        });					//Add a mouse listener to the blankArea
        
        this.addMouseListener(new MouseListener(){
        	public void mousePressed(MouseEvent e) {				//Mouse pressed event
                eventOutput("Mouse pressed on panel (# of clicks: "				//Fires when the mouse is pushed down
                        + e.getClickCount() + ")");
            }
            
            public void mouseReleased(MouseEvent e) {				//Mouse released event
                eventOutput("Mouse released on panel (# of clicks: "				//Fires when the mouse is released
                        + e.getClickCount() + ")");
            }
            
            public void mouseEntered(MouseEvent e) {				//Mouse entered event
                eventOutput("Mouse entered on panel");						//Fires when the mouse enters the listening component
            }
            
            public void mouseExited(MouseEvent e) {					//Mouse exited event
                eventOutput("Mouse exited on panel");							//Fires when the mouse exits the listening component
            }
            
            public void mouseClicked(MouseEvent e) {				//Mouse clicked event
                eventOutput("Mouse clicked on panel (# of clicks: "				//Fires when the mouse is pushed down and released with minimal displacement
                        + e.getClickCount() + ")");						//If there is displacement, it's a drag not a click
            }
        });								//Add a mouse listener to this as well

        blankArea.addMouseMotionListener(new MouseMotionListener(){

        	@Override
        	public void mouseDragged(MouseEvent e) {
        		eventOutput("Mouse dragged on blankArea (" + e.getXOnScreen() + ", " + e.getYOnScreen() + ")");
        		
        	}

        	@Override
        	public void mouseMoved(MouseEvent e) {
        		eventOutput("Mouse moved on blankArea (" + e.getXOnScreen() + ", " + e.getYOnScreen() + ")");
        		
        	}
        	
        });				//Add a mouse motion listener to the blankArea
        
        this.addMouseMotionListener(new MouseMotionListener(){

        	@Override
        	public void mouseDragged(MouseEvent e) {
        		eventOutput("Mouse dragged on panel (" + e.getXOnScreen() + ", " + e.getYOnScreen() + ")");
        		
        	}

        	@Override
        	public void mouseMoved(MouseEvent e) {
        		eventOutput("Mouse moved on panel (" + e.getXOnScreen() + ", " + e.getYOnScreen() + ")");
        		
        	}
        });						//Add a mouse motion listener to this as well
        
        setPreferredSize(new Dimension(450, 450));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));	//Adds a border to the panel
    }
    
    void eventOutput(String eventDescription) {		//We just forward mouse events to this method
        textArea.append(eventDescription + " detected.\n");
    }
}