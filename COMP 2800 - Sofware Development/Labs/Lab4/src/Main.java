import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import java.io.File;
import java.io.IOException;

import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main {
	
	private JFrame frame = new JFrame();
	
	public static void main(String[] args) {
		new Main();
	}
	
	private Main() {
		EventQueue.invokeLater( new Runnable() {
			@Override
			public void run() {
				frame = new MainFrame();
				frame.setSize(1000,800);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
	
	@SuppressWarnings("serial")
	public class MainFrame extends JFrame{
		private boolean userVictory = false;
		private int speed = 1000;
		private Clip preLoadedAudio;
		private Container windowPanel;	
		private Timer timer;
		private Random randomGen;
		private ImageIcon imageIcon;
		private int imageWidth, imageHeight;
		private JSlider imageSpeed;
		
		// Constructor
		private MainFrame() {
			
			
			// Frame properties 
			setTitle("CATCH ME IF YOU CAN!");
			setAlwaysOnTop(true);
			setLayout(new BorderLayout());
						
			// add information label
			InformationLabel();
			// adding content to the panel with a white background
			windowPanel = this.getContentPane();
			// add sliders to change speed
			addSlider();
			
			// randomGenerator for randomly getting image position
			randomGen = new Random();
			// Adding image icon 
			imageIcon = new ImageIcon(getClass().getResource("Images/catchme.jpg"));
			imageWidth = imageIcon.getIconWidth();
			imageHeight = imageIcon.getIconHeight();
			
			//Setting timer
			timer = new Timer(speed, new ActionListener ()
	        {
	            public void actionPerformed(ActionEvent event) {
	            	repaint();
	            }
	        });			
			timer.start();
			StartAudio();
			
		}	

		// information label
		private void InformationLabel() {
			JButton label = new JButton("Capturing the Dancing Image to win the game. The Slider below sets the speed of how fast the image should dance in the window panel. Get creative and enjoy!!!!!!");
			label.setBackground(Color.YELLOW);
			add(label, BorderLayout.NORTH);	
		}
		
		// Audio Manager for normal audio and victory audio
		private void StartAudio() {
			// to real audio file
			try {
				// getting the file source
				File audioFile = new File("src/Audio/assignmentAudio.wav");
				// Obtains an audio input stream from the provided File.
				AudioInputStream audioManager = AudioSystem.getAudioInputStream(audioFile);
				// getting a clip that can be used for playing back an audio stream.
				preLoadedAudio = AudioSystem.getClip();
				preLoadedAudio.open(audioManager);
				preLoadedAudio.start();
			}
			// Catching any error while opening the audio file
			catch(UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
		}
		private void VictoryAudio() {
			userVictory = true;
			try {
				// getting the file source
				File audioFile = new File("src/Audio/assignmentVictory.wav");
				// creating audioManager to manipulate the audio
				AudioInputStream audioManager = AudioSystem.getAudioInputStream(audioFile);
				// loading the audio to Clip
				preLoadedAudio = AudioSystem.getClip();
				preLoadedAudio.open(audioManager);
				preLoadedAudio.start();
			}
			// Catching any error while opening the audio file
			catch(UnsupportedAudioFileException | IOException | LineUnavailableException ev) {
				ev.printStackTrace();
			}
		}
		
		// adding slider and changing speed through slider
		private void addSlider() {
			imageSpeed = new JSlider(JSlider.HORIZONTAL, 100, 1000, 400);
			imageSpeed.setMajorTickSpacing(100);
			imageSpeed.setMinorTickSpacing(50);
			imageSpeed.setPaintTicks(true);
			imageSpeed.setPaintLabels(true);
			
//			Adding Slider-ChangeListener to change the rotation speed of the star
			imageSpeed.addChangeListener(new ChangeListener() { // anonymous inner class  
	            // handle change in slider value
	            @Override
	            public void stateChanged(ChangeEvent e) {
	            	ChangeSpeed(imageSpeed.getValue());
	            }
	         } 
	      );
			

//			Adding panel to the main Panel at the bottom
			add(imageSpeed, BorderLayout.SOUTH);
		}
		private void ChangeSpeed(int newSpeed) {	
			this.speed = newSpeed;
			timer.setDelay(speed);
		}	
		
		// Painting the panel
		public void paint(Graphics g) {
			g.setColor(Color.GRAY);
			g.fillRect(0,55,1000,670);
			//Getting random points
			int pointX = randomGen.nextInt(800), pointY = randomGen.nextInt(470) + 55;
			// Adding image to the random point as well as its frame so we can check if user clicked on the image
			imageIcon.paintIcon(windowPanel, g, pointX, pointY);
			Rectangle imageFrame = new Rectangle(pointX, pointY, imageWidth, imageHeight);
			
			frame.addMouseListener(new MouseAdapter()
	        {
				public void mouseClicked(MouseEvent e){}
				
				public void mousePressed(MouseEvent e) {
//					System.out.printf("Pressed on %d - %d\n",e.getX(), e.getY());
					// if user hasn't won
					if(!userVictory) {
						if(imageFrame.contains(e.getPoint())) {
							// Stopping the timer 
							timer.stop();
							// Stopping the main Audio
							preLoadedAudio.stop();
							//Changing the audio to victory audio
							VictoryAudio();
							// changing the image on click						
							imageIcon = new ImageIcon(getClass().getResource("Images/victoryImage.jpg"));
							imageIcon.paintIcon(windowPanel, g, pointX, pointY);
							// Repaint the panel
							repaint();						
						}
					}
					// if user is already won
					else if(userVictory) {
						// stopping the victory audio
						preLoadedAudio.stop();
						// starting the main audio
						StartAudio();
						// changing the image
						imageIcon = new ImageIcon(getClass().getResource("Images/catchme.jpg"));
						imageIcon.paintIcon(windowPanel, g, pointX, pointY);
						// Repaint the panel 
						repaint();
						// starting the timer again
						timer.start();
						// Changing the victory to false
						userVictory = false;
					}
				}
				public void mouseReleased(MouseEvent e) {}				
				public void mouseEntered(MouseEvent e){}        
                public void mouseExited(MouseEvent e){}
			});
		}
		
	}
}