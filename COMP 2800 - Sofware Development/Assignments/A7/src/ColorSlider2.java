import java.awt.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class ColorSlider2 extends JPanel{
	public int currentR = 0;
	public int currentG = 0;	
	public int currentB = 0;
	
	
	private JPanel windowPanel;  
	private ColorSelector colorSelector;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.add(new ColorSlider2());
	}
	
	public ColorSlider2() {
		setLayout(new BorderLayout());
		
		windowPanel = new JPanel();
		windowPanel.setPreferredSize(new Dimension(300, 300));
		windowPanel.setBackground(Color.GRAY);
		add(new JLabel("Slide to change background color"), BorderLayout.NORTH);
		add(windowPanel, BorderLayout.CENTER);
		colorSelector = new ColorSelector(windowPanel);
		add(colorSelector, BorderLayout.SOUTH);
		
	}
	
	
	public int getCurrentR() {
		return currentR;
	}

	public void setCurrentR(int currentR) {
		this.currentR = currentR;
		colorSelector.redSelector.setValue(this.currentR);
	}

	public int getCurrentG() {
		return currentG;
	}

	public void setCurrentG(int currentG) {
		this.currentG = currentG;
		colorSelector.greenSelector.setValue(this.currentG);
	}

	public int getCurrentB() {
		return currentB;
	}

	public void setCurrentB(int currentB) {
		this.currentB = currentB;
		colorSelector.blueSelector.setValue(this.currentB);
	}

	
	public class ColorSelector extends JPanel{
		private JSlider redSelector;
		private JLabel redValue = new JLabel("0", SwingConstants.RIGHT);

		private JSlider greenSelector;
		private JLabel greenValue = new JLabel("0", SwingConstants.RIGHT);

		private JSlider blueSelector;
		private JLabel blueValue = new JLabel("0", SwingConstants.RIGHT);
		
		private JPanel windowPanel;

		GridBagLayout panelLayout = new GridBagLayout();
		final static boolean shouldFill = true;
		
		public ColorSelector(JPanel panel) {
			windowPanel = panel;
			setLayout(panelLayout); // 2 by 3; gaps of 5
			addColorSliders();	
			addToPanel();
		}
		
//		Function to add Red, Green and Blue JSliders and their ChangeListener
		private void addColorSliders() {
			redSelector = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 1);
			redSelector.setValue(currentR);
			redValue.setText(String.valueOf(currentR));
			redSelector.addChangeListener(new ChangeListener() { // anonymous inner class  
		            @Override
		            public void stateChanged(ChangeEvent e) {
		               currentR = redSelector.getValue();
		               redValue.setText(String.valueOf(currentR));
		               changeWindowBgColor();
//		               getCurrentR();
		            }
		         }
		      );
			
			greenSelector = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 1);
			greenSelector.setValue(currentG);
			greenValue.setText(String.valueOf(currentG));
			greenSelector.addChangeListener(new ChangeListener() { // anonymous inner class  
		            @Override
		            public void stateChanged(ChangeEvent e) {
		               currentG = greenSelector.getValue();
		               greenValue.setText(String.valueOf(currentG));
		               changeWindowBgColor();
		            }
		         } 
		      ); 
			
			blueSelector = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 1);
			blueSelector.setValue(currentB);
			blueValue.setText(String.valueOf(currentB));
			blueSelector.addChangeListener(new ChangeListener() { // anonymous inner class  
		            @Override
		            public void stateChanged(ChangeEvent e) {
		               currentB = blueSelector.getValue();
		               blueValue.setText(String.valueOf(currentB));
		               changeWindowBgColor();
		            }
		         } 
		      );
			changeWindowBgColor();
		}		
//		Function to add every information to the frame at the bottom
		private void addToPanel() {
			GridBagConstraints panelGrid = new GridBagConstraints();

			panelGrid.fill = GridBagConstraints.HORIZONTAL;
			panelGrid.weightx = 0.1;
			panelGrid.gridx = 0;
			panelGrid.gridy = 0;
			this.add(new JLabel("Red"), panelGrid);
			panelGrid.weightx = 2;
			panelGrid.gridx = 1;
			panelGrid.gridy = 0;
			this.add(redSelector, panelGrid);
			panelGrid.weightx = 0.1;
			panelGrid.gridx = 2;
			panelGrid.gridy = 0;
			this.add(redValue, panelGrid);
			panelGrid.weightx = 0.1;
			panelGrid.gridx = 0;
			panelGrid.gridy = 1;
			this.add(new JLabel("Green"), panelGrid);
			panelGrid.weightx = 2;
			panelGrid.gridx = 1;
			panelGrid.gridy = 1;
			this.add(greenSelector, panelGrid);
			panelGrid.weightx = 0.1;
			panelGrid.gridx = 2;
			panelGrid.gridy = 1;
			this.add(greenValue, panelGrid);
			panelGrid.weightx = 0.1;
			panelGrid.gridx = 0;
			panelGrid.gridy = 2;
			this.add(new JLabel("Blue"), panelGrid);
			panelGrid.weightx = 2;
			panelGrid.gridx = 1;
			panelGrid.gridy = 2;
			this.add(blueSelector, panelGrid);
			panelGrid.weightx = 0.1;
			panelGrid.gridx = 2;
			panelGrid.gridy = 2;
			this.add(blueValue, panelGrid);
		}
//		Function to change the background of the windowPanel 
		private void changeWindowBgColor() {
			windowPanel.setBackground(new Color(currentR, currentG, currentB));
			windowPanel.repaint();
//			System.out.println("colorR : " + currentR + "\ncolorG : " + currentG + "\ncolorB : " + currentB + "\n");
		}
	}
}
