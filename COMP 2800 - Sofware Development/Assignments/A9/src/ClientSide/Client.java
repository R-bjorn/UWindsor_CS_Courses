package ClientSide;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Client {
	Socket sock;
	String ip_addr;
	Class<?> c;
	Object classInst;
	
	public static void main(String args[]) throws IOException {
		new Client();
	}
	
	public Client() {
		
		addFrame();
	}
	
	public void addFrame() {
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(500,500);
	}
	
	@SuppressWarnings("serial")
	public class MainFrame extends JFrame{
		public MainFrame() {
			super("Client");
			addNorthbar();
		}
		
		public void addNorthbar() {
			JPanel panel = new JPanel();
			panel.add(new JLabel("server IP:   "), BorderLayout.WEST);
			JTextField fileInput = new JTextField("");
			fileInput.setColumns(20);
			fileInput.setBackground(Color.WHITE);
			fileInput.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					ip_addr = fileInput.getText();
					System.out.println("Connecting Server : " + ip_addr);
					connectServer();
					addClass("Page");
					dispose();
				}
				
			});
			panel.add(fileInput, BorderLayout.CENTER);
			add(panel, BorderLayout.NORTH);
		}
		
		public void addClass(String fileName) {
			System.out.println("Running " + fileName + "...");
//			Runtime run = Runtime.getRuntime();
			try {
				String[] command = {"java.exe", fileName};
			    ProcessBuilder pb = new ProcessBuilder(command);
			    pb.redirectErrorStream(true);
			    pb.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		public void connectServer() {
			byte[] aByte = new byte[1];
	        int bytesRead;
	        
			try {
				sock = new Socket(ip_addr, 8888);
				System.out.println("Connect with "+ip_addr);
				InputStream in =sock.getInputStream();
//				BufferedReader bf = new BufferedReader(in); 
//				int fileCount = Integer.parseInt(bf.readLine());
//				System.out.println("Files : " + fileCount);
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				if(in != null) {
					FileOutputStream fos = null;
		            BufferedOutputStream bos = null;
		            try {
		                fos = new FileOutputStream(new File(".").getCanonicalPath() + "\\Page.Class");
		                System.out.println("Creating : " + new File(".").getCanonicalPath() + "\\Page.Class");
		                bos = new BufferedOutputStream(fos);
		                bytesRead = in.read(aByte, 0, aByte.length);

		                do {
		                        baos.write(aByte);
		                        bytesRead = in.read(aByte);
		                } while (bytesRead != -1);

		                bos.write(baos.toByteArray());
		                bos.flush();
		                bos.close();
		            } catch (IOException ex) {
		                // Do exception handling
		            }
				}
//				System.out.println("Getting Page.class...");
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		public void loadFile(String fileName) {
			System.out.println("Loading file : " + fileName + "...");
		}
		
		
	}
}
