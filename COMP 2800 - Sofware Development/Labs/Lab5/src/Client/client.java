package Client;

import java.net.*;
import java.util.Random;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class client{
	static int clientNumber = 0;
	Random randomGen = new Random();
	
	public static void main(String args[]) throws IOException {
		new client();
	}	
	
	public client() throws UnknownHostException, IOException {
		Socket sock = new Socket("localhost", 4999);
		InputStreamReader in = new InputStreamReader(sock.getInputStream());
		BufferedReader bf = new BufferedReader(in); 
		
		String serverMsg = bf.readLine();
		System.out.println("recieved: " +serverMsg);
		clientNumber = Integer.parseInt(serverMsg);
		
		clientFrame frame = new clientFrame();
	}

	public class clientFrame extends JFrame{
		public clientFrame() {
			super("Client");
			setSize(500,500);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
		}
		
		public void paint(Graphics g) {	
			// Drawing ovals at random points
			while(--clientNumber > 0) {
				g.setColor(new Color(randomGen.nextInt(255),randomGen.nextInt(255),randomGen.nextInt(255)));
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.drawOval(randomGen.nextInt(500), randomGen.nextInt(500),5,5);
			}
		}
	}
}


