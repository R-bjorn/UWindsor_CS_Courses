import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;


public class Main {
	
	public static void main(String args[]) {
		new Main();
	}
	
	public Main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				PanelGround pg = new PanelGround();
//				pg.setSize(500 , 500);
				pg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				pg.setVisible(true);
				pg.pack();
			}
		});
	}
	
	@SuppressWarnings("serial")
	private class PanelGround extends JFrame{
		String fileName;
		Class<?> c;
		JFrame propertiesFrame = new JFrame("Properties");	
		JPanel mainFramePanel;
	
		public PanelGround() {
			super("Assignment 6");
			addNorthBar();
		}
		
		private void addNorthBar() {
			JPanel panel = new JPanel();
			panel.add(new JLabel("typing in an executable class:   "), BorderLayout.WEST);
			JTextField fileInput = new JTextField("");
			fileInput.setColumns(20);
			fileInput.setBackground(Color.WHITE);			
			fileInput.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					fileName = fileInput.getText();
					addClass(fileName);
				}
				
			});
			
			panel.add(fileInput, BorderLayout.CENTER);
			add(panel, BorderLayout.NORTH);
		}
		
		public void addClass(String fileName) {
			try {
				if(c != null) {
					System.out.println("Hello");
					getContentPane().remove(mainFramePanel);
					propertiesFrame.dispose();
				}
				c = Class.forName(fileName);
				Constructor<?> cons = c.getConstructor();
				Object object = cons.newInstance(new Object[] {});
				mainFramePanel = (JPanel) object;
				this.add(mainFramePanel, BorderLayout.CENTER);
				this.revalidate();
				this.repaint();
				this.setVisible(true);
				this.setSize(800,600);
				showProperties();
			} catch (ClassNotFoundException | NoSuchMethodException 
					| SecurityException | InstantiationException | IllegalAccessException | 
					IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			} 
		}
		
		public void showProperties() {
			
			propertiesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			propertiesFrame.setVisible(true);
			propertiesFrame.setSize(300, 500);
			
			Field[] classFeilds = c.getFields(); 

			JLabel[] feildLabels = new JLabel[classFeilds.length];
			int i = 0;
			
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			
			for(Field field : classFeilds) {				
				feildLabels[i] = new JLabel("<html>Field Name : " + field.getName() + "<br/>" + "Field  Type : " + field.getType() + "<br/><br/></html>");
				panel.add(feildLabels[i++]);
			}
			
			JScrollPane scrollPanel = new JScrollPane(panel, 
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			propertiesFrame.add(scrollPanel);			
		}
		
	}
}
