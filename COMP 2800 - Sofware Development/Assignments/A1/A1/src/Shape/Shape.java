package Shape;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Shape extends JPanel{	
//	Adding Coordinates for the drawing
	protected int startPointX, startPointY, shapeWidth, shapeHeight;
	
//	Constructor
	public Shape(JFrame frame) {
//		Resetting Coordinates to 0
		startPointX = startPointY = shapeWidth = shapeHeight = 0;
		setPreferredSize(new Dimension(500, 500));
		setLayout(new BorderLayout());
	    setBackground(Color.WHITE);
	    frame.add(this, BorderLayout.CENTER); // add panel to JFrame	    
	}

//	Set Starting points
	public void setStartPoint(int x, int y) {
        this.startPointX = x;
        this.startPointY = y;
    }
//	Set Ending Point and calculate the Width and Height
    public void setEndPoint(int x, int y) {
        shapeWidth = x;
        shapeHeight = y;
    }

//    MouseListener Handler Class
	public class MouseHandler implements MouseListener, 
    MouseMotionListener {
	    // MouseListener event handlers
	    @Override
	    public void mouseClicked(MouseEvent event){} 
	    @Override
	    public void mouseEntered(MouseEvent event){}
	    @Override
	    public void mouseMoved(MouseEvent event){} 
	
	    // handle event when mouse pressed
	    @Override
	    public void mousePressed(MouseEvent event)
	    {
	    	setStartPoint(event.getX(), event.getY());
	    }
	    
	    // handle event when user drags mouse with button pressed
	    @Override
	    public void mouseDragged(MouseEvent event)
	    {
	    	setEndPoint(event.getX(), event.getY());
            repaint();
	    }
	
	    // handle event when mouse released 
	    @Override
	    public void mouseReleased(MouseEvent event)
	    {
	    	setEndPoint(event.getX(), event.getY());
            repaint();
	    }

	    // handle event when mouse exits area
	    @Override
	    public void mouseExited(MouseEvent event)
	    {
	    	
	    } 
	}
} 
	   
