package HA2;

import Shape.Shape;
import Shape.Rectangle;
import Shape.Oval;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{
//	This is the Main Frame 
//	1. Adding Options to show in JComboBox
	private static final String[] options = {"Oval", "Rectangle"};
//	2. Adding JComboBox Otions for user to choose 
	private final JComboBox<String> comboBox = new JComboBox<String>(options);
//	3. Adding the JPanel where user will draw shapes
	private Shape shape = new Shape(this);
	
	// Constructor
	public MainFrame() {
		
//		Adding BorderLayout to easily define areas for particular things
		this.setLayout(new BorderLayout());	
//		Adding Title
		this.setTitle("Drawing rectangles and ovals");	
		
//		Adding JComboBox to the Top/North
		this.CreatingJComboBox(BorderLayout.NORTH);
				
//		Adding Shape JPanel to the center
		this.add(shape, BorderLayout.CENTER);
		
//		Adding a text to the bottom
		this.add(new JLabel("Drag Mouse to Draw"),BorderLayout.SOUTH);
	}

	private void CreatingJComboBox(String location) {
//		Adding Action Listener for JComboBox
		ActionListener comboBoxActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == comboBox) {
//					First thing we should do is remove the current jpanel on the frame
					MainFrame.this.getContentPane().remove(shape);
//					If user choose oval then Oval Panel will be generated and user can drag and draw ovals of any size using mouse
					if( comboBox.getSelectedItem().equals("Oval"))
						shape = new Oval(MainFrame.this);
//					If user choose oval then Rectangle Panel will be generated and user can drag and draw rectangles of any size using mouse
					else if(comboBox.getSelectedItem().equals("Rectangle"))
						shape = new Rectangle(MainFrame.this);
				}
//				Adding the new Frame according to the user seleciton and validate it
				MainFrame.this.add(shape, BorderLayout.CENTER);
				MainFrame.this.validate();
			}
		};
		
//		Implementing ActionListener to comboBox
		comboBox.addActionListener(comboBoxActionListener);
//		Adding ComboBox to the Frame at the Top/North
		this.add(comboBox, location);		
	}
}	