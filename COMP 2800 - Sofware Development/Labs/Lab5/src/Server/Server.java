package Server;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
	public static void main(String args[]) throws IOException {
		ServerSocket ss = new ServerSocket(4999);
		Socket s = ss.accept();
		
		System.out.println("Connected!");
		
		PrintWriter pr = new PrintWriter(s.getOutputStream());
		
		Scanner myObj = new Scanner(System.in);
		String clientNumber = myObj.nextLine(); 
		
		pr.println(clientNumber);
		pr.flush();
	}
}
