package quiz;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.*;

public class GraphicsMain 
{
	public static void main(String[] args) 
	{
		new GraphicsMain();
	}

	final static int panW = 800;
	final static int panH = 800;
	
	GrPanel panel;
	
	ArrayList<Shape> shapeList = new ArrayList<Shape>();
	final static int SHAPENUMBER = 5;
	
	GraphicsMain()
	{
		panel = new GrPanel();
		
		//Setup shapeList:
		for (int i = 0; i < 5; i++)
		{
			shapeList.add(i, new Shape());
		}
		
		JFrame window = new JFrame("Shape");
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		window.add(panel);

		window.pack();
		window.setLocationRelativeTo(null); //always in middle
		window.setVisible(true); 
	
		runGame(); //instead of a timer.
		
		window.dispose(); //close window when runGame() exits.
	}

	void runGame() 
	{
		int sleeptime = 5;
		
		while(true) 
		{
			for (int i = 0; i < SHAPENUMBER; i++)
			{
				shapeList.get(i).move();
			}
			
			panel.repaint();
			
			try { Thread.sleep(sleeptime); }
			catch (InterruptedException e) {}
		}
	}

	@SuppressWarnings("serial")
	private class GrPanel extends JPanel 
	{
		GrPanel()
		{	
			this.setPreferredSize(new Dimension(panW, panH)); //the JPanel is the exact dimensions specified and the JFrame adjusts around it due to pack()
			this.setBackground(Color.BLACK);
			//this.setBackground(new Color(0,0,0,10));
		}

		@Override
		public void paintComponent(Graphics g) 
		{
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			for (Shape s : shapeList)
			{
				s.paint(g2);
			}
		}
	}
}
