import java.awt.*; // import the java.awt package
import javax.swing.*;

public class ShowTicTacToe extends JFrame {
	
	public ShowTicTacToe(){
		super("Tictactoe Board");
		setSize(300, 300);
		setVisible(true);
	}
	
	public static void main(String args[]){
		ShowTicTacToe MyBoard;
		MyBoard = new ShowTicTacToe();
	}
	
	public void paint(Graphics g){
		int red = 255, green = 0, blue = 0;
		Font my_font;
		my_font = new Font( "Serif", Font.BOLD, 18);
		g.setColor(new Color(red, green, blue));
		// One way to set color
		g.drawLine(70, 170, 170, 170);
		g.drawLine(70, 203, 170, 203);
		g.drawLine(103, 137, 103, 237);
		g.drawLine(137, 137, 137, 237);
		g.setColor(new Color(0, 0, 0));
		g.fillRect(50, 117, 140, 5);
		g.fillRect(50, 117, 5, 140);
		g.fillRect(50, 252, 140, 5);
		g.fillRect(185, 117, 5, 140);
		g.setColor(Color.blue);
		// Another way to set color
		g.setFont(my_font);
		g.drawString("My Tic Tac Toe Board", 0, 30);
	}
}