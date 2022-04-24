import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		Object classInst;
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
			fileInput.addActionListener(new ActionListener() {
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
					getContentPane().remove(mainFramePanel);
					propertiesFrame.dispose();
				}
				c = Class.forName(fileName);
//				classInst = c.getDeclaredConstructor().newInstance();
				Constructor<?> cons = c.getConstructor();
//				Object object = cons.newInstance(new Object[] {});
				classInst = cons.newInstance();
				mainFramePanel = (JPanel) classInst;
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
		
		public void refresh() {
			propertiesFrame.dispose();
			showProperties();
		}
		
		public void showProperties(){
			
			propertiesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			propertiesFrame.setVisible(true);
			propertiesFrame.setSize(400, 500);
			
			JPanel propertiesPanel = new JPanel();
			propertiesPanel.setLayout(new BoxLayout(propertiesPanel, BoxLayout.PAGE_AXIS));
			
			for(Field field : classInst.getClass().getFields()) {
				// Ignore extended class fields
				if(!classInst.getClass().isAssignableFrom(field.getDeclaringClass())) { continue; }
				
				if(field.getType().equals(boolean.class) || field.getType().equals(int.class)) {
					JPanel panel2 = new JPanel();
					panel2.setLayout(new GridLayout(1,0));
					panel2.add(new JLabel(field.getName()));
					
					JTextField input = new JTextField();
					input.setColumns(20);
					input.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								String methodName = "set" + field.getName().substring(0,1).toUpperCase() + field.getName().substring(1);
								
								if(field.getType().equals(boolean.class)) // setting boolean
									classInst.getClass().getMethod(methodName, new Class[] {boolean.class}).invoke(classInst, Boolean.parseBoolean(input.getText()));
								else// setting integer
									classInst.getClass().getMethod(methodName, new Class[] {int.class}).invoke(classInst, Integer.parseInt(input.getText()));
								
							} catch (IllegalArgumentException | IllegalAccessException | SecurityException | NoSuchMethodException | InvocationTargetException e1) {
								e1.printStackTrace();
							}
							PanelGround.this.revalidate();
							PanelGround.this.repaint();
					
						}});
					
					try {
						String methodName = "get" + field.getName().substring(0,1).toUpperCase() + field.getName().substring(1);
						String result = "";
						if(field.getType().equals(boolean.class))
							result = classInst.getClass().getMethod(methodName, new Class[] {}).invoke(classInst,(Object[]) null).toString();		
						else// setting integer
							result = classInst.getClass().getMethod(methodName, new Class[] {}).invoke(classInst,(Object[]) null).toString();
						
						input.setText(result);
						
					} catch (IllegalArgumentException | IllegalAccessException | SecurityException | InvocationTargetException | NoSuchMethodException e) { e.printStackTrace();}
					
					panel2.add(input);
					propertiesPanel.add(panel2);
				}
			}
			
			// Add Reset Button
			JButton reset = new JButton("Refresh");
			reset.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        refresh();
			    }
			});
			reset.setBackground(Color.WHITE);
			JPanel panel2 = new JPanel();
			panel2.setLayout(new GridLayout(1,0));
			panel2.add(reset);
			panel2.add(new JLabel());
			propertiesPanel.add(panel2);
			
			// Add Scroll Panel
			JScrollPane scrollPanel = new JScrollPane(propertiesPanel, 
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			propertiesFrame.add(scrollPanel);
		}
		
	}
}
