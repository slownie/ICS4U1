package unit4;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SimpleGraphics {

	public static void main(String[] args) {
		new SimpleGraphics();
	}
	
	//global variables
	JFrame window = new JFrame("This is the title");
	JPanel panel;
	
	//constructor
	SimpleGraphics(){
		//Set up Window
		window.setSize(600, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		window.setLocationRelativeTo(null); //centre window
		
		//set up panel
		panel = new JPanel();
		panel.setBackground(Color.YELLOW);
		
		//Add stuff to panel:
		JLabel label1 = new JLabel("  Welcome!  ");
		label1.setForeground(Color.WHITE);
		label1.setBackground(new Color(0,0,20));
		label1.setOpaque(true);
		
		Font f = new Font("Arial",Font.PLAIN,28);
		label1.setFont(f);
		
		panel.add(label1);
		panel.add(new JLabel("label 2"));
		JButton btn = new JButton("Help");
		btn.setFont(f);
		panel.add(btn);
		
		for (int i = 0; i < 10; i++) {
			panel.add(new JButton("Click me"));	
		}
		
		
		window.add(panel);	//must add the panel to JFrame
		//this is the last thing you do:
		window.setVisible(true);		
	}

}
