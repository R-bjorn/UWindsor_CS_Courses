package Shape;

import java.awt.BorderLayout;

import java.awt.Graphics;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Oval extends Shape{

	public Oval(JFrame frame) {
		super(frame);
		
		frame.add(this, BorderLayout.CENTER);
		frame.setTitle("Drawing Ovals");
		MouseHandler handler = new MouseHandler(); 
	    this.addMouseListener(handler); 
	    this.addMouseMotionListener(handler); 
	}
	
	public void drawPerfectOval(Graphics g, int x, int y, int x2, int y2) {
        int relativeX = Math.min(x,x2);
        int relativeY = Math.min(y,y2);
        int relativeWidth = Math.abs(x-x2);
        int relativeHeight = Math.abs(y-y2);
        g.drawOval(relativeX, relativeY, relativeWidth, relativeHeight);
    }

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 	    
	    drawPerfectOval(g, startPointX, startPointY, shapeWidth, shapeHeight);
	}
}
