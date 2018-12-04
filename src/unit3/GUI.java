/* Samuel Lownie
 * October 10th 2018
 * GUI and Action Listener Practice.
 */
package unit3;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class GUI 
{
	//Global Variables:
	JFrame window = new JFrame("GUI Program");
	JPanel panel = new JPanel();

	public static void main (String [] args)
	{
		new GUI();
	}
	
	GUI()
	{
		//JFrame Setup:
		window.setSize(400, 500);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Sets Panel to BorderLayout:
		panel.setLayout(new BorderLayout());
		
		//JLabel:
		JLabel title = new JLabel("GUI Testing");
		panel.add(title, BorderLayout.PAGE_START);
		
		//JButton:
		JButton button = new JButton("Click on me!");
		button.addActionListener(new AL1());
		panel.add(button, BorderLayout.PAGE_END);
		
		//JRadioButtons:
		JRadioButton rButton1 = new JRadioButton("Red");
		rButton1.setActionCommand("RED");
		rButton1.addActionListener(new AL2());
		rButton1.setOpaque(false);
		
		JRadioButton rButton2 = new JRadioButton("Green");
		rButton2.setActionCommand("GREEN");
		rButton2.addActionListener(new AL2());
		rButton2.setOpaque(false);
		
		JRadioButton rButton3 = new JRadioButton("Blue");
		rButton3.setActionCommand("BLUE");
		rButton3.addActionListener(new AL2());
		rButton3.setOpaque(false);
		
		//ButtonGroup:
		JPanel radioPanel = new JPanel();
		radioPanel.setOpaque(false);
		ButtonGroup group = new ButtonGroup();
		group.add(rButton1);
		group.add(rButton2);
		group.add(rButton3);
		
		radioPanel.add(rButton1);
		radioPanel.add(rButton2);
		radioPanel.add(rButton3);
		panel.add(radioPanel);
		
		window.add(panel);
		window.setVisible(true);
	}
	
	//Counts how many times the user has clicked the button:
	class AL1 implements ActionListener
	{
		int click;
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			window.setTitle("Number of Clicks: "+click++);
			if (click > 25)
			{
				window.setTitle("Tired Yet? "+click);
			}
			
			if (click > 50)
			{
				window.setTitle("Okay that's enough: "+click);
			}
		}
	}
	
	//Changes the Panel Background based on what button you click:
	class AL2 implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if (e.getActionCommand().equals("RED"))
			{
				panel.setBackground(Color.RED);
			}
			
			if (e.getActionCommand().equals("GREEN"))
			{
				panel.setBackground(Color.GREEN);
			}
			
			if (e.getActionCommand().equals("BLUE"))
			{
				panel.setBackground(Color.BLUE);
			}
		}
	}
}
