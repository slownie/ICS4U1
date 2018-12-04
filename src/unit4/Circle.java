package unit4;
import java.awt.*;

class Circle {
	int cx, cy; //centre coordinates
	int r = 20;	
	int vx = 3;
	int vy = 5;	//speeds
	
	Circle(int maxX, int maxY) {
		//random centre. FIXME: do not let circles be made on edges
		cx = (int)(Math.random()*(maxX-2*r))+r;
		cy = (int)(Math.random()*(maxY-2*r))+r;
		
	}
	
	void paint(Graphics g) {
		g.setColor(Color.CYAN);
		g.drawOval(cx-r, cy-r, r*2,r*2);
	}
}




