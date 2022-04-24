

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String args[]) throws IOException {
		if(args.length < 1) {
			System.out.println("Run Program with some parameters");
			System.exit(1);
		}
		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(8888);
		while(true) {			
			System.out.println("wait for connection...");
			
			Socket s = ss.accept();		
			System.out.println("Connected!");
			
			PrintWriter pr = new PrintWriter(s.getOutputStream());
			pr.println(args.length);
			
			for(int i = 0 ; i < args.length ; i++) {
//				Sends the final name
				pr.println(args[i]);
				
				File file = new File("../bin/"+args[i]);
				if(!file.canRead()) {
					System.out.println("Cannot read the file");
					System.exit(1);
				}	
				System.out.println("file length: "+file.length());
//				Sends the file length
				pr.println(file.length());
			}
			
			pr.flush();
		}
		
		
		
		
	}
}
