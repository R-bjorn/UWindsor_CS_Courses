	package Main;
	
	import java.awt.*;
	import java.awt.event.*;
	import java.awt.geom.AffineTransform;
	import java.io.*;
	import javax.swing.*;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.ListIterator;
	
	@SuppressWarnings("serial")
	public class Main implements Serializable{
		// Global Variables
		private UndirectedGraph graph = new UndirectedGraph();
		
		public static void main(String args[]){
			new Main();
		}
		public Main(){
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					// main
					JFrame frame = new JFrame("Assignment 5");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setLayout(new BorderLayout());
					frame.setSize(1300,600);
					frame.setVisible(true);
					frame.add(graph.getToolBar(), BorderLayout.NORTH);
					frame.add(graph, BorderLayout.CENTER);
					frame.add(new JButton("Create Nodes | Select More than Two nodes to connect then using Connect | Delete Nodes | Save/Open files") {{setBackground(Color.YELLOW);setForeground(Color.darkGray);}}, BorderLayout.SOUTH);
				}
			});
		}
		private class UndirectedGraph extends JComponent{	
			
			private Toolbar toolBar = new Toolbar();
			private boolean selected;
			private Point mouseClickPoint = new Point(650, 300);
			private Rectangle mouseFrame = new Rectangle();
			private ArrayList<Node> userNodes = new ArrayList<Node>();
			private ArrayList<Node> selectedNodes = new ArrayList<Node>();
			private ArrayList<ConnectedNodes> connectedNodes = new ArrayList<ConnectedNodes>();
			
			public UndirectedGraph() {
				setSize(1300, 600);
				setOpaque(true);
				addMouseListener(new MouseListener());
				addMouseMotionListener(new MouseDragged());
			}
			
			// Mouse Listeners 
			private class MouseListener extends MouseAdapter{
				public void mouseReleased(MouseEvent e) {
					selected = false;
					if(e.isPopupTrigger())
						toolBar.popupMenu.show(e.getComponent(), e.getX(), e.getY());
					e.getComponent().repaint();
				}
				public void mousePressed(MouseEvent e) {
					mouseClickPoint = e.getPoint();
					// you can click multiple nodes by holding shift down
					if(e.isShiftDown()) {
						Node.toggleSelectNode(userNodes,mouseClickPoint);
					}
					// if user clicks on the node, show popup and select node
					else if(e.isPopupTrigger()) {
						Node.selectSingleNode(userNodes, mouseClickPoint);
						toolBar.popupMenu.show(e.getComponent(), e.getX(), e.getY());
					}
					else if(Node.selectSingleNode(userNodes, mouseClickPoint)) {
						selected = false;
					}
					else {
						Node.removeAllSelectedNodes(userNodes);
						selected = true;
					}
					e.getComponent().repaint();
				}
			}
			private class MouseDragged extends MouseMotionAdapter{
				public void mouseDragged(MouseEvent e) {
					if(selected) {
						mouseFrame.setBounds(
								Math.min(mouseClickPoint.x, e.getX()),
			                    Math.min(mouseClickPoint.y, e.getY()),
			                    Math.abs(mouseClickPoint.x - e.getX()),
			                    Math.abs(mouseClickPoint.y - e.getY())
								);
						Node.highlightNodeFrame(userNodes, mouseFrame);
					}
					else {
						Node.moveNode(userNodes, new Point(e.getX() - mouseClickPoint.x, e.getY() - mouseClickPoint.y));
						mouseClickPoint = e.getPoint();
					}
					e.getComponent().repaint();
				}
			}
			// Getter
			public JToolBar getToolBar() {
				return toolBar;
			}
			// Paint
			public void paintComponent(Graphics g) {
				g.setColor(Color.decode("#D3D3D3"));
				g.fillRect(0,0,getWidth(), getHeight());
				for(ConnectedNodes e : connectedNodes)
					e.draw(g);
				for(Node n : userNodes)
					n.draw(g);
				if(selected) {
					g.setColor(Color.black);
					g.drawRect(mouseFrame.x, mouseFrame.y, mouseFrame.width, mouseFrame.height);
				}
					
			}
			//Tool bar
			private class Toolbar extends JToolBar{
				private Action createNode = new CreateNewNode("Create Node");
				private Action cleanGraph = new CleanGraph("Clean Graph");
				private Action deleteNode = new DeleteNode("Delete");
				private Action connectNodes = new ConnectNodes("Connect Nodes");
				private Action saveFile = new SaveToFile("Save");
				private Action openFile = new OpenFromFile("Open Project");
				
				private JPopupMenu popupMenu = new JPopupMenu();
				
				public Toolbar() {
					setLayout(new FlowLayout(FlowLayout.LEFT));
					setBackground(Color.CYAN);
					
					add(new JButton(createNode));
					add(new JButton(cleanGraph));
					add(new JButton(deleteNode));
					add(new JButton(connectNodes));
					add(new JButton(saveFile));
					add(new JButton(openFile));
					
					popupMenu.add(new JButton(createNode));
					popupMenu.add(new JButton(deleteNode));
					popupMenu.add(new JButton(connectNodes));
				}
			}
			// ActionListeners for each button
			private class CreateNewNode extends AbstractAction{
				public CreateNewNode(String title) {
					super(title);
				}
	
				@Override
				public void actionPerformed(ActionEvent e) {
					Node.removeAllSelectedNodes(userNodes);
					Node newNode = new Node(mouseClickPoint, 10, Color.black);
					newNode.setSelected(true);
					userNodes.add(newNode);
					repaint();
					
				}
			}
			private class CleanGraph extends AbstractAction{
	
				public CleanGraph(String title) {
					super(title);
				}
				@Override
				public void actionPerformed(ActionEvent e) {
					userNodes.clear();
					connectedNodes.clear();
					repaint();				
				}
				
			}
			private class DeleteNode extends AbstractAction{
	
				public DeleteNode(String title) {
					super(title);
				}
				@Override
				public void actionPerformed(ActionEvent e) {
					ListIterator<Node> i = userNodes.listIterator();
					ListIterator<ConnectedNodes> j = connectedNodes.listIterator();
					while(i.hasNext()) {
						Node currentNode = i.next();
						if(currentNode.isSelected()) {
							// delete the whole edge if connected to other nodes
							while(j.hasNext()) {
								ConnectedNodes currentEdge = j.next();
								if(currentEdge.getStartNode().equals(currentNode) || currentEdge.getEndNode().equals(currentNode)) {
									j.remove();
								}
							}
							i.remove();
						}
					}
					repaint();
				}
				
			}
			private class ConnectNodes extends AbstractAction{
	
				public ConnectNodes(String title) {
					super(title);
				}
				@Override
				public void actionPerformed(ActionEvent e) {
					Node.getSelectedNodes(userNodes, selectedNodes);
					// more than one node selected to connect
					if(selectedNodes.size() > 1) {
						Node firstNode = null;
						for(Node secondNode : selectedNodes) {
							if(firstNode != null) {
								connectedNodes.add(new ConnectedNodes(firstNode, secondNode));
								firstNode = secondNode;
							}
							firstNode = secondNode;
						}
					}
					repaint();
				}
			}
			private class SaveToFile extends AbstractAction implements Serializable{
	
				public SaveToFile(String title) {
					super(title);
				}
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						String fileName = JOptionPane.showInputDialog("File name ( add extension '.ser')");
						System.out.println(fileName);
						FileOutputStream fos = new FileOutputStream(fileName);
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						
						oos.writeObject(connectedNodes);
						oos.writeObject(userNodes);
						
						oos.close();
						fos.close();
						JOptionPane.showMessageDialog(new JPanel(), "File saves as " + fileName);
					}
					catch(IOException error){
						JOptionPane.showMessageDialog(new JPanel(), "Saving File : Error - " + error);
					}
					
					int reply = JOptionPane.showConfirmDialog(null, "Do you want to create new file", "New File", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
					    userNodes.clear();
					    connectedNodes.clear();
					    graph.repaint();
					} 				
				}
				
			}
			private class OpenFromFile extends AbstractAction implements Serializable{
	
				public OpenFromFile(String title) {
					super(title);
				}
				@SuppressWarnings("unchecked")
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						userNodes.clear();
						connectedNodes.clear();
						graph.repaint();
						
						String fileName = JOptionPane.showInputDialog("File name ( add extension '.ser')");
						ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
						
						connectedNodes = (ArrayList<ConnectedNodes>) ois.readObject();
						userNodes = (ArrayList<Node>) ois.readObject();
						repaint();
						
						ois.close();
						JOptionPane.showMessageDialog(new JPanel(), "Opening file " + fileName + " successful!");
					}
					catch(Exception error){
						JOptionPane.showMessageDialog(new JPanel(), "Opening file - Error : " + error);
					}
				}
				
			}
	
		}
		
		// Single Node
		private class Node implements Serializable{
			
			// Variables
			private Point nodePoint;
			private int nodeRadius;
			private Color nodeColor;
			private boolean isSelected;
			private Rectangle nodeFrame = new Rectangle();
			
			// Constructor
			public Node(Point nodePoint, int nodeRadius, Color nodeColor) {
				super();
				this.nodePoint = nodePoint;
				this.nodeRadius = nodeRadius;
				this.nodeColor = nodeColor;
				nodeFrame.setBounds(nodePoint.x - nodeRadius, nodePoint.y - nodeRadius, 2*nodeRadius, 2*nodeRadius);
				
			}
	
			
			public Point getNodePoint() {
				return nodePoint;
			}
			public void setNodePoint(Point newPoint) {
				this.nodePoint = newPoint;
				nodeFrame.setBounds(nodePoint.x - nodeRadius, nodePoint.y - nodeRadius, 2*nodeRadius, 2*nodeRadius);
			}
			public boolean isSelected() {
				return isSelected;
			}
			public void setSelected(boolean isSelected) {
				this.isSelected = isSelected;
			}
			public boolean nodeFrameContainsPoint(Point point) {
				return nodeFrame.contains(point);
			}
			public static void getSelectedNodes(List<Node> allNodes, List<Node> selectedNodes) {
				selectedNodes.clear();
				for(Node node : allNodes)
					if(node.isSelected())
						selectedNodes.add(node);
				
			}
			public static void removeAllSelectedNodes(List<Node> allNodes) {
				for(Node node : allNodes)
					node.setSelected(false);
			}
			public static boolean selectSingleNode(List<Node> allNodes, Point point) {
				for(Node node : allNodes) {
					if(node.nodeFrameContainsPoint(point)) {
						if(!node.isSelected()) {
							removeAllSelectedNodes(allNodes);
							node.setSelected(true);	
							return true;
						}
					}
				}	
				return false;
			}
			public static void highlightNodeFrame(List<Node> allNodes, Rectangle rect) {
				for(Node node:allNodes)
					node.setSelected(rect.contains(node.getNodePoint()));
			}
			public static void toggleSelectNode(List<Node> allNodes, Point p) {
				for(Node node:allNodes)
					if(node.nodeFrameContainsPoint(p))
						node.setSelected(!node.isSelected());
						
			}
			public static void moveNode(List<Node> allNodes, Point newLocation) {
				for(Node node : allNodes)
					if(node.isSelected()) {
						Point nodePoint = node.getNodePoint();
						Point newPoint = new Point(nodePoint.x + newLocation.x, nodePoint.y + newLocation.y);
						node.setNodePoint(newPoint);
					}
			}
			public void draw(Graphics g) {
				g.setColor(this.nodeColor);
				g.fillOval(this.nodeFrame.x, this.nodeFrame.y, this.nodeFrame.width, this.nodeFrame.height);
				
				if(Node.this.isSelected()) {
					g.setColor(Color.black);
					g.drawRect(this.nodeFrame.x, this.nodeFrame.y, this.nodeFrame.width, this.nodeFrame.height);
				}
					
			}
		}
		// Arrow between Nodes
		private class NodeConnectingArrow{
			private int startX, startY, endX, endY;
	
			public NodeConnectingArrow(int startX, int startY, int endX, int endY) {
				super();
				this.startX = startX;
				this.startY = startY;
				this.endX = endX - 10;
				this.endY = endY - 10;
			}
			
			public void draw(Graphics g) {
				Graphics2D g2d = (Graphics2D) g.create();
				
				double angle = Math.atan2(endY - startY, endX - startX);
				g2d.setColor(Color.blue);
				g2d.setStroke(new BasicStroke(2));
				g2d.drawLine(startX, startY, (int) (endX - 10*Math.cos(angle)), (int) (endY - 10 * Math.sin(angle)));
				
				AffineTransform at1 = g2d.getTransform();
				AffineTransform at2 = (AffineTransform) at1.clone();
				at2.translate(endX, endY);
				at2.rotate(angle - Math.PI/2);
				
				g2d.setTransform(at2);
				
				Polygon arrowHead = new Polygon();
				arrowHead.addPoint(0,0);
				arrowHead.addPoint(-5, -10);
				arrowHead.addPoint(5,-10);
				
				g2d.fill(arrowHead);
				g2d.setTransform(at1);
			}
			
		}
		// Collection of Nodes
		private class ConnectedNodes implements Serializable{
			public Node getStartNode() {
				return node1;
			}
			public Node getEndNode() {
				return node2;
			}
			Node node1, node2;
			
			public ConnectedNodes(Node a, Node b) {
				this.node1 = a;
				this.node2 = b;
			}
			public void draw(Graphics g) {
				Point node1Point = node1.getNodePoint();
				Point node2Point = node2.getNodePoint();
				
				g.setColor(Color.darkGray);
				NodeConnectingArrow arrow = new NodeConnectingArrow(node1Point.x, node1Point.y, node2Point.x, node2Point.y);
				arrow.draw(g);
			}	
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
