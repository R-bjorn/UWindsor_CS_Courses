import java.awt.EventQueue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Main{
//	Main Frame
	JFrame frame;
//	Image icons we need to use in this code
	private ImageIcon turtleIcon;
	private ImageIcon hareIcon;
	private ImageIcon startIcon;
	private ImageIcon finishIcon;
	private ImageIcon celebration;
//	Speed for Hare and Turtle
	private float turtleSpeed = 100;
	private float hareSpeed = 200;
	private int hareSleep = 4000;
	private int hareSleepCount = 0;
//	timer for starting the race
	private Timer turtleTimer;
	private Timer hareTimer;
//	for drawing
	private boolean canDraw = false;
	private ArrayList<Point> hareSleepPoints = new ArrayList<Point>();
//	For race start
	private boolean raceStart = false;
//	Threads
	Thread hare, turtle;
//	points defined here
	Point startPoint , finishPoint;
	movingPoint harePoint, turtlePoint;
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				frame = new TurtleAndHare();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(1500, 800);
				frame.setVisible(true);
			}
		});
	}
	
	@SuppressWarnings("serial")
	public class TurtleAndHare extends JFrame{

//		Layout for toolbar
		GridBagLayout panelLayout = new GridBagLayout();
//		Race window
		private MyFrame windowFrame;

		public TurtleAndHare() {
			super("Race between Hare and Turtle!");
			setLayout(new BorderLayout());
			setResizable(false);
			
			
			// Adding Input Bar at the Top
			add(makeNorthBar(), BorderLayout.NORTH);
			// Adding Window Panel for race
			windowFrame = new MyFrame();
			add(windowFrame,BorderLayout.CENTER);
			// Adding Start button to the bottom
			add(startButton(), BorderLayout.SOUTH);
			
			turtleTimer = new Timer((int) turtleSpeed, new ActionListener() {
				public void actionPerformed(ActionEvent event) {
	            	repaint();
	            }
			});
			
			hareTimer = new Timer((int) hareSpeed, new ActionListener() {
				public void actionPerformed(ActionEvent event) {
	            	repaint();
	            }
			});
		}
		
		public JPanel makeNorthBar() {
			GridBagConstraints panelGrid = new GridBagConstraints();
			panelGrid.insets = new Insets(10,20,20,20);
			
			JPanel panel = new JPanel();
			panel.setLayout(panelLayout);
			panel.setBackground(Color.BLACK);
					
//			Adding "Speed of Turtle :"
			panelGrid.fill = GridBagConstraints.HORIZONTAL;
			panelGrid.weightx = 0.5;
			panelGrid.gridx = 0;
			panelGrid.gridy = 0;
			JLabel label = new JLabel("Enter the speed of Turtle : ", JLabel.CENTER);
			label.setOpaque(true);
			label.setBackground(Color.WHITE);	
			JTextField turtleInput = new JTextField("");
			turtleInput.setBackground(Color.GRAY);
			turtleInput.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					turtleSpeed = Float.parseFloat(turtleInput.getText());
					System.out.println("Turtle Speed : "+ turtleSpeed);
					turtleTimer.setDelay((int) turtleSpeed);
					turtlePoint.setDelay(turtleSpeed);
				}
			});
			JPanel turtleSpeed = new JPanel(new BorderLayout());
			turtleSpeed.add(label, BorderLayout.WEST);
			turtleSpeed.add(turtleInput, BorderLayout.CENTER);
			panel.add(turtleSpeed, panelGrid );

			
//			Adding "Speed of Hare"
			panelGrid.fill = GridBagConstraints.HORIZONTAL;
			panelGrid.weightx = 0.5;
			panelGrid.gridx = 1;
			panelGrid.gridy = 0;
			label = new JLabel("Enter the speed of Hare : ", JLabel.CENTER);
			label.setOpaque(true);
			label.setBackground(Color.WHITE);	
			JTextField hareInput = new JTextField("");
			hareInput.setBackground(Color.GRAY);
			hareInput.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					hareSpeed = Float.parseFloat(hareInput.getText());
					System.out.println("Hare Speed : "+ hareSpeed);
					hareTimer.setDelay((int)hareSpeed);
					harePoint.setDelay(hareSpeed);
				}
			});
			JPanel hareSpeed = new JPanel(new BorderLayout());
			hareSpeed.add(label, BorderLayout.WEST);
			hareSpeed.add(hareInput, BorderLayout.CENTER);
			panel.add(hareSpeed, panelGrid );
			
//			Adding "Sleep Time of Hare"
			panelGrid.fill = GridBagConstraints.HORIZONTAL;
			panelGrid.weightx = 0.5;
			panelGrid.gridx = 0;
			panelGrid.gridy = 1;
			label = new JLabel("Enter the sleep time of Hare (in milliseconds) : ", JLabel.CENTER);
			label.setOpaque(true);
			label.setBackground(Color.WHITE);	
			JTextField hareSleepInput = new JTextField("");
			hareSleepInput.setBackground(Color.GRAY);
			hareSleepInput.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					hareSleep = Integer.parseInt(hareSleepInput.getText());
					System.out.println(hareSleep);
				}
			});
			JPanel hareSleepSpeed = new JPanel(new BorderLayout());
			hareSleepSpeed.add(label, BorderLayout.WEST);
			hareSleepSpeed.add(hareSleepInput, BorderLayout.CENTER);
			panel.add(hareSleepSpeed, panelGrid );
			
//			Adding "Sleep Time of Hare"
			panelGrid.fill = GridBagConstraints.HORIZONTAL;
			panelGrid.weightx = 0.5;
			panelGrid.gridx = 1;
			panelGrid.gridy = 1;
			label = new JLabel("Enter how many times hare sleeps during the race (Maximum 2 times | Minimum 0 times): ", JLabel.CENTER);
			label.setOpaque(true);
			label.setBackground(Color.WHITE);	
			JTextField hareSleepCountInput = new JTextField("0");
			hareSleepCountInput.setBackground(Color.GRAY);
			hareSleepCountInput.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					hareSleepCount = Integer.parseInt(hareSleepCountInput.getText());
					if(hareSleepCount > 0) {
						startPointing();
					}
				}
			});
			JPanel hareSleepCountPanel = new JPanel(new BorderLayout());
			hareSleepCountPanel.add(label, BorderLayout.WEST);
			hareSleepCountPanel.add(hareSleepCountInput, BorderLayout.CENTER);
			panel.add(hareSleepCountPanel, panelGrid );

			
			
			
			return panel;			
		}
		
		public static JPanel changeNorthBar() {
			JPanel panel = new JPanel();
			panel.setBackground(Color.BLACK);
			panel.setPreferredSize(new Dimension(0,100));	
			return panel;
		}
		
		public JPanel startButton() {
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			
			JButton startButton = new JButton("Start the Race!!!");
			startButton.setBounds(0,0,200,100);
			startButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					turtleTimer.start();
					hareTimer.start();
					
					raceStart = true;
					hare.start();
					turtle.start();
					startButton.setEnabled(false);		// enable it again when someone wins
				}
			});
			
			panel.add(startButton, BorderLayout.CENTER);			
			return panel;
		}

		public void startPointing() {
			
			String infoMsg = "You selected that you want hare to sleep " + hareSleepCount + " times! Now you can chose which points you want hare to sleep on its way! Be careful to point in only hare's way otherwise it will not sleep."; 
			
			JOptionPane.showMessageDialog(null, infoMsg, "Hare Sleep Information", JOptionPane.INFORMATION_MESSAGE);
			
			canDraw = true;
		}
	}
	
	@SuppressWarnings("serial")
	public class MyFrame extends Container implements Runnable{
		

		ImageIcon circ;
		private boolean willDraw = false;
//		if anyone won
		private boolean hareWon = false, turtleWon = false; 
//		sleeping hare
		private boolean isWalking = true; 
//		timing
		private long time;
		
		public MyFrame() {
			// Initializing Icons
			startIcon = new ImageIcon(getClass().getResource("Images/start.png"));
			finishIcon = new ImageIcon(getClass().getResource("Images/finish.jpg"));
			Image resizeImage = finishIcon.getImage().getScaledInstance(finishIcon.getIconWidth(), finishIcon.getIconHeight() * 6,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
			finishIcon = new ImageIcon(resizeImage);
			
			hareIcon = new ImageIcon(getClass().getResource("Images/hare.gif"));
			turtleIcon = new ImageIcon(getClass().getResource("Images/turtle.gif"));
			circ = new ImageIcon(getClass().getResource("Images/round.png"));
			
			// Setting Points
			startPoint = new Point(50 , 50);
			finishPoint = new Point(1400,50);
			turtlePoint = new movingPoint((int)startPoint.getX(),200, turtleSpeed);
			harePoint = new movingPoint((int) startPoint.getX(), 400, hareSpeed);
			
			// Threads
			hare = new Thread(harePoint);
			turtle = new Thread(turtlePoint);
		}
		
		public void paint(Graphics g) {			
			g.setColor(Color.GRAY);
			g.fillRect(0,0,getWidth(), getHeight());
			
			// Printing icons on container
			startIcon.paintIcon(MyFrame.this, g, (int)startPoint.getX(), (int)startPoint.getY());		
			finishIcon.paintIcon(MyFrame.this, g, (int)finishPoint.getX(), (int)finishPoint.getY());			
			turtleIcon.paintIcon(MyFrame.this, g, (int)turtlePoint.getX(), (int)turtlePoint.getY());			
			hareIcon.paintIcon(MyFrame.this, g, (int)harePoint.getX(), (int)harePoint.getY());
			
			// if race starts, 
			if(raceStart) {
				CheckForHareSleepingPoints();
				CheckForWin();
			}
			else if(turtleWon || hareWon) {
				BorderLayout layout = (BorderLayout)frame.getContentPane().getLayout();
				frame.getContentPane().remove(layout.getLayoutComponent(BorderLayout.NORTH));
				frame.getContentPane().remove(layout.getLayoutComponent(BorderLayout.CENTER));
//				frame.add(TurtleAndHare.changeNorthBar(), BorderLayout.NORTH);				
				frame.add(new Celebration(), BorderLayout.CENTER);
				frame.getContentPane().invalidate();
				frame.getContentPane().validate();
				
//				celebration.paintIcon(MyFrame.this, g, 0, 0);	
				
				String won = "";
				if(hareWon && turtleWon)
					JOptionPane.showMessageDialog(null, "IT'S A TIE!!!!!!!!!!!!!!!!!!!!!!!!!!!", "Who won the race?", JOptionPane.INFORMATION_MESSAGE);
				if(hareWon)
					won = "Hare";
				if(turtleWon)
					won = "Turtle";
				
				String winningMessage = "Finally, This is the time we've been waiting for ladies and gentlemen!\nOur race winner is!!!!!!!!!!!!!!!      " + won+ "!!!!!!!!!!!\n HOOYAAAAY! Congratulations!!!!"; 
				
				JOptionPane.showMessageDialog(null, winningMessage, "Who won the race?", JOptionPane.INFORMATION_MESSAGE);
				
				System.exit(0);
			}
					
			if(canDraw)
				addListener(g);
			
			if(willDraw)
				for(Point p : hareSleepPoints) 
					circ.paintIcon(MyFrame.this, g, (int)p.getX(), (int)p.getY());
		}

		@SuppressWarnings("removal")
		public void CheckForHareSleepingPoints() {			
			if(isWalking) {
				time = System.currentTimeMillis();
			}
			
			Rectangle rect = new Rectangle((int)harePoint.getX(), (int)harePoint.getY(), hareIcon.getIconWidth(), hareIcon.getIconHeight());
			
			Iterator<Point> iter = hareSleepPoints.iterator();
			
			while(iter.hasNext()) {
				Point p = iter.next();
				if(rect.contains(p)) {
					iter.remove();
					hareIcon = new ImageIcon(getClass().getResource("Images/hareSleeping.png"));
					hare.suspend();
					isWalking = false;
				}		
			}
			if(!isWalking) {
				long endTime = System.currentTimeMillis();
				float sec = (endTime - time) / 1000F;
				if(sec > hareSleep/1000) {
					hareIcon = new ImageIcon(getClass().getResource("Images/hare.gif"));
					hare.resume();
					isWalking = true;
				}
					
			}
		}
		
		public void CheckForWin() {
			
			// If hare wins
			if(harePoint.getX() > finishPoint.getX()) {
				hareTimer.stop();
				hareWon = true;
				harePoint.setLocation(harePoint.getX() - (hareSpeed*2/10), harePoint.getY());
				hareIcon = new ImageIcon(getClass().getResource("Images/harePhoto.png"));
			}
			// if turtle wins
			if(turtlePoint.getX() > finishPoint.getX()) {
				turtleTimer.stop();
				turtleWon = true;
				turtlePoint.setLocation(turtlePoint.getX() - (turtleSpeed/100), turtlePoint.getY());
				turtleIcon = new ImageIcon(getClass().getResource("Images/turtlePhoto.png"));
			}
			if(hareWon || turtleWon)
				raceStart = false;
		}
		
		public void addListener(Graphics g) {
			canDraw = false;
			MyFrame.this.addMouseListener(new MouseAdapter()
	        {
				public void mouseClicked(MouseEvent e){
					int x = e.getX(), y = e.getY();
					if(hareSleepCount-- > 0) 
						hareSleepPoints.add(new Point(x,y));
					willDraw = true;
				}
				public void mousePressed(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}				
				public void mouseEntered(MouseEvent e){}        
                public void mouseExited(MouseEvent e){}
			});
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				this.repaint();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e)
		        {	        
					e.printStackTrace();
				}
			}
		}
	}
	
	public class movingPoint implements Runnable{
		Point point;
		float delay;
		public movingPoint(int x, int y, float delay) {
			point = new Point(x, y);
			this.delay = delay;
		}
		public void setLocation(double d, double y) {
			point.setLocation(d,y);			
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				point.setLocation(point.getX() + (delay/50), point.getY());				
				
				try{
					Thread.sleep(100);
				}catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		}
		public void setDelay(float delay) {
			this.delay = delay;
		}
		public double getX() {
			return point.getX();
		}
		public double getY() {
			return point.getY();
		}

	}

	@SuppressWarnings("serial")
	public class Celebration extends Container{
		public Celebration() {
			celebration = new ImageIcon(getClass().getResource("Images/celebration.gif"));
		}
		
		public void paint(Graphics g) {
			g.setColor(Color.BLACK);
			g.fillRect(0,0,getWidth(), getHeight());
			
			celebration.paintIcon(Celebration.this, g, 0, 0);
		}
	}


}
