package unit5;
/* This game is written all in one class (file).
 * At some point as complexity increases, it would
 * make sense to write games in more than one class,
 * splitting the graphics off from the data and 
 * processing.
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TicTacToe {

	static final int GRID = 3; //the grid size
	static final int EMPTY = 0;

	int [][] board = new int[GRID][GRID];
	// X = +1    O = -1  empty = 0
	boolean turn = true;  //true = X turn;

	JLabel topLabel;
	GraphicsPanel grpanel;

	TicTacToe() {
		setupGame();
		createAndShowGUI();
	}

	void setupGame() {
		//clear board
		for (int i=0;i<GRID;i++) {
			for (int j=0;j<GRID;j++) {
				board[i][j]=EMPTY;
			}
		}

		//DEBUG: for testing
		board[0][0] = 1;
		board[1][1] = -1;
	}


	/* This method still needs to be written. The MouseListener method would call this. */
	void processClick(int row, int col) {


		if (turn) {
			board[row][col] = 1;
		} else {
			board[row][col] = -1;
		}
		turn = !turn;




		showMessage("Tie Game");
	}


	void createAndShowGUI() {
		JFrame frame = new JFrame("TicTacToe");
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setSize(500, 520);
		frame.setLocationRelativeTo(null);

		topLabel = new JLabel("Enter status messages here");
		topLabel.setFont(new Font("Sans Serif", Font.BOLD, 20));
		topLabel.setOpaque(true);
		topLabel.setForeground(Color.RED);
		topLabel.setVisible(false);

		grpanel = new GraphicsPanel();
		grpanel.add(topLabel);
		frame.add(grpanel);
		frame.setVisible(true);

	}



	void showMessage(String text) {
		
		topLabel.setText("    " + text + "    ");
		topLabel.setVisible(true);

		Timer timer = new Timer(1500,  
			new ActionListener() 
		{

				@Override
				public void actionPerformed(ActionEvent e) {
					topLabel.setVisible(false);
					
				}
			}
		);
		
		timer.setRepeats(false);
		timer.start();
	}


	@SuppressWarnings("serial")
	class GraphicsPanel extends JPanel {

		int jpanW, jpanH;
		int blockX, blockY;
		int spc; //spacing amount

		GraphicsPanel() {
			this.setBackground(new Color(255,255,230));
		}

		void initGraphics() {
			jpanW = this.getSize().width;		
			jpanH = this.getSize().height;	
			blockX = (int)((jpanW/GRID)+0.5);
			blockY = (int)((jpanH/GRID)+0.5);			
			spc = (int)(blockX*0.1); //10%
		}

		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			initGraphics(); //doing this every time the screen is redrawn allows the window to be resized
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			g2.setStroke(new BasicStroke(2.0f));

			g.setColor(Color.RED);
			for (int i=0;i<GRID;i++) {
				g.drawLine(blockX*i,0,blockX*i,jpanH);
				g.drawLine(0,blockY*i,jpanW,blockY*i);
			}

			g.setColor(Color.BLUE);
			g2.setStroke(new BasicStroke(4.0f));
			for (int i=0;i<GRID;i++) {
				for (int j=0;j<GRID;j++) {
					if (board[i][j] == 1) {
						g2.drawLine(blockX*i +spc ,blockY*j +spc, blockX*(i+1) -spc, blockY*(j+1) -spc);
						g2.drawLine(blockX*(i+1) -spc, blockY*j +spc, blockX*i +spc, blockY*(j+1) -spc);
					}
					if (board[i][j] == -1) {
						g2.drawOval(blockX*i +spc ,blockY*j +spc, blockX -spc*2, blockY -spc*2);
					}
				}
			}	
		}

	}

	public static void main(String[] cheese) {
		new TicTacToe();
	}
}