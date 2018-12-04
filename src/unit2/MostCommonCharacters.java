package unit2;
import java.io.*;
import java.util.Arrays;
public class MostCommonCharacters 
{
	public static void main(String[] args) 
	{
		String fileName = "Dracula.txt";
		int totals[] = new int[26];
		String text = "";
		
		//Create a FileReader and then wrap it with BufferedReader:
		BufferedReader brFile = null;
		try 
		{
			brFile = new BufferedReader (new FileReader (new File(fileName)));
		} catch (FileNotFoundException e) {
			System.out.println("Error: File not found.");
			System.exit(0);
		}
			
		while(true)
		{
			try 
			{
				text = brFile.readLine();
			} catch (IOException e) {
				System.out.println("Error: I can't read this.");
				e.printStackTrace();
			}
			if (text == null) break;
			
			text = text.toUpperCase();
			for (int i= 0; i < text.length(); i++) 
			{
				char cc = text.charAt(i);
				if (cc < 'A' || cc > 'Z') continue;
				totals[cc-65]++;	
				/* A adds 1 to element 0  (A is changed to 0, B is changed to 1, ...) 
				   B adds 1 to element 1
				   C adds 1 to element 2 ...			 */
			}
		}
		
		for (int i = 0; i < 26; i++)
		{
			System.out.print((char)('A' + i)+" = "+totals[i]+", ");
		}
		
		int mostCommonNumber [] = new int [5];
		char mostCommonChar [] = new char [5];
		int leastCommonNumber [] = new int [5];
		char leastCommonChar [] = new char [5];
		int lowerIndex = 0;
		int upperIndex = 0;
		
		System.out.print("\n\nTop 5 Most Common Characters:\n");
		
		//Duplicates an the totals array for sorting purposes:
		int [] sorted = new int [26];
		for (int i = 0; i < totals.length; i++)
		{
			sorted[i] = totals[i];
		}
		Arrays.sort(sorted);
		
		for (int i = 0; i < totals.length; i++)
		{
			if(isLower(sorted, totals[i]))
			{
				leastCommonNumber[lowerIndex] = totals[i];
				leastCommonChar[lowerIndex] = (char)('A'+i);
				lowerIndex++;
			}
			
			if(isUpper(sorted, totals[i]))
			{
				mostCommonNumber[upperIndex] = totals[i];
				mostCommonChar[upperIndex] = (char)('A'+i);
				upperIndex++;
			}
		}
	
		for (int i = 0; i < 5; i++)
		{
			System.out.println(mostCommonChar[i] + " = " + mostCommonNumber[i]);
		}
		
		System.out.println("\nTop 5 Least Common Characters:");
		
		for (int i = 0; i < 5; i++)
		{
			System.out.println(leastCommonChar[i] + " = " + leastCommonNumber[i]);
		}
		
		//Closes the File:
		try
		{
			brFile.close();
		} catch (IOException e) {}
	}
	
	
	static boolean isLower(int [] array, int number)
	{
		for (int i = 0; i < 5; i++)
		{
			if (array[i] == number)
			{
				return true;
			}
		}
		return false;
	}
	
	
	static boolean isUpper(int [] array, int number)
	{
		for (int i = 21; i < 26; i++)
		{
			if (array[i] == number)
			{
				return true;
			}
		}
		return false;
	}
}
