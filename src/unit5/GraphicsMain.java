package unit5;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.*;

import javax.swing.*;

public class GraphicsMain implements ActionListener  
{
	public static void main(String[] args) 
	{
		new GraphicsMain();
	}

	final static int panW = 800;
	final static int panH = 800;
	final static int TSPEED = 10;

	
	GrPanel panel;	//JPanel panel doesn't work since I've created new variables that don't exist in the parent class
	Shape_v2 shape;
	
	
	GraphicsMain()
	{
		JFrame window = new JFrame("Shape");
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		shape = new Shape_v2();
		panel = new GrPanel();
		window.add(panel);

		window.pack();
		window.setLocationRelativeTo(null); //always in middle

		window.setVisible(true); 
	
		Timer timer = new Timer(TSPEED, this);
		timer.start();
	
	}

	@SuppressWarnings("serial")
	private class GrPanel extends JPanel 
	{
		GrPanel()
		{
			this.setBackground(Color.BLACK);
			this.setPreferredSize(new Dimension(panW, panH));
		}


		@Override
		public void paintComponent(Graphics g) 
		{
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			shape.paint(g2);			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		shape.move();
		panel.repaint();		
	}
}
