import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Client {
	Socket sock;
	public static void main(String args[]) throws UnknownHostException, IOException {
		new Client();
	}
	
	public Client() throws UnknownHostException, IOException {
		sock = new Socket("127.0.0.1", 8888);
		InputStreamReader in = new InputStreamReader(sock.getInputStream());
		BufferedReader bf = new BufferedReader(in); 
		
		int fileCount = Integer.parseInt(bf.readLine());
		System.out.println("number of files: " + fileCount);
		
		ArrayList<Long> fileLength = new ArrayList<Long>();
		ArrayList<String> fileName = new ArrayList<String>();
		for(int i = 0 ; i < fileCount ; i++) {
			fileName.add(bf.readLine());
			System.out.println("filename: " + fileName.get(i));
			fileLength.add(Long.parseLong(bf.readLine()));
			System.out.println("filelength: " + fileLength.get(i));
			fileName.set(i, fileName.get(i).split("\\.")[0]);
			if(!fileName.get(i).contains("$")) {
				System.out.println("filename without .class: " + fileName.get(i));
				ClientFrame frame = new ClientFrame(fileName.get(i));
				frame.setSize(500,500);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}			
		}

	}
	
	@SuppressWarnings("serial")
	public class ClientFrame extends JFrame {
		String fileName;
		Class<?> c;
		JPanel mainFramePanel;
		
		public ClientFrame(String files) {
			super("Client");
			this.fileName = files;
			addNorthBar();
		}
		
		public void addNorthBar() {
			JPanel panel = new JPanel();
			panel.add(new JLabel("server IP:   "), BorderLayout.WEST);
			JTextField fileInput = new JTextField("");
			fileInput.setColumns(20);
			fileInput.setBackground(Color.WHITE);
			fileInput.setText(sock.getLocalAddress().getHostAddress());
			addClass(fileName);
			panel.add(fileInput, BorderLayout.CENTER);
			add(panel, BorderLayout.NORTH);
		}
		
		public void addClass(String file) {
			try {
				c = Class.forName(file);
				Constructor<?> cons = c.getConstructor();
				Object object = cons.newInstance(new Object[] {});
				mainFramePanel = (JPanel) object;
				this.add(mainFramePanel, BorderLayout.CENTER);
				this.revalidate();
				this.repaint();
				this.setVisible(true);
				this.setSize(800,600);
			}
			catch (ClassNotFoundException | NoSuchMethodException 
					| SecurityException | InstantiationException | IllegalAccessException | 
					IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			} 
		}
	}

}
