//package ServerSide;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

@SuppressWarnings("serial")
public class Page extends JFrame{
	String ip_addr;
	String student_name;
	String file_name;
	
	public static void main(String args[]) {
		Page frame = new Page("127.0.0.1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(500,200);	
//		frame.add(new Page("127.0.0.1"));
	}
	
	public Page(String addr) {
		super("Simplest Browser");
		this.ip_addr = addr;
		
		setLayout(new BorderLayout());
		
		addNorthBar();
		addUserInput();
	}
	
	public void addNorthBar() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("server IP:   "), BorderLayout.WEST);
		JTextField fileInput = new JTextField("");
		fileInput.setColumns(20);
		fileInput.setBackground(Color.WHITE);
		fileInput.setText(ip_addr);
		panel.add(fileInput, BorderLayout.CENTER);
		add(panel, BorderLayout.NORTH);
	}

	public void addUserInput() {
		JPanel panel = new JPanel();
		JPanel stdPanel = new JPanel();
		stdPanel.setLayout(new BorderLayout());
		stdPanel.add(new JLabel("student name "), BorderLayout.WEST);
		JTextField nameInput = new JTextField("");
		nameInput.setColumns(15);
		nameInput.setBackground(Color.WHITE);
		nameInput.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				student_name = nameInput.getText();
			}
		});
		stdPanel.add(nameInput, BorderLayout.CENTER);
		
		JPanel filePanel = new JPanel();		
		filePanel.setLayout(new BorderLayout());
		filePanel.add(new JLabel("file_name "), BorderLayout.WEST);		
		JTextField fileInput = new JTextField("");
		fileInput.setColumns(15);
		fileInput.setBackground(Color.WHITE);
		fileInput.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				file_name = fileInput.getText();
			}
			
		});
		filePanel.add(fileInput, BorderLayout.CENTER);
		
		panel.add(stdPanel);
		panel.add(filePanel);
		add(panel, BorderLayout.CENTER);
		
		JButton upload = new JButton("upload");
		upload.setBackground(Color.WHITE);
		upload.setSize(50, 300);
		upload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nameInput.setText("");
				fileInput.setText("");
				// TODO Auto-generated method stub
				File new_student = new File(System.getProperty("user.dir") + "\\" +  student_name);
				new_student.mkdir();
			}
			
		});
		JPanel upload_panel = new JPanel();
		upload_panel.add(upload);
		add(upload_panel, BorderLayout.SOUTH);
		
		
		
	}
}
