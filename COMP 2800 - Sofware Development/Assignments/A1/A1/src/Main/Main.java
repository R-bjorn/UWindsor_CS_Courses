/**
 * 
 */
package Main;

import javax.swing.JFrame;

import HA2.MainFrame;

/**
 * @author ravid
 *
 */
public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		Creating the Main Frame
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		frame.setVisible(true);
	}
}
