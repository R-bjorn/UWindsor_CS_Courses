package Lab3;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class StarGraphics extends JPanel{
	private boolean toggleDir = false;
	private int speed = 10;
	private Timer timer;
	protected double angleOfStarRotation = 0;
	
	public StarGraphics() {
		
		setPreferredSize(new Dimension(500, 470));
		setBackground(Color.BLACK);
		setLayout(new BorderLayout());
		
		startTimer();
	}
	
	public void startTimer() {
		timer = new Timer(speed, new ActionListener() {
			public void actionPerformed(ActionEvent e){
//				System.out.println(angleOfStarRotation);
				if(!toggleDir)	//rotates clockwise
					angleOfStarRotation = angleOfStarRotation + 1;
				else		//rotates counterclockwise
					angleOfStarRotation = angleOfStarRotation - 1;
                  
//                if (angleOfStarRotation == 360 || angleOfStarRotation == -360)  // If there is a full circle, it will reset the angle to zero
//                    angleOfStarRotation = 0;
                  
                repaint();
			}});
        timer.start();
	}
	
	public void ChangeSpeed(int newSpeed) {	
		this.speed = newSpeed;
		timer.setDelay(speed);
	}	
	public void ChangeDirections() {toggleDir = !toggleDir;	}

}
