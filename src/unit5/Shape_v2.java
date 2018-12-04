package unit5;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.Polygon;
import java.awt.geom.Point2D;

public class Shape_v2 
{

	final static int RMAX = 200, RMIN = 50;
	int cx, cy;
	int r;	
	int vx, vy;
	RadialGradientPaint paint;
	private int panW = GraphicsMain.panW;
	private int panH = GraphicsMain.panH;
	
	//Moving Constants:
	static final int UP = 1;
	static final int DOWN = 2;
	static final int LEFT = 3;
	static final int RIGHT = 4;
	static final int SPEED = 2; //speed for all movement (in pixels)
	private int direction = RIGHT; //Start moving RIGHT
	Polygon poly = new Polygon();
	
	Shape_v2() 
	{
		
		//Set values for instance variables
		@SuppressWarnings("unused")
		int z = (panW > panH) ? panH : panW;
		r = RMIN;
		/*cx = (int)(Math.random()*(z-2*r)+r);
		cy = (int)(Math.random()*(z-2*r)+r);*/
		//cx = cy = 100;
		vx = vy = 1;
		
		
		//createPolygon();
		createStar(6);
		
		Point2D center = new Point2D.Float(150,240);
        float radius = 60;
        float[] dist = {0.1f, 0.9f};
        Color[] colors = {Color.MAGENTA.darker(), Color.CYAN};
		paint = new RadialGradientPaint(center, radius, dist, colors,CycleMethod.REFLECT);
	}
	
	void paint(Graphics2D g2) 
	{
//		g.setColor(Color.CYAN);		
		g2.setPaint(paint);
		g2.fillPolygon(poly);
	}
	
	@SuppressWarnings("unused")
	private void createPolygon()
	{
		int diameter = RMAX - RMIN;
		for (int i = 0; i < 16; i++)
		{
			int x1 = 2*(int)(Math.random()*diameter) - diameter;
			int y1 = 2*(int)(Math.random()*diameter) - diameter;
			poly.addPoint(cx + x1, cy + y1);
		}
	}
	
	
	//1) Write out how the createStar() Method Works:
	private void createStar(int numPoints)
	{
		//Two Local Variables for the X and Y Points are Created and Initialized:
		int x = 0;
		int y = 0;
		
		//
		for (int i = 0; i < numPoints*2; i++)
		{
			//2) Change to a Ternary Operator:
			y = (i % 2 == 0) ? RMIN: RMAX;
			
			if (i%2 == 0)
			{
				y = RMIN;
			} else {
				y = RMAX;
			}
			
			Point2D p2D = rotatePoint(i * 188.0/numPoints, x, y, 0, 0);
			int newX = (int) (p2D.getX() + cx);
			int newY = (int) (p2D.getY() + cy);
			
			//A Polygon is Created at these new X and Y Variables:
			poly.addPoint(newX, newY);
		}
	}
	
	void move() 
	{
		movePolygon();
		//pulse();
	}
	
	void movePolygon()
	{
		moveCircle(); //to move the center (needed for bouncing)
		poly.translate(vx, vy);
	}
	
	private void moveCircle() 
	{		
		//move
		this.cx += this.vx;
		this.cy += this.vy;
//		//bounce off walls. Add check to make sure that they don't get stuck in walls
		if (this.cx-this.r < 0    && this.vx < 0) this.vx *= -1;
		if (this.cy-this.r < 0    && this.vy < 0) this.vy *= -1;
		if (this.cx+this.r > panW && this.vx > 0) this.vx *= -1;
		if (this.cy+this.r > panH && this.cy > 0) this.vy *= -1;	
	}
	
	@SuppressWarnings("unused")
	private void moveCircle2()
	{
		//move:
		if (direction == RIGHT) 
		{
			cx += SPEED;
			if (cx > panW * 0.9) direction = DOWN; //If the Circle is 90% across the screen, move down
		}
		
		if (direction == DOWN)
		{
			cy += SPEED;
			if (cy > panH * 0.9) direction = LEFT;
		}
		
		if (direction == LEFT)
		{
			cx -= SPEED;
			if (cx < panW * 0.1) direction = UP;
		}
		
		if (direction == UP)
		{
			cy -= SPEED;
			if (cy < panH * 0.1) direction = RIGHT;
		}
	}
	
	int tick = 0;
	@SuppressWarnings("unused")
	private void pulse() 
	{
		tick++;
		r = (int) (RMIN + RMIN/3*Math.sin(Math.toRadians(tick)));
	}
	
	Point2D rotatePoint(double angle, double x, double y, double centrex, double centrey) {
		angle = Math.toRadians(angle);
		double newx = (x-centrex) * Math.cos(angle) + (y-centrey) * Math.sin(angle);
		double newy = -(x-centrex) * Math.sin(angle) + (y-centrey) * Math.cos(angle);
		Point2D pd = new Point2D.Double(newx+centrex,newy+centrey); 
		return pd;
	}
}
