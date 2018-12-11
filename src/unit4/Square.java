package unit4;
import java.awt.Color;
import java.awt.Graphics;

class Square 
{
	//Coordinates of the Square:
	int xCord;
	int yCord;
	
	int xDest;
	int yDest;
	
	//Width and Height of the Square:
	int width = 50;
	int height = 50;
	
	//Speeds of the Cube:
	int xSpeed;
	int ySpeed;
	
	Square(int xCord, int yCord, int xDest, int yDest)
	{
		this.xCord = xCord;
		this.yCord = yCord;
		
		this.xDest = xDest;
		this.yDest = yDest;
		
		xSpeed = 2;
		ySpeed = 2;
	}
	
	void paint(Graphics g)
	{
		g.setColor(Color.CYAN);
		g.fillRect(xCord, yCord, width, height);
	}
	
	void move()
	{
		if (xDest > xCord)
		{
			xCord += xSpeed;
			//If the Square reaches it's Destination on the X-Axis, set the Destination on the Y-Axis to 50:
			if (xCord == xDest)
			{
				yDest = 50;
			}
		}
		
		if (yDest > yCord) 
		{
			yCord += ySpeed;
			if (yCord == yDest)
			{
				xDest = 0;
			}
		} 
		
		if (xDest < xCord) 
		{
			xCord -= xSpeed;
			if (xCord == xDest)
			{
				yDest = 0;
			}
		}  
		
		if (yDest < yCord) 
		{
			yCord -= ySpeed;
			if (yCord == yDest)
			{
				xDest = 50;
			}
		}
	}
}
