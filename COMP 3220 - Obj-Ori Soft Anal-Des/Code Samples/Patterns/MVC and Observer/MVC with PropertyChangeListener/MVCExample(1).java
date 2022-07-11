package mvcExamplePropertyChangedListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MVCExample extends JPanel {
	private MyModel m = new MyModel(24.7F, "hello");
	private MyViewA a = new MyViewA(m);
	private MyViewB b = new MyViewB(m);
	private MyController c = new MyController(m, a, b);
    
    public static void main(String[] args) {

        //Create and set up the window.
        JFrame frame = new JFrame("MVC Example");
        
        //Create and set up the content pane
        JComponent newContentPane = new MVCExample();
        frame.setContentPane(newContentPane);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        
        MVCExample e = new MVCExample();
    }
    
    public MVCExample() {
    	super();
    	add(a);
    	add(b);
    	
    	JTextField t = new JTextField(100);
    	t.addActionListener(c);
    	add(t);
    }
}