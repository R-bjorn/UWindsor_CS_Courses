package Main;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;


public class Main {
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
//				Making JFrame
				JFrame frame = new JFrame("COMP2800 - Assignment 3");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new BorderLayout());
				
//				Adding MainPanel which has everything we need for this assignment
				MainPanel panel = new MainPanel();
				// toolbar to the top
				frame.add(panel.toolBar, BorderLayout.NORTH);
				// scrollpanel to the center
				frame.add(new JScrollPane(panel), BorderLayout.CENTER);
				// setting default button so user can instantly draw 
				frame.getRootPane().setDefaultButton(panel.toolBar.defaultButton);
				
//				Making frame visible
				frame.pack();
				frame.setLocationByPlatform(true);
				frame.setVisible(true);		
				
				JLabel infoLabel = new JLabel("1. create nodes | 2.  Click on two or more nodes to create a edge | 3. Delete a node");
                infoLabel.setBackground(Color.WHITE);
                frame.add(infoLabel, BorderLayout.SOUTH);
			}
		});
	}
	
	@SuppressWarnings("serial")
	public class MainPanel extends JComponent
	{
//		Toolbar
	    private ToolBar toolBar = new ToolBar();
	    private final int nodeRad = 10;
	    private Shape shape = Shape.Circular;
	    
//	    for Manipulating nodes/panel
	    private Point mousePoint = new Point(1000 / 2, 600 / 2);
	    private Rectangle mouseSelected = new Rectangle(); 
	    private boolean selectedNode; 
	    
//	    To remember all the nodes
	    private List<Node> nodes = new ArrayList<Node>();
	    private List<Node> selected = new ArrayList<Node>();
	    private List<Edge> edges = new ArrayList<Edge>();
	    
	    // constructor
	    public MainPanel() {
	    	this.setOpaque(true);
	    	this.addMouseListener(new MouseSensor());
	        this.addMouseMotionListener(new MouseMotionListener());
	    }
	    
	    @Override
	    public Dimension getPreferredSize()
	    { 
	        return new Dimension(1000, 600);
	    }

	    private class MouseSensor extends MouseAdapter {


	        @Override
	        public void mouseReleased(MouseEvent e) {
	            selectedNode = false;
	            mouseSelected.setBounds(0, 0, 0, 0); // draws a rectangel when the user tries to select everything in the graph
	            if (e.isPopupTrigger()) {
	                showPopup(e);
	            }
	            e.getComponent().repaint();
	        }

	        @Override
	        public void mousePressed(MouseEvent e) {
	            mousePoint = e.getPoint();
	            if (e.isShiftDown()) // checks if multiple nodes are selected
	            {
	                Node.selectToggle(nodes, mousePoint);
	            } else if (e.isPopupTrigger()) {
	                Node.selectOne(nodes, mousePoint);
	                showPopup(e);
	            } else if (Node.selectOne(nodes, mousePoint)) {
	                selectedNode = false;
	            } else {
	                Node.selectNone(nodes);
	                selectedNode = true;
	            }
	            e.getComponent().repaint();
	        }

	        private void showPopup(MouseEvent e) // when right click is clicked anywherree on the screen
	        { // shows content sensitive to the screeen
	            toolBar.popMenu.show(e.getComponent(), e.getX(), e.getY());
	        }
	    }
	    
	    private class MouseMotionListener extends MouseMotionAdapter
	    {

	        Point delta = new Point(); // to move one or moree nodes to a new location

	        @Override
	        public void mouseDragged(MouseEvent e) {
	            if (selectedNode) {
	                mouseSelected.setBounds(
	                    Math.min(mousePoint.x, e.getX()),
	                    Math.min(mousePoint.y, e.getY()),
	                    Math.abs(mousePoint.x - e.getX()),
	                    Math.abs(mousePoint.y - e.getY()));
	                Node.selectRect(nodes, mouseSelected);
	            } else //moving nodes to a new location
	            {
	                delta.setLocation(
	                    e.getX() - mousePoint.x,
	                    e.getY() - mousePoint.y);
	                Node.updatePosition(nodes, delta);
	                mousePoint = e.getPoint();
	            }
	            e.getComponent().repaint();
	        }
	    }
	    // for drawing to the panel
	    @Override
	    public void paintComponent(Graphics g)
	    { 
	        g.setColor(Color.GRAY);
	        g.fillRect(0, 0, getWidth(), getHeight());
	        // Adding each node to the panel
	        for (Edge e : edges)
	            e.draw(g);
	        // Adding each nodes to the panel
	        for (Node n : nodes) 
	            n.draw(g);
	        // Adding rectangle around selected nodes
	        if (selectedNode) { 
	            g.setColor(Color.darkGray);
	            g.drawRect(mouseSelected.x, mouseSelected.y,
	                mouseSelected.width, mouseSelected.height);
	        }
	    }
		
	    // function to craete toolbar
	    public class ToolBar extends JToolBar

		{
	    	// Create new node
			private Action createNode = new CreateNewNode("Create Node");
			private JButton defaultButton = new JButton(createNode);
			// Delete node
	        private Action deleteNode = new DeleteExistingNode("Delete Node");
	        private JRadioButton delNode = new JRadioButton(deleteNode);
	        // Made connecting between nodes
	        private Action addEdge = new ConnectTwoNodes("Add Edge");
	        private JRadioButton addEd = new JRadioButton(addEdge);
	        // delete connected nodes
	        private Action deleteEdge = new DeleteExistingNode("Delete Edge");
	        private JRadioButton delEd = new JRadioButton(deleteEdge);
	        private ButtonGroup toolbarGroup = new ButtonGroup();
	        // for pop menu	        
	        private JPopupMenu popMenu = new JPopupMenu();
	        private Action connect = new ConnectTwoNodes("Add Edge");
	        private Action delete = new DeleteExistingNode("Delete");
	        
	        
	        ToolBar()
	        {
	        	//adding to bar 
	            this.setLayout(new FlowLayout(FlowLayout.LEFT));
	            this.setBackground(Color.lightGray);
	            this.add(defaultButton);
	            this.add(delNode);
	            this.add(addEd);
	            this.add(delEd);
	            // making ground of buttons
	            toolbarGroup.add(delNode);
	            toolbarGroup.add(addEd);
	            toolbarGroup.add(delEd);
	            //adding to popup menu
	            popMenu.add(new JMenuItem(createNode));
	            popMenu.add(new JMenuItem(connect));
	            popMenu.add(new JMenuItem(delete));
	        }
		}

	    // for pair(s) of nodes, add an edge
	    private static class Edge
	    {
	        private Node nodeFirst;
	        private Node nodeSecond;

	        public Edge(Node n1, Node n2) {
	            this.nodeFirst = n1;
	            this.nodeSecond = n2;
	        }
	        public void draw(Graphics g)
	        {

	            Point p1 = nodeFirst.getLocation();
	            Point p2 = nodeSecond.getLocation();
	            g.setColor(Color.darkGray);
	            grphicPointer line3 = new grphicPointer(p1.x, p1.y, p2.x, p2.y, Color.RED, 1);
	            line3.draw(g);
	        }
	   }
	    
	    private static final Polygon GRAPHIC_POINTER = new Polygon();

	    static {
	    	GRAPHIC_POINTER.addPoint(0, 0);
	    	GRAPHIC_POINTER.addPoint(-5, -10);
	    	GRAPHIC_POINTER.addPoint(5, -10);
	    }
	    
	    public static class grphicPointer {


	          private final int x1;
	          private final int y1;
	          private final int x2;
	          private final int y2;
	          private final Color color;
	          private final int thickness;

	          public grphicPointer(int x, int y, int x2, int y2, Color color, int thickness) {
	              super();
	              this.x1 = x;
	              this.y1 = y;
	              this.x2 = x2 -10;
	              this.y2 = y2 -10;
	              this.color = color;
	              this.thickness = thickness;
	          }

	          public void draw(Graphics g) {
	              Graphics2D g2 = (Graphics2D) g;

	              // calculate the angle of the sate
	              double slope = Math.atan2(y2 - y1, x2 - x1);

	              g2.setColor(color);
	              g2.setStroke(new BasicStroke(thickness));
	              g2.drawLine(x1, y1, (int) (x2 - 10 * Math.cos(slope)), (int) (y2 - 10 * Math.sin(slope)));

	              AffineTransform t1 = g2.getTransform();
	              AffineTransform t2 = (AffineTransform) t1.clone();
	              t2.translate(x2, y2);
	              t2.rotate(slope - Math.PI / 2);
	              g2.setTransform(t2);
	              g2.fill(GRAPHIC_POINTER);
	              g2.setTransform(t1);
	          }
	      }
	      
	    private static class Node {

	          private Point p;
	          private int r;
	          private Color color;
	          private Shape shape;
	          private boolean selected = false;
	          private Rectangle b = new Rectangle();

	          // create new node
	          public Node(Point p, int r, Color color, Shape shape) {
	              this.p = p;
	              this.r = r;
	              this.color = color;
	              this.shape = shape;
	              setBoundary(b);
	          }

	          // based on the node size calculate its rectangle boundary
	          private void setBoundary(Rectangle b) {
	              b.setBounds(p.x - r, p.y - r, 2 * r, 2 * r);
	          }

	          //draw a nodee
	          public void draw(Graphics g) {
	              g.setColor(this.color);
	              if (this.shape == Shape.Circular) {
	                  g.fillOval(b.x, b.y, b.width, b.height);
	              }
	              // draws a rectangle over the selected oval, so the user know that the node is selected
	              if (selected) {
	                  g.setColor(Color.darkGray);
	                  g.drawRect(b.x, b.y, b.width, b.height);
	              }
	          }

	          //get the node location
	          public Point getLocation() {
	              return p;
	          }

	          public boolean contains(Point p) {
	              return b.contains(p);
	          }

	          public boolean isSelected() {
	              return selected;
	          }

	          // mark the selcted nodes as selected
	          public void setSelected(boolean selected) {
	              this.selected = selected;
	          }

	          // check if selected nodes to a list
	          public static void getSelected(List<Node> list, List<Node> selected) {
	              selected.clear();
	              for (Node n : list) {
	                  if (n.isSelected()) {
	                      selected.add(n);
	                  }
	              }
	          }

	          // that no nodes were selected
	          public static void selectNone(List<Node> list) {
	              for (Node n : list) {
	                  n.setSelected(false);
	              }
	          }

	          // only one node was seleected , so can't add a edge
	          public static boolean selectOne(List<Node> list, Point p) {
	              for (Node n : list) {
	                  if (n.contains(p)) {
	                      if (!n.isSelected()) {
	                          Node.selectNone(list);
	                          n.setSelected(true);
	                      }
	                      return true;
	                  }
	              }
	              return false;
	          }

	      // when dragging a node with a rectangle echeck if the a reectangle boundary contains a node or not
	          public static void selectRect(List<Node> list, Rectangle r)
	          {
	              for (Node n : list) {
	                  n.setSelected(r.contains(n.p));
	              }
	          }

	          //  toggle arre the selected node status, check if the clicked point contains a node or not
	          public static void selectToggle(List<Node> list, Point p)
	          {
	              for (Node n : list) {
	                  if (n.contains(p)) {
	                      n.setSelected(!n.isSelected());
	                  }
	              }
	          }
	          
	          // update the position of each nodes when moved
	          public static void updatePosition(List<Node> list, Point d) {
	              for (Node n : list) {
	                  if (n.isSelected()) {
	                      n.p.x += d.x;
	                      n.p.y += d.y;
	                      n.setBoundary(n.b);
	                  }
	              }
	          }
	      }

		private class ConnectTwoNodes extends AbstractAction

	    {
	        public ConnectTwoNodes(String name){
	            super(name);
	        }

	        @Override
	        public void actionPerformed(ActionEvent e){
	            Node.getSelected(nodes, selected);
	            //more than one node is selected
	            if (selected.size() > 1)
	                for (int i = 0; i < selected.size() - 1; ++i)
	                    edges.add(new Edge(selected.get(i), selected.get(i + 1)));
	            repaint();
	        }
	    }

		private class DeleteExistingNode extends AbstractAction {

	        public DeleteExistingNode(String name) {
	            super(name);
	        }

	        @Override
	        public void actionPerformed(ActionEvent e){
	            ListIterator<Node> iter = nodes.listIterator();
	            while (iter.hasNext()) {
	                Node n = iter.next();
	                if (n.isSelected())
	                {
	                    deleteEdges(n);
	                    iter.remove();
	                }
	            }
	            repaint();
	        }

	        private void deleteEdges(Node n) {
	            ListIterator<Edge> iter = edges.listIterator();
	            while (iter.hasNext())
	                if (iter.next().nodeFirst == n || iter.next().nodeSecond == n)
	                    iter.remove();
	        }
	    }

		private class CreateNewNode extends AbstractAction {

	        public CreateNewNode(String name) {
	            super(name);
	        }

	        public void actionPerformed(ActionEvent e) {
	            Node.selectNone(nodes);
	            nodes.add(new Node(mousePoint.getLocation(), nodeRad, Color.BLUE, shape));
	            repaint();
	        }
	    }
	   
	}
	
	public enum Shape {
        Circular
    }
}










































