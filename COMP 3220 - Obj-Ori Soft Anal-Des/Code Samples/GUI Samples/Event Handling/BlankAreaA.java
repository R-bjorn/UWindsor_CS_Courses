import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;

public class BlankAreaA extends JLabel {
    Dimension minSize = new Dimension(100, 50);

    public BlankAreaA() {
    	this.setBackground(Color.YELLOW);
        setOpaque(true);
    }

    public Dimension getMinimumSize() {
        return minSize;
    }

    public Dimension getPreferredSize() {
        return minSize;
    }
}