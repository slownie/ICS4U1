package unit4;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class MovingCircles {

	public static void main(String[] args) {
		new MovingCircles();
	}
	
	//global variables
	DrawingPanel drPanel;
	int panW, panH;
	JFrame window; //probably does not need to be a global variable
	ArrayList<Circle> circles = new ArrayList<Circle>();
	
	//for timer to create circles
	final static int T1_SPEED = 4000; //speed of timer (ms)
	final static int T1_DELAY = 100; //initial delay
	
	//for timer to move circles
	final static int T2_SPEED = 10;
	
	MovingCircles() {
		//set up JFrame
		window = new JFrame("Moving Circles");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//make the JFrame take up the whole screen		
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		drPanel = new DrawingPanel();
		window.add(drPanel);
		//window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		//make sure that you have imported javax.swing.Timer NOT util.Timer
		Timer createCircleTimer = new Timer(T1_SPEED, new Timer1Listener());
		createCircleTimer.setInitialDelay(T1_DELAY);
		createCircleTimer.start();	
		
		//make an anonymous inner class for the Timer ActionListener
		Timer moveTimer = new Timer(T2_SPEED, new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				moveCircles();
				drPanel.repaint();
			}			
		});
		moveTimer.start();
		
		/*Timer moveTimer = new Timer(T2_SPEED, (ActionEvent e) ->
		{
			moveCircles();
			drPanel.repaint();						
		}); */
	}
	
	private void moveCircles() {
		for (Circle c : circles) {
			//move
			c.cx += c.vx;
			c.cy += c.vy;
			//bounce off walls
			if (c.cx-c.r < 0) c.vx *= -1;
			if (c.cy-c.r < 0) c.vy *= -1;
			if (c.cx+c.r > panW) c.vx *= -1;
			if (c.cy+c.r > panH) c.vy *= -1;
		}
	}
	
	@SuppressWarnings("serial")
	private class DrawingPanel extends JPanel {
		DrawingPanel() 
		{
			this.setBackground(Color.BLACK);			
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g); //repaint background
			panW = this.getWidth();
			panH = this.getHeight();
			
			//change g to a g2D object to set AntiAliasing
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
					RenderingHints.VALUE_ANTIALIAS_ON);
			
			g.setColor(Color.WHITE);
			g.drawLine(0, 0, panW, panH);
			//draw all circles
			for (Circle c : circles) {
				c.paint(g);
			}
		}
	}

	private class Timer1Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//create 10 circles
			for (int i=0; i<10; i++) {
				circles.add( new Circle(panW, panH));
			}
			drPanel.repaint();
		}		
	}
}
