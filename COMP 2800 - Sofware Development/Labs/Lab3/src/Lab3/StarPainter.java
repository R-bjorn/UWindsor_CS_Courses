package Lab3;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.security.SecureRandom;

@SuppressWarnings("serial")
public class StarPainter extends StarGraphics{
	private int[] starXPoints = {55, 67, 109, 73, 83, 55, 27, 37, 1, 43};
    private int[] starYPoints = {0, 36, 36, 54, 96, 72, 96, 54, 36, 36};
    GeneralPath starDesign = new GeneralPath();
    
    public StarPainter() {
		drawStar(starDesign);
	}
    
    public void drawStar(GeneralPath path) {
    	path.moveTo(starXPoints[0], starYPoints[0]);
        
        for(int i=0; i<10; i++)
     	   path.lineTo(starXPoints[i], starYPoints[i]);
        
        path.closePath();
    }
    
    public void starActions(Graphics2D g) {
    	int startAngle = 360;
    	// For Random Color
        SecureRandom random = new SecureRandom(); 
    	   
        g = (Graphics2D) g.create();
        // rotate around origin and draw stars in random colors
        for (int count = 1; count <= 1; count++) 
        {
            double angle = startAngle - 90;
            // rotate coordinate system
            g.rotate(angleOfStarRotation * Math.PI / angle); //rotates as per the rotated angle    // 
           // set random drawing color
            g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
          // draw filled star
            g.fill(starDesign);
          // dispose the star
            g.dispose();
        }
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
       super.paintComponent(g);
       
       Graphics2D g2d = (Graphics2D) g.create();
       g2d.translate(250,150);       
       starActions(g2d);
       g2d.dispose();
       
    }
}
