//package ServerSide;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	@SuppressWarnings("resource")
	public static void main(String args[]) throws IOException {
		if(args.length < 1) {
			System.out.println("Run Program with some parameters");
			System.exit(1);
		}
		
		ServerSocket servers = new ServerSocket(8888);
		
		while(true) {
			System.out.println("wait for connection...");
			
			Socket sock = servers.accept();		
			System.out.println("Connected!");
			
//			PrintWriter pr = new PrintWriter(sock.getOutputStream());
//			pr.println(args.length);
//			pr.flush();
			
			BufferedOutputStream outToClient = new BufferedOutputStream(sock.getOutputStream());
			
			if(outToClient != null) {
				File myFile = new File("Page.class");
				byte[] mybytearray = new byte[(int) myFile.length()];
				
				FileInputStream fis = null;

                try {
                    fis = new FileInputStream(myFile);
                } catch (FileNotFoundException ex) {
                    // Do exception handling
                }
                
                BufferedInputStream bis = new BufferedInputStream(fis);
                
                try {
                    bis.read(mybytearray, 0, mybytearray.length);
                    outToClient.write(mybytearray, 0, mybytearray.length);
                    outToClient.flush();
                    outToClient.close();
                    sock.close();
                } catch (IOException ex) {
                    // Do exception handling
                }
			}
		}
	}	

}