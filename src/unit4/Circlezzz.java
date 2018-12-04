package unit4;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Circlezzz implements MouseMotionListener, MouseListener  
{

	public static void main(String[] args) 
	{
		new Circlezzz();
	}

	Circle circle = null;
	GrPanel panel;	//JPanel panel doesn't work since I've created new variables that don't exist in the parent class
	
	Circlezzz(){
		JFrame window = new JFrame("circlezzz");
		window.setSize(800,700);
		window.setLocationRelativeTo(null); //always in middle
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new GrPanel();
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
		window.add(panel);

		window.setVisible(true); 
	}

	@SuppressWarnings("serial")
	private class GrPanel extends JPanel {
		GrPanel(){
			this.setBackground(Color.BLACK);
		}
		
		boolean clear = true;
		
		@Override
		public void paintComponent(Graphics g) {
			if (clear) {
				super.paintComponent(g);
				clear = false;
			}
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			if (circle == null) return;
			
			if (circle.r > 0 )	circle.paint(g);
		}
		
		
	}

	private class Circle {
		static final int R = 100;
		int x,y;
		double r;
		
		Circle(int x,int y) {
			r = R;
			this.x = x;
			this.y = y;
		}
		
		void paint(Graphics g) {
			int r = (int)(this.r + 0.5);
			int width = 2*r;
			int height = width;
			g.setColor(Color.YELLOW);
			g.drawOval(x-r, y-r, width, height);
		}
	}

	

	@Override
	public void mousePressed(MouseEvent e) {
		
		circle = new Circle(e.getX(), e.getY());
		panel.clear = true;
		panel.repaint();
		
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if (circle != null && circle.r >=0) {
			//circle.r -=0.5;
			circle.r *=0.95;
			circle.x = e.getX();
			circle.y = e.getY();
			panel.repaint();
		}
		
	}
	
	@Override
	public void mouseClicked (MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited (MouseEvent e) {}
	@Override
	public void mouseDragged(MouseEvent e) {}


	
}
