package unit1;
import java.util.Scanner;
/* Program to get a sentence and then a substring.
 * Print out x's equal to the length of the substring in place of the substring.
 */
public class FindSubstringAndReplace
{
	public static void main (String [] args)
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		String sentence;
		String subString;
		String newSentence;
		String x = "";
		
		System.out.println("Type in a Sentence:");
		sentence = sc.nextLine();
		
		if (sentence.equals(""))
		{
			System.out.println("Error.");
			System.exit(0);
		}
		
		System.out.println("Type in a SubString:");
		subString = sc.nextLine();
		if (sentence.contains(subString))
		{
			for (int i = 0; i < subString.length(); i++)
			{
				x += 'x';
			}
			newSentence = sentence.replaceAll(subString, x);
			System.out.println(newSentence);
		} else {
			System.out.println("Not Found.");
		}
	}
}