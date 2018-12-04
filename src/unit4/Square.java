package unit4;
import java.awt.Color;
import java.awt.Graphics;

class Square 
{
	//Coordinates of the Square:
	int xCord;
	int yCord;
	
	//Width and Height of the Square:
	int width = 50;
	int height = 50;
	
	//Speeds of the Cube:
	int speed = 2;
	
	Square(int x, int y)
	{
		xCord  = x;
		yCord  = y;
	}
	
	void paint(Graphics g)
	{
		g.setColor(Color.CYAN);
		g.fillRect(xCord, yCord, width, height);
	}
}
