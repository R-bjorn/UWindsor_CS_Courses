import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Q1 extends JFrame{
	public static final int WINDOWWIDTH = 400;
	public static final int WINDOWHEIGHT = 400;
	public static final int FACEDIAMETER = 200;
	public static final int XFACE = 100;
	public static final int YFACE = 100;
	
	public static final int EYEWIDTH = 20;
	public static final int EYEHEIGHT = 10;
	public static final int XRIGHTEYE = XFACE + 55;
	public static final int YRIGHTEYE = YFACE + 60;
	public static final int XLEFTEYE = XFACE + 130;
	public static final int YLEFTEYE = YFACE + 60;
	
	public static final int MOUTHWIDTH = 100;
	public static final int MOUTHHEIGHT = 50;
	public static final int XMOUTH = XFACE + 50;
	public static final int YMOUTH = YFACE + 100;
	public static final int MOUTHSTART = 180;
	public static final int MOUTHSWEEP = 180;
	
	private boolean isWinking = false;
	
	private class WinkAction implements ActionListener{
		public void actionPerformed(ActionEvent e){
			isWinking = !isWinking;
			repaint();
		}
	}
	
	public static void main(String [] args){
		Q1 drawing = new Q1();
		drawing.setVisible(true);
	}
	
	public Q1(){
		setSize(WINDOWWIDTH, WINDOWHEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Winking Face Application");
		setLayout(new BorderLayout());
		getContentPane().setBackground(Color.WHITE);
		
		JButton winkButton = new JButton("Click to wink");
		winkButton.addActionListener(new WinkAction());
		add(winkButton, BorderLayout.SOUTH);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.drawOval(XFACE, YFACE, FACEDIAMETER, FACEDIAMETER);
		
		g.setColor(Color.BLUE);
		g.fillOval(XRIGHTEYE, YRIGHTEYE, EYEWIDTH, EYEHEIGHT);
		
		if (isWinking){
			g.setColor(Color.BLACK);
			g.drawLine(XLEFTEYE, YLEFTEYE, XLEFTEYE + EYEWIDTH, YLEFTEYE);
		}else{
			g.setColor(Color.BLUE);
			g.fillOval(XLEFTEYE, YLEFTEYE, EYEWIDTH, EYEHEIGHT);
		}

		g.setColor(Color.RED);
		g.drawArc(XMOUTH, YMOUTH, MOUTHWIDTH, MOUTHHEIGHT, MOUTHSTART, MOUTHSWEEP);
	}
}
