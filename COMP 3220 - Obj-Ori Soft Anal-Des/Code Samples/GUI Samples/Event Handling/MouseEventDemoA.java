import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class MouseEventDemoA extends JPanel implements MouseListener, MouseMotionListener {
    BlankAreaA blankArea;
    JTextArea textArea;
    
    public static void main(String[] args) {
        
        createAndShowGUI();
    }
    
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("MouseEventDemo");
        
        //Create and set up the content pane
        JComponent newContentPane = new MouseEventDemoA();
        frame.setContentPane(newContentPane);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public MouseEventDemoA() {
        super(new GridLayout(0,1));							//Don't worry about this, it's just another layout manager
        blankArea = new BlankAreaA();						//Instantiate our blank area
        add(blankArea);										//Add this to our panel
        textArea = new JTextArea();							//Create the text area
        textArea.setEditable(false);						//Don't let the user edit the text
        JScrollPane scrollPane = new JScrollPane(textArea);	//Add a scrollbar to the text
        scrollPane.setVerticalScrollBarPolicy(				//Have the vertical scroll always there
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(200, 75));
        add(scrollPane);									//Add the scrollbar to the panel
        
        //Register for mouse events on blankArea and the panel.
        blankArea.addMouseListener(this);					//Add a mouse listener to the blankArea
        this.addMouseListener(this);								//Add a mouse listener to this as well
        
        blankArea.addMouseMotionListener(this);				//Add a mouse motion listener to the blankArea
        this.addMouseMotionListener(this);						//Add a mouse motion listener to this as well
        
        setPreferredSize(new Dimension(450, 450));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));	//Adds a border to the panel
    }
    
    void eventOutput(String eventDescription, MouseEvent e) {		//We just forward mouse events to this method
        textArea.append(eventDescription + " detected on "			//It adds text describing the event to the text area
                + e.getComponent().getClass().getName()				//Which type of event is being fired
                + ".\n");
    }
    
    public void mousePressed(MouseEvent e) {				//Mouse pressed event
        eventOutput("Mouse pressed (# of clicks: "				//Fires when the mouse is pushed down
                + e.getClickCount() + ")", e);
    }
    
    public void mouseReleased(MouseEvent e) {				//Mouse released event
        eventOutput("Mouse released (# of clicks: "				//Fires when the mouse is released
                + e.getClickCount() + ")", e);
    }
    
    public void mouseEntered(MouseEvent e) {				//Mouse entered event
        eventOutput("Mouse entered", e);						//Fires when the mouse enters the listening component
    }
    
    public void mouseExited(MouseEvent e) {					//Mouse exited event
        eventOutput("Mouse exited", e);							//Fires when the mouse exits the listening component
    }
    
    public void mouseClicked(MouseEvent e) {				//Mouse clicked event
        eventOutput("Mouse clicked (# of clicks: "				//Fires when the mouse is pushed down and released with minimal displacement
                + e.getClickCount() + ")", e);						//If there is displacement, it's a drag not a click
        blankArea.repaint();
    }

	public void mouseDragged(MouseEvent e) {
		eventOutput("Mouse dragged (" + e.getXOnScreen() + ", " + e.getYOnScreen() + ")", e);
		
	}

	public void mouseMoved(MouseEvent e) {
		eventOutput("Mouse moved (" + e.getXOnScreen() + ", " + e.getYOnScreen() + ")", e);
	}

}