package unit5;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MapAndContinents 
{
	public static void main(String[] args) 
	{
		new MapAndContinents();
	} 

	//Global Variables:
	static int GRIDSIZE = 30; //Size of the Grid.
	final static int SCRSIZE = 720;	//Screen Size.
	final static int PPS = 24; //Pixels per Square.
	final static int LANDNUM = (GRIDSIZE * GRIDSIZE / 2); //Number of LandTiles.

	//Constant Variables:
	final static int EMPTYTILE = 0;		
	final static int LANDTILE  = 1;		
	final static int LAKETILE  = 2;		
	final static int OCEANTILE = 3;

	//Colours:
	final static Color EMPTYCOLOUR = new Color (222, 222, 222);
	final static Color LANDCOLOUR  = new Color (100, 200, 100);
	final static Color LAKECOLOUR  = new Color (120, 120, 255);
	final static Color OCEANCOLOUR = new Color ( 10,  10, 130);

	int[][] BOARD = new int[GRIDSIZE][GRIDSIZE]; 
	
	JFrame window;
	DrawingPanel drPanel;
	
	MapAndContinents() 
	{
		initMap();
		drawGUI();
	}

	void initMap() 
	{		
		//Clears the Map, by setting every tile to an EMPTYTILE (0):
		for (int i = 0; i < GRIDSIZE; i++) 
		{
			for (int j = 0; j < GRIDSIZE; j++) 
			{
				BOARD[i][j]= EMPTYTILE;
			}
		}

		//Checks if the Screen Size divided by the Grid Size is greater than 20:
		if (SCRSIZE / GRIDSIZE < 20) 
		{
			System.out.println("Error: Board Size to Small for the Screen.");
			System.exit(0);
		}

		generateRandomMap();
	}

	void generateRandomMap() 
	{
		//Board Coordinates:
		int i;
		int j;
		
		boolean isFinished = false;
		int landTileCount = 0;
		
		//Makes half the tiles LANDTILEs:
		while (!isFinished) 
		{
			i = (int)(Math.random() * GRIDSIZE);
			j = (int)(Math.random() * GRIDSIZE);

			if (BOARD[i][j] == EMPTYTILE) 
			{
				BOARD[i][j] = LANDTILE;
				landTileCount++;
				if (landTileCount >= LANDNUM) isFinished=true;				
			}			
		}	
	}
	
	void findLakes(int i, int j, int colour) 
    {
		if (BOARD[i][j] == EMPTYTILE)
        {
			System.out.println(colour);
            BOARD[i][j] = colour;
        
            for (int x = i - 1; x <= i + 1; x++)
            {
                for (int y = j - 1; y <= j + 1; y++)
                {
                	//Checks for unusable Coordinates:
                    if (x < 0 || y < 0 || x > GRIDSIZE - 1 || y > GRIDSIZE - 1 || BOARD[x][y] != EMPTYTILE) continue;
                    
                    //Skips Diagonals:
                    if (x == i - 1 && y == j + 1) continue;
                    if (x == i + 1 && y == j + 1) continue;
                    if (x == i - 1 && y == j - 1) continue;
                    if (x == i + 1 && y == j - 1) continue;
                    
                    //Recursion:
                    findLakes(x, y, colour);
                }
            }
        }
    }
	
	boolean isOceanRightDown(int x, int y)
	{
		//Returns false if not an empty tile:
		if (BOARD[x][y] != EMPTYTILE) return false;
		
		//Checks if the Coordinates are on the Border of the Grid:
		if (x == 0 || x == GRIDSIZE - 1 || y == 0 || y == GRIDSIZE - 1) return true;
		
		//Checks the tile to the right:
		if (isOceanRightDown(x + 1, y)) return true;
		
		//Returns the tile below:
		return isOceanRightDown(x, y + 1);
	}
	
	boolean isOceanLeftUp(int x, int y)
	{
		//Returns false if not an empty tile:
		if (BOARD[x][y] != EMPTYTILE) return false;
		
		//Checks if the Coordinates are on the Border of the Grid:
		if (x == 0 || x == GRIDSIZE - 1 || y == 0 || y == GRIDSIZE - 1) return true;
		
		//Checks the tile to the left:
		if (isOceanLeftUp(x - 1, y)) return true;
		
		//Returns the tile above:
		return isOceanLeftUp(x, y - 1);
	}
	
	void drawGUI() 
	{
		window = new JFrame("Map and Continenents");
		drPanel = new DrawingPanel();
		
		//JFrame Setup:
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setSize(SCRSIZE, SCRSIZE);
		
		window.add(drPanel);
		window.pack();
		window.setVisible(true);
		drPanel.initGraphics();
	}

	@SuppressWarnings("serial")
	class DrawingPanel extends JPanel	
	{
		//JFrame Width and Height:
		int fWid; 
		int fHei;
		
		//Box Width and Height:
		int bX; 
		int bY;		

		public DrawingPanel() 
		{
			this.setPreferredSize(new Dimension(GRIDSIZE * PPS, GRIDSIZE * PPS));
			MyMouseListener ml = new MyMouseListener();
			addMouseListener(ml);
		}

		void initGraphics() 
		{
			fWid = this.getSize().width;		
			fHei = this.getSize().height;	
			bX = (int)((fWid/GRIDSIZE) + 0.5);
			bY = (int)((fHei/GRIDSIZE) + 0.5);
		}

		public void paintComponent(Graphics g) 
		{
			super.paintComponent(g);
		
			//Draw a Black Grid:
			g.setColor(Color.BLACK);
			for (int i = 0; i < GRIDSIZE; i++) 
			{
				g.drawLine(bX * i, 0, bX * i, fHei);
				g.drawLine(0, bY * i, fWid, bY * i);
			}

			//Colours all the Blocks:
			for (int i = 0; i < GRIDSIZE; i++) 
			{
				for (int j = 0; j < GRIDSIZE; j++) 
				{
					colourBlock(i, j, g);						
				}
			}			
		}

		void colourBlock(int i, int j, Graphics g)
		{
			switch (BOARD[i][j])
			{
			case EMPTYTILE:
				g.setColor(EMPTYCOLOUR);
				break;
			case LANDTILE:
				g.setColor(LANDCOLOUR);
				break;
			case LAKETILE:
				g.setColor(LAKECOLOUR);
				break;
			case OCEANTILE:
				g.setColor(OCEANCOLOUR);
				break;
			}
			
			g.fillRect(bX * i + 1, bY * j + 1, bX - 1, bY - 1);
		}
		
		class MyMouseListener extends MouseAdapter	
		{	
			public void mousePressed(MouseEvent e) 
			{
				int x = e.getX();
				int y = e.getY();
				int i = (int)  x/bX;
				int j = (int)  y/bY;	

				//Changes the Tiles with Right-Click:
				if (e.getButton() != MouseEvent.BUTTON1) 
				{
					if (BOARD[i][j] == LANDTILE)
					{
						BOARD[i][j] = EMPTYTILE;
						repaint();
						
					} else {
						BOARD[i][j] = LANDTILE;
						repaint();
					}
				}
				
				//Changes Tiles to Water with Left-Click:
				if (e.getButton() == MouseEvent.BUTTON1)
				{
					switch (BOARD[i][j])
					{
					case EMPTYTILE:
						int c = (isOceanRightDown(i, j) || isOceanLeftUp(i, j)) ? OCEANTILE : LAKETILE;
						System.out.println(c);
						findLakes(i, j, c);
						repaint();
						break;
					
					//Changes LandTiles to LakeTiles:	
					case LANDTILE:
						BOARD[i][j] = LAKETILE;
						repaint();
						break;
						
					}
				}
			}
		} 
	}
}
