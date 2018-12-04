package unit5;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;



public class MapContinent implements ActionListener
{
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MapContinent();
			}
		});
	} //end main

	/*******************************************/
	//add this line
	static String filename = "save.dat";

	//REMOVE FINAL FROM GRID SIZE
	static int SIZE = 30; //board size
	/*******************************************/

	final static int SCRSIZE = 720;	//screen size
	final static int sq = 24; //pixels per square
	final static int NUM_LAND = (SIZE * SIZE /2); //number of land tiles

	final static int EMPTY = 0;		//constant for empty tile
	final static int LAND  = 1;			//contant for land tile	
	final static int LAKE  = 2;		//this is just any number used for LAKE and OCEAN
	final static int OCEAN = 3;

	final static Color COLOURBACK = new Color(242,242,242);	
	final static Color COLOUREMPTY = new Color(222,222,222);
	final static Color COLOURLAND = new Color(100,200,100);
	final static Color COLOURLAKE = new Color(120,120,255);
	final static Color COLOUROCEAN = new Color(10,10,130);

	//global variables
	int[][] board = new int[SIZE][SIZE];
	DrawingPanel panel;
	JFrame frame;

	MapContinent() {	//constructor
		initGame();
		createAndShowGUI();
	}

	//PROBLEM 3: When half of the squares are land, the land is scattered quite a lot into little islands.
	//           Find a way to make a random map that has the land in bigger chunks.
	void initGame() {		
		//clear board
		for (int i=0;i<SIZE;i++) {
			for (int j=0;j<SIZE;j++) {
				board[i][j]=EMPTY;
			}
		}

		//check setup
		if (SCRSIZE / SIZE < 20) {
			System.out.println("Board size too small for number of squares! Aborting ...");
			System.exit(0);
		}

		makeRandomMap();

		//makeContinents();	
	}

	void makeRandomMap() {
		int i,j;
		boolean done = false;
		int landTiles = 0;
		//exactly half are land
		while (!done) {
			i = (int)(Math.random() * SIZE);
			j = (int)(Math.random() * SIZE);

			if (board[i][j] == EMPTY) {
				board[i][j] = LAND;
				landTiles++;
				if (landTiles >= NUM_LAND) done=true;				
			}			
		}	
	}
	
	void makeContinents() {
	}
	
	//PROBLEM 1: Fix the function "findLakes()" so that it colours all empty squares that are adjacent to this one.
	//PROBLEM 2: Once you have solved problem 2, now set things up so that if any part 
	//           of a lake touches the edge of the board it becomes an ocean.	
	void findLakes(int x, int y) {
		
		if (board[x][y] == EMPTY) board[x][y] = LAKE;
		
	}

	void findOceans(int x, int y) {
		
	}

	void createAndShowGUI() {
		panel = new DrawingPanel();

		frame = new JFrame("Map and Continents...");
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		//+++++++ ADD BUTTONS ++++++++++++
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.DARK_GRAY); 
		JButton btnSave = new JButton("Save");
		JButton btnLoad = new JButton("Load");
		topPanel.add(btnSave);
		topPanel.add(btnLoad);
		btnSave.addActionListener(this);
		btnLoad.addActionListener(this);
		frame.add(topPanel, BorderLayout.NORTH);
		//+++++++ END ADD BUTTONS ++++++++++++

		frame.add(panel); 		
		frame.setSize(SCRSIZE, SCRSIZE); //may not be needed since my JPanel has a preferred size
		frame.setResizable(false);
		frame.setLocationRelativeTo( null );
		frame.pack();
		frame.setVisible(true);

		//once the panel is visible, initialize the graphics. (Is this before paintComponent is run?)
		panel.initGraphics();

	}

	@SuppressWarnings("serial")
	class DrawingPanel extends JPanel	//inner class
	{		
		int jpanW, jpanH;
		int blockX, blockY;		

		public DrawingPanel() {
			setBackground(COLOURBACK);
			//Because the panel size variables don't get initialized until the panel is displayed,
			//we can't do a lot of graphics initialization here in the constructor.
			this.setPreferredSize(new Dimension(SIZE*sq, SIZE*sq));
			MyMouseListener ml = new MyMouseListener();
			addMouseListener(ml);			
		}

		//** Called by createGUI()
		void initGraphics() {
			jpanW = this.getSize().width;		
			jpanH = this.getSize().height;	
			blockX = (int)((jpanW/SIZE)+0.5);
			blockY = (int)((jpanH/SIZE)+0.5);
			// System.out.println("init");
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			//Draw white grid
			g.setColor(Color.WHITE);
			for (int i=0;i<SIZE;i++) {
				g.drawLine(blockX*i,0,blockX*i,jpanH);
				g.drawLine(0,blockY*i,jpanW,blockY*i);
			}

			for (int i=0;i<SIZE;i++) {
				for (int j=0;j<SIZE;j++) {
					colourRect(i,j,g);						
				}
			}			
		}

		void colourRect(int i, int j, Graphics g) {
			switch(board[i][j]) {

			case LAND:
				g.setColor(COLOURLAND);
				break;
			case LAKE:
				g.setColor(COLOURLAKE);
				break;
			case OCEAN:
				g.setColor(COLOUROCEAN);
				break;
			case EMPTY:
			default:
				g.setColor(COLOUREMPTY);					
			}	
			g.fillRect(blockX*i+1, blockY*j+1, blockX-2, blockY-2);
		}		

		class MyMouseListener extends MouseAdapter	{	//inner class inside DrawingPanel
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				//calculate which square you clicked on
				int i = (int)  x/blockX;
				int j = (int) y/blockY;	// blockY/y

				//allow the right mouse button to toggle/cycle the terrain
				if (e.getButton() != MouseEvent.BUTTON1) {
					switch (board[i][j]) {
					case LAND:
						board[i][j] = EMPTY;
						break;
					default:
						board[i][j] = LAND;
					}
					repaint();
					return;
				}

				findLakes(i,j);								
				repaint();
			}		
		} //end of MyMouseListener class

	} //end of DrawingPanel class

	/* Action Listener for Load and Save buttons*/
	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			if (e.getActionCommand().equals("Load"))loadData();
		} catch (IOException e1) {}
		
		if (e.getActionCommand().equals("Save")) saveData();		
	}

	void saveData() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter( new BufferedWriter( 
					new FileWriter( new File (filename))));
		} catch (IOException e) {
			System.out.println("CANNOT WRITE TO " + filename);
			e.printStackTrace();
			System.exit(0);
		}

		pw.println(SIZE);  //Grid Size (wow! that was easy.)

		//write out each row of data
		for (int i = 0; i < SIZE; i++) {
			String s = "";
			for (int j = 0; j < SIZE; j++) {
				s = s + board[j][i];
			}
			pw.println(s);
		}
		pw.close();
	}

	void loadData() throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader( new FileReader (new File (filename)));
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open file for reading!");
			e.printStackTrace();
			System.exit(0);
		}

		String s = "";
		//		try {
		s = br.readLine();	//read first line
		//		} catch (IOException e) {}
		SIZE = Integer.valueOf(s);

		//reset the array size
		board = new int[SIZE][SIZE];

		//read in each row
		for (int i = 0; i < SIZE; i++) {
			s = br.readLine();
			for (int j = 0; j < SIZE; j++) {
				char c = s.charAt(j);
				board[j][i] = c - '0';
			}			
		}
		br.close();
		
		//reset the JFrame or JPanel depending on how you did your pgm
		panel.setPreferredSize(new Dimension(SIZE*sq, SIZE*sq));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.repaint();
	}
}















