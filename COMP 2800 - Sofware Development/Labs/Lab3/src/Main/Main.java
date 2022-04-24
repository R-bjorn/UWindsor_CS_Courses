package Main;

import javax.swing.JFrame;
import java.awt.EventQueue;
import Lab3.MainFrame;

public class Main {
	public static void main(String args[]) {
//		Making an instance of the class that makes the frame
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame frame = new MainFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500, 500);
                frame.setVisible(true);
            }
        });
	}
}
