package Lab3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	private StarGraphics frameStar;
	private JButton starToggle;
	public JSlider starSpeed;
	
	
	public MainFrame() {
//		Setting some properties of the frame
		setTitle("Star Moving with Internal Events");
		setLayout(new BorderLayout());
		
//		Initializing the panel which has rotating star
		frameStar = new StarPainter();
		add(frameStar);
		
//		Adding the bottom toggle button and slider
		addToggler();	
	}
	
//	Getter for the slider value
	public int sliderSpeed() {
		return starSpeed.getValue();
	}
	
	
//	Adds Button to change the direction of the star
	private void addToggler() {
//		Adding another jpanel which has layout set to null so i can add button of my size
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(20,80));
		panel.setBackground(Color.WHITE);
		
//		Initializing button and its action listener to change star direction
		starToggle = new JButton("Toggle");
		starToggle.setBounds(190, 0, 80, 20);
		starToggle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == starToggle) {
					frameStar.ChangeDirections();
				}
			}
		});
		
//		Adding button to panel
		panel.add(starToggle);
		
//		Adding slider to the panel
		addSlider(panel);
		
//		Adding panel to the main Panel at the bottom
		add(panel, BorderLayout.SOUTH);
	}
	
	private void addSlider(JPanel panel) {
//		Adding Slider and it's properties
		starSpeed = new JSlider(JSlider.HORIZONTAL, 0, 20, 5);
		starSpeed.setMajorTickSpacing(10);
		starSpeed.setMinorTickSpacing(1);
		starSpeed.setPaintTicks(true);
		starSpeed.setPaintLabels(true);
		starSpeed.setBounds(70,30,400, 45);
		
//		Adding Slider-ChangeListener to change the rotation speed of the star
		starSpeed.addChangeListener(new ChangeListener() { // anonymous inner class  
            // handle change in slider value
            @Override
            public void stateChanged(ChangeEvent e) {
            	frameStar.ChangeSpeed(starSpeed.getValue());
            }
         } 
      );
		
//		Adding label besides the slider
		JLabel label = new JLabel("Speed : ");
		label.setBounds(10 , 10, 80, 80);
		panel.add(label);
		panel.add(starSpeed);
	}
}
