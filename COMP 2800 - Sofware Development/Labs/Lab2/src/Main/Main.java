/**
 * 
 */
package Main;

import javax.swing.JFrame;

import Lab2.ColorFrame;

/**
 * @author ravid
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		Adding ColorFrame frame adds a panel and a slider 
		ColorFrame frame = new ColorFrame();
//		Manipulating?/Changing frame settings 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setVisible(true);
	}
}
