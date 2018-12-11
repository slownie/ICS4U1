package unit4;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoadingIcon 
{
	//Global Variables:
	JFrame window;
	DrawingPanel drPanel;
	
	//ArrayList of Squares:
	ArrayList<Square> squares = new ArrayList<Square>();
	
	//Timer Variables:
	final static int T1SPEED = 5000; //Speed of Timer
	final static int T1DELAY = 300; //Initial Delay
	
	final static int T2SPEED = 10;
	
	final static int FRAMERATE = 60;
	
	public static void main (String [] args) 
	{
		new LoadingIcon();
	}
	
	LoadingIcon()
	{
		//JFrame Setup:
		window = new JFrame("Loading...");
		window.setSize(500, 500);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		drPanel = new DrawingPanel();
		
		window.add(drPanel);
		window.setVisible(true);
		
		Timer createSquareTimer = new Timer(T1SPEED, new TimerListener());
		createSquareTimer.setInitialDelay(T1DELAY);
		createSquareTimer.start();
		
		Timer moveTimer = new Timer(T2SPEED, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				moveSquares();
				drPanel.repaint();
			}
		});
		moveTimer.start();
	}
	
	private void moveSquares()
	{
		for (Square s : squares)
		{
			s.move();
		}
	}
	
	@SuppressWarnings("serial")
	private class DrawingPanel extends JPanel
	{
		DrawingPanel()
		{
			this.setBackground(Color.BLACK);
		}
		
		@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			for (Square s : squares)
			{
				s.paint(g);
			}
		}
	}
	
	private class TimerListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			squares.add(new Square(0, 0, 50, 0));
			drPanel.repaint();
		}
	}
}
