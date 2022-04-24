import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class ColorSlider1 extends JPanel{
	
	private JPanel windowPanel;  
	private ColorSelector windowBgColor;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.add(new ColorSlider1());
	}
	
	public ColorSlider1() {
		setLayout(new BorderLayout());
		
		windowPanel = new JPanel();
		windowPanel.setPreferredSize(new Dimension(300, 300));
		windowPanel.setBackground(Color.GRAY);
		add(new JLabel("Slide to change background color"), BorderLayout.NORTH);
		add(windowPanel, BorderLayout.CENTER);
		windowBgColor = new ColorSelector(windowPanel);
		add(windowBgColor, BorderLayout.SOUTH);
	}
	
	public class ColorSelector extends JPanel{
//		1. Adding JSlider, SliderValueInt and ValueJLabel for selecting Red from 0 to 255
		private JSlider redSelector;
		private int redColorIndex;
		private JLabel redValue = new JLabel("0", SwingConstants.RIGHT);
//		2. Adding JSlider, SliderValueInt and ValueJLabel for selecting Green from 0 to 255
		private JSlider greenSelector;
		private int greenColorIndex;
		private JLabel greenValue = new JLabel("0", SwingConstants.RIGHT);
//		3. Adding JSlider, SliderValueInt and ValueJLabel for selecting Blue from 0 to 255
		private JSlider blueSelector;
		private int blueColorIndex;
		private JLabel blueValue = new JLabel("0", SwingConstants.RIGHT);
		
//		Adding Color Changing window panel
		private JPanel windowPanel;

		GridBagLayout panelLayout = new GridBagLayout();
		final static boolean shouldFill = true;
		
		
//		Constructor
		public ColorSelector(JPanel panel) {
			windowPanel = panel;
//			Setting GridLayout so its easier to set the text, slider and its value for the panel
			setLayout(panelLayout); // 2 by 3; gaps of 5
//			Adding 3 JSliders and their ChangeListener
			addColorSliders();	
//			adding everything to this panel in order
			addToPanel();
		}
		
//		Function to add Red, Green and Blue JSliders and their ChangeListener
		private void addColorSliders() {
//			1. Red Color Horizontal Slider with values between (0 - 255) 
			redSelector = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 1);
//			adding ChangeListener
			redSelector.addChangeListener(new ChangeListener() { // anonymous inner class  
		            // handle change in slider value
		            @Override
		            public void stateChanged(ChangeEvent e) {
//		            	getting red color index
		               redColorIndex = redSelector.getValue();
		               redValue.setText(String.valueOf(redColorIndex));
//		               changing the window background color every time slider moves
		               changeWindowBgColor();
		            }
		         } 
		      );
			
//			2. Green Color Horizontal Slider with values between (0 - 255)
			greenSelector = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 1);
//			adding ChangeListener
			greenSelector.addChangeListener(new ChangeListener() { // anonymous inner class  
		            // handle change in slider value
		            @Override
		            public void stateChanged(ChangeEvent e) {
//		            	getting green color index
		               greenColorIndex = greenSelector.getValue();
		               greenValue.setText(String.valueOf(greenColorIndex));
//		               changing the window background color every time slider moves
		               changeWindowBgColor();
		            }
		         } 
		      ); 
			
//			3. Blue Color Horizontal Slider with values between (0 - 255)
			blueSelector = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 1);
//			adding ChangeListener
			blueSelector.addChangeListener(new ChangeListener() { // anonymous inner class  
		            // handle change in slider value
		            @Override
		            public void stateChanged(ChangeEvent e) {
//		            	getting blue color index
		               blueColorIndex = blueSelector.getValue();
		               blueValue.setText(String.valueOf(blueColorIndex));
//		               changing the window background color every time slider moves
		               changeWindowBgColor();
		            }
		         } 
		      );
		}
//		Function to add every information to the frame at the bottom
//		Need to use the gridBagLayout or arranging it to be relative.
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
			windowPanel.setBackground(new Color(redColorIndex, greenColorIndex, blueColorIndex));
			windowPanel.repaint();
		}
	}

}
