import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;

public class BlankAreaB extends JLabel {
    Dimension minSize = new Dimension(100, 50);
    private int x1, y1, x2, y2;
    
    public BlankAreaB() {
    	this.setBackground(Color.YELLOW);
        setOpaque(true);
    }

    public Dimension getMinimumSize() {
        return minSize;
    }

    public Dimension getPreferredSize() {
        return minSize;
    }
    
    public void setMouseDownPosition(int x, int y){
    	x1 = x;
    	y1 = y;
    	System.out.println(x1 + " " + y1);
    }
    
    public void setMouseUpPosition(int x, int y){
    	x2 = x;
    	y2 = y;
    	System.out.println(x2 + " " + y2);
    }
    
    public void paint(Graphics g){
    	//do stuff
    	super.paint(g);
    	g.setColor(Color.BLACK);
    	g.drawLine(x1, y1, x2, y2);
    }
}