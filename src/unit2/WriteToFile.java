package unit2;
import java.io.*;
public class WriteToFile 
{
	public static void main(String[] args) 
	{
		/*** SETUP ***/
		String fileName = "text.txt";
		String text = "";
		//create the buffred reader 
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		//create the printwriter
		BufferedWriter writer = null;
		try 
		{
			writer = new BufferedWriter(new BufferedWriter(new FileWriter(fileName)));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*** MAIN LOOP - read in a line of text and write it to the file ****/
		System.out.println("Type anything:\nType \'EXIT\' when you're done.");
		try
		{	
			while (true)
			{
				text = reader.readLine();
				if (text.equals("EXIT")) break;
				writer.write(text+"\n");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*** FINISH UP .... ***/
		try 
		{
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
