package unit4;
import java.awt.*;
import javax.swing.*;
public class Scaling 
{
	public static void main (String [] args)
	{
		new Scaling();
	}
	
	Scaling()
	{
		//JFrame Setup:
		JFrame window =  new JFrame();
		window.setTitle("Graphing");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		DrawingPanel panel = new DrawingPanel();
		window.add(panel);
		window.pack();
		window.setVisible(true);
	}
	
	int panW, panH;
	double xmin = -5.0, xmax = +5.0;
	double ymin = -5.0, ymax = +5.0;
	
	@SuppressWarnings("serial")
	class DrawingPanel extends JPanel
	{
		DrawingPanel()
		{
			panW = panH = 900;
			this.setPreferredSize(new Dimension(panW, panH));
			this.setBackground(Color.WHITE);
		}
		
		@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			//Draws axies:
			g.drawLine(toPX(0.0), 0, toPX(0.0), panH);
			g.drawLine(0, toPY(0.0), panW, toPY(0.0));
			
			double xStep = (xmax - xmin) / panW;
			for (double x = xmin; x < xmax; x += xStep)
			{
				double y = x*x;
				int px = toPX(x);
				int py = toPY(y);
				g.drawLine(px, py, px, py);
			}
		}
		
		//Changes x value on the axis to a pixel location:
		int toPX (double x)
		{
			int px = (int) ((x - xmin)*panW / (xmax - xmin));
			return px;
		}
		
		//Changes y value on the axis to a pixel location:
		int toPY (double y)
		{
			int py = panH - (int) ((y - ymin)*panH / (ymax - ymin));
			return py;
		}
	}
}
