package Lab2;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
//This Class is an extend of JFrame so we can add JPanel and JSliders and directly add things here
// and make an object in the main so its easy to organize.
public class ColorFrame extends JFrame{
	
//	Making the window where our color will change according to user input
	private JPanel windowPanel; 
//	Adding the ColorSelector with three JSliders 
	private ColorSelector windowBgColor;
	
//	Constructor
	public ColorFrame() {
//		Adding BorderLayout to organize content
		setLayout(new BorderLayout());
//		Adding Title for the frame
		setTitle("RGB Color Selector");
		
//		Adding the windowPanel where we will see the color changing 
		windowPanel = new JPanel();
		windowPanel.setPreferredSize(new Dimension(300, 300));
		windowPanel.setBackground(Color.GRAY);
		
//		Adding how to use on the top
		this.add(new JLabel("Slide to change background color"), BorderLayout.NORTH);
//		Adding color changing window in the center
		this.add(windowPanel, BorderLayout.CENTER);
//		adding 3 sliders to select Red, Green, Blue Color Indexes
		windowBgColor = new ColorSelector(windowPanel);
		this.add(windowBgColor, BorderLayout.SOUTH);
	}
}
