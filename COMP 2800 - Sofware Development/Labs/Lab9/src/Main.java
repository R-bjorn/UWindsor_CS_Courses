import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.GridLayout;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
	
	public static void main(String args[]) {
		if(args.length < 1) {
			System.exit(0);
		}
		
		BufferedImage originalImage = null;
		BufferedImage rImage = null;
		BufferedImage gImage = null;
		BufferedImage bImage = null;
		int width = 963;
        int height = 640;
		
		try {
            File input_file = new File("Image/"+args[0]);
  
            originalImage = new BufferedImage(
                width, height, BufferedImage.TYPE_INT_ARGB);
            rImage = new BufferedImage(
                    width, height, BufferedImage.TYPE_INT_ARGB);;
            gImage = new BufferedImage(
                    width, height, BufferedImage.TYPE_INT_ARGB);;
            bImage = new BufferedImage(
                    width, height, BufferedImage.TYPE_INT_ARGB);;
  
            // Reading input file
            originalImage = ImageIO.read(input_file);
            rImage = ImageIO.read(input_file);
            gImage = ImageIO.read(input_file);
            bImage = ImageIO.read(input_file);
        }
        catch (IOException e) {
            System.out.println("Error: " + e);
        }
		
		for (int x = 0; x < originalImage.getWidth(); x++) {
		    for (int y = 0; y < originalImage.getHeight(); y++) {
		    	Color pixelColor = new Color(originalImage.getRGB(x,y));
		    	rImage.setRGB(x,y,(new Color(0, pixelColor.getGreen(), pixelColor.getBlue())).getRGB());
		    	gImage.setRGB(x,y,(new Color(pixelColor.getRed(), 0, pixelColor.getBlue())).getRGB());
		    	bImage.setRGB(x,y,(new Color(pixelColor.getRed(), pixelColor.getGreen(), 0)).getRGB());
		    }
		}
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(800, 500);
		
		frame.setLayout(new GridLayout(1, 4));
		JLabel oImg = new JLabel(new ImageIcon(originalImage));
		frame.add(oImg);
		JLabel rImg = new JLabel(new ImageIcon(rImage));
		frame.add(rImg);
		JLabel gImg = new JLabel(new ImageIcon(gImage));
		frame.add(gImg);
		JLabel bImg = new JLabel(new ImageIcon(bImage));
		frame.add(bImg);
		
		try {
            // Output file path
			String filename = args[0].split("\\.")[0];
			String extension = args[0].split("\\.")[1];
			System.out.println(filename);
            File rOutput = new File("D:\\Computer Science\\University\\S6\\COMP-2800 Software Development\\Labs\\Lab9\\src\\Image\\"+filename+"-r."+extension);
            File bOutput = new File("D:\\Computer Science\\University\\S6\\COMP-2800 Software Development\\Labs\\Lab9\\src\\Image\\"+filename+"-g."+extension);
            File gOutput = new File("D:\\Computer Science\\University\\S6\\COMP-2800 Software Development\\Labs\\Lab9\\src\\Image\\"+filename+"-b."+extension);
  
            // Writing to file taking type and path as
            ImageIO.write(rImage, extension, rOutput);
            ImageIO.write(gImage, extension, gOutput);
            ImageIO.write(bImage, extension, bOutput);
        }
        catch (IOException e) {
            System.out.println("Error: " + e);
        }
		
	}
}
