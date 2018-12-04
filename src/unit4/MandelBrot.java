package unit4;
import java.awt.*;
import javax.swing.*;
public class MandelBrot
{
	//GLOBAL VARIABLES
	static double xMin = -2.5, xMax = 1.5, yMin = -1.5, yMax = 1.5;    	    	
	static double xStep, yStep;	
	final static int MAX_ITER=255;
	final static Dimension SCREEN = new Dimension(1000,800);

	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				createAndShowGUI();
			}
		});
	}

	/*********************************************************************************
	Name:		createAndShowGUI()
	Purpose:	sets up JFrame. Note: this is static. No object is actually created.
	Called by:	main()
	Calls: 		none. Creates JPanel subclass and displays it.
	**********************************************************************************/
	private static void createAndShowGUI() 
	{
		//JFrame Setup:
		JFrame frame = new JFrame("Mandelbrot Set");
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );		
		frame.setSize(SCREEN);		
		frame.setResizable(false);
		frame.setLocationRelativeTo( null );

		DrawingPanel panel = new DrawingPanel();
		frame.add(panel);
		frame.setVisible(true);
	}

	/*********************************************************************************
	Name:		inner class DrawingPanel (also static)
	Purpose:	subclass JPanel so that we can make our own paintComponent()
	Note: 		panW and panH must not be zero, or initGraphics() will have a 
	 			divide by zero error. So we'll set a rough initial estimate for them	
	**********************************************************************************/
	@SuppressWarnings("serial")
	private static class DrawingPanel extends JPanel
	{		
		int panW=500;
		int panH=500;
		
		/*********************************************************************************
		Constructor
		Purpose: set background colour, but this could be done when we create "panel".
		Calls: 		super()  -- why?
		**********************************************************************************/
		public DrawingPanel() {
			//super();
			setBackground(Color.WHITE);				
		}
		
		/*********************************************************************************
		Name:		paintComponent()
		Purpose:	This function paints the graph on the GraphPanel.
		Called automatically by: repaint()
					repaint() is called whenever the screen is resized.
		Calls: 		initGraphics, plotPoint, recurse
		**********************************************************************************/
		@Override
		public void paintComponent(Graphics g) 	
		{
			super.paintComponent(g);

			initGraphics();
			
			//test: draw a parabola	
			for (double d = xMin; d < xMax; d +=xStep) 
			{
				plotPoint(d,d*d,Color.GREEN,g);        
			}

			//MandelBrot code here
			for (double x = xMin; x < xMax; x += xStep)
			{
				for (double y = yMin; y < yMax; y += yStep)
				{
					double cReal = x;
					double cImag = y;
					Color clr;
					
					int n = (recurse(cReal, cImag));
					if ( n == 0)
					{
						clr = Color.BLACK;
					} else {
						clr = Color.getHSBColor(n/255.0f, 1.0f, 1.0f);
					}
					plotPoint(x, y, clr, g);
				}
			}
			
		} //end of paintComponent

		int recurse(double cReal, double cImag)
		{
			double temp, zReal = 0.0, zImag = 0.0;
			int i = 0;
			
			while (i++ <= MAX_ITER)
			{
				temp = zReal*zReal - zImag*zImag + cReal;
				zImag = 2 * zReal*zImag + cImag;
				zReal = temp;
				
				if (zReal*zReal + zImag*zImag > 4) break;
				
			}
			
			if ( i > MAX_ITER) return 0;
			return i;
		}
		
		
		/*********************************************************************************
		Name:		initGraphics()
		Purpose:	This function sets up the screen size variables needed for drawing to the screen.
		 	It relates scale-space values to pixel-space ones.
			It needs to be recalculated if the screen is resized, or if the scale changes.		
		Called from: paintComponent
		Calls: --
		**********************************************************************************/
		
		void initGraphics() {	
			panW = this.getSize().width;		
			panH = this.getSize().height;		
			//set xstep and ystep
			xStep = (xMax - xMin) / panW;
			yStep = (yMax - yMin) / panH;
			//System.out.println("IG " + xMax + " " + xMin + " " + jpanW + " " + xStep);				
		}

		/*********************************************************************************
		Name:		plotPoint()
		Purpose:	This function draws a point at the correct pixel based on the screen size
					and the scale of the graph.
		Parameters: x and y are the point coordinates in scale space, not screen pixel locations.
		Called from: paintComponent()
		Calls: --
		**********************************************************************************/
		public void plotPoint(double x, double y, Color col, Graphics g)
		{
			int px1 = (int) (panW * (x - xMin) / (xMax - xMin));			
			int py1 = panH - (int) (panH * (y - yMin) / (yMax - yMin));			
			g.setColor (col);
			g.drawLine (px1, py1, px1, py1);
		}

	}// end of inner class
}
