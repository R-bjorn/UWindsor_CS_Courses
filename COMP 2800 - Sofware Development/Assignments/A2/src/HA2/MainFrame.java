package HA2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;



public class MainFrame extends JComponent{
//	This is the Main Frame
//	1. Adding Tool bar for this frame
	private ContentPanel control = new ContentPanel();
	
	// Constructor 
	public MainFrame() {
		JFrame f = new JFrame("Directed Edges to Nodes");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationByPlatform(true);
        f.setVisible(true);
        MainFrame gp = new MainFrame();
        f.add(gp.control, BorderLayout.NORTH);
        f.add(new JScrollPane(gp), BorderLayout.CENTER);
        f.getRootPane().setDefaultButton(gp.control.createEdgeButton);
        f.pack();
        
        
        JButton instructionPanel = new JButton("1. add nodes to add an edge ||| 2.  Click on two or more nodes to add a edge,  you can't add a edge if there is only one node in the graph ||| 3. To delete an edge click on the associated node to delete it");
        //instructionPanel.setBounds(50, 200, 150, 20);
        instructionPanel.setBackground(Color.YELLOW);
        f.add(instructionPanel, BorderLayout.SOUTH);		
	}
	
	
	private class ContentPanel extends JToolBar{
		private Action newEdge = (Action) new NewEdgeAction("Add");	
		private Action moveEdge = (Action) new MoveEdgeAction("Move");
		private Action deleteEdge = (Action) new DeleteEdgeAction("Delete");
		
		private JButton createEdgeButton = new JButton(newEdge);
		private JRadioButton moveEdgeButton = new JRadioButton(moveEdge);
		private JRadioButton deleteEdgeButton = new JRadioButton(deleteEdge);
		private JPopupMenu popupMenu = new JPopupMenu();
		private ButtonGroup buttonGroup = new ButtonGroup();
		
		ContentPanel()
		{
			this.setLayout(new FlowLayout(FlowLayout.LEFT));
            this.setBackground(Color.lightGray);
            this.add(createEdgeButton);
            this.add(moveEdgeButton);
            this.add(deleteEdgeButton);
            buttonGroup.add(moveEdgeButton);
            buttonGroup.add(deleteEdgeButton);
            popupMenu.add(new JMenuItem(newEdge));
            popupMenu.add(new JMenuItem(moveEdge));
            popupMenu.add(new JMenuItem(deleteEdge));
            
		}
		
	}
	
	
	private class NewEdgeAction extends AbstractAction{
		
		public NewEdgeAction(String name) {
			super(name);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			repaint();	
		}
		
	}
	
	private class MoveEdgeAction extends AbstractAction{
		
		public MoveEdgeAction(String name) {
			super(name);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			repaint();	
		}
		
	}
	
	private class DeleteEdgeAction extends AbstractAction{
		
		public DeleteEdgeAction(String name) {
			super(name);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			repaint();	
		}
		
	}
}
