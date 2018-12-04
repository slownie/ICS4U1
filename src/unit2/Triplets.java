/* Samuel Lownie
 * 09/17/2018
 * Print out any words that contain triplet characters.
 */
package unit2;
import java.util.*;
public class Triplets 
{
	public static void main(String[] args) 
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner (System.in);
		String word;
		String words [];
		String lowercaseWord = "";
		char currentCharacter;
		char previousCharacter;
		char nextCharacter;
		
		//Gets a word from the User and converts it to lowercaseWord:
		System.out.println("Type in a word:");
		word = sc.nextLine();
		lowercaseWord = word.toLowerCase();
		words = lowercaseWord.split(" ");
		
		//Loops through words:
		for (int f = 0; f < words.length; f++)
		{
			for (int i = 1; i < lowercaseWord.length() - 1; i++)
			{
				currentCharacter = lowercaseWord.charAt(i);
				previousCharacter = lowercaseWord.charAt(i - 1);
				nextCharacter = lowercaseWord.charAt(i + 1);
				
				if (currentCharacter == previousCharacter &&
						currentCharacter == nextCharacter)
					{
						System.out.println(words[f]);
					}
			}
		}
	}
}
